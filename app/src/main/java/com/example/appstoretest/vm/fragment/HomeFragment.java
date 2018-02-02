package com.example.appstoretest.vm.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.model.net.HomeInfo;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.BaseCallBack;
import com.example.appstoretest.vm.adapter.HomeAdapter;
import com.example.appstoretest.vm.adapter.RvAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ant on 2018/1/31.
 */

public class HomeFragment extends BaseFragment {

  private HomeInfo info;
  private String key;

  @Override
  public void showSuccess() {
    RecyclerView mRv = RecyclerViewFactory.createVertical();
    HomeAdapter rvAdapter = new HomeAdapter(info,this.getActivity());
    mRv.setAdapter(rvAdapter);

    commonPager.changView(mRv);
  }

  @Override
  public void loadData() {

    //从内存中获取
    key = Constants.Http.HOME + ".0";
    String json = CommonCacheProcess.getCacheFromLocal(key);
    if(json != null){

      parseJson(json);
      commonPager.runOnUiThread();
    }else{
      //获取网络数据
      loadNetData();
    }

  }

  private void loadNetData() {
    HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("index", 0);
    Call call = HttpUtils.getClient().newCall(HttpUtils.getRequest(Constants.Http.HOME, params));
    call.enqueue(new BaseCallBack(commonPager) {
      @Override
      public void onSuccess(String json) {
        //缓存内存
        MyApplication.getProtocolCache().put(key, json);
        //缓存到本地
        CommonCacheProcess.cacheToFile(key, json);

        parseJson(json);
      }
    });
  }

  public void parseJson(String json){

    info = MyApplication.getGson().fromJson(json, HomeInfo.class);

    List<AppInfo> list = info.list;
    List<String> picture = info.picture;
    if ((list != null && list.size() > 0) || (picture != null && picture.size() > 0)) {
      commonPager.isDataEmpty = false;
    } else {
      commonPager.isDataEmpty = true;
    }

  }

}
