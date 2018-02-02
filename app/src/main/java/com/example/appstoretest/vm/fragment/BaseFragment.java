package com.example.appstoretest.vm.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.BaseCallBack;
import com.example.appstoretest.vm.base.CommonPager;

import java.util.HashMap;

import okhttp3.Call;

/**
 * Created by ant on 2018/1/31.
 */

public abstract class BaseFragment extends Fragment {

  public final CommonPager commonPager;
  private String key;

  public BaseFragment() {

    commonPager = new CommonPager() {
      @Override
      public void loadData() {
        BaseFragment.this.loadData();
      }

      @Override
      public void showSuccess() {
        BaseFragment.this.showSuccess();
      }
    };
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return commonPager.containerView;
  }



  //public abstract void loadData();
  public abstract void showSuccess();
  public abstract String getPath();
  public abstract void parseJson(String json);

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);

    if (isVisibleToUser){
      commonPager.dynamic();
    }

  }

  public void loadData(){
    //从内存中获取
    key = getPath() + ".0";
    String json = CommonCacheProcess.getCacheFromLocal(key);
    if(json != null){
      commonPager.isReadData = true;
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
    Call call = HttpUtils.getClient().newCall(HttpUtils.getRequest(getPath(), params));
    call.enqueue(new BaseCallBack(commonPager) {
      @Override
      public void onSuccess(String json) {
        //缓存内存
        MyApplication.getProtocolCache().put(key, json);
        //缓存到本地
        CommonCacheProcess.cacheToFile(key, json);

        commonPager.isReadData = true;
        parseJson(json);
      }
    });
  }

}
