package com.example.appstoretest.vm.fragment;


import android.graphics.ImageFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.appstoretest.R;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.model.net.HomeInfo;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.adapter.RvAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.os.Build.VERSION_CODES.O;

/**
 * Created by ant on 2018/1/31.
 */

public class AppFragment extends BaseFragment {

  private RecyclerView mRv;
  private HomeInfo info;

  @Override
  public void showSuccess() {
    mRv = RecyclerViewFactory.createVertical();

    RvAdapter rvAdapter = new RvAdapter(info,this.getActivity());
    mRv.setAdapter(rvAdapter);
    commonPager.changView(mRv);
  }


  @Override
  public void loadData() {
// http://localhost:8080/GooglePlayServer/home?index=0
    OkHttpClient client = HttpUtils.getClient();
    final HashMap<String, Object> params = new HashMap<String, Object>();
    params.put("index", 0);
    Request request = HttpUtils.getRequest(Constants.Http.HOME,params);

    Call call = client.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        commonPager.isReadData = false;
        commonPager.runOnUiThread();
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {

        if(response.code() == 200){
          commonPager.isReadData = true;
          String jsonString = response.body().string();
          Gson gson = new Gson();
          info = gson.fromJson(jsonString, HomeInfo.class);


          List<AppInfo> list = info.list;
          List<String> picture = info.picture;
          if ((list != null && list.size() > 0) || (picture != null && picture.size() > 0)) {
            commonPager.isDataEmpty = false;
          } else {
            commonPager.isDataEmpty = true;
          }
        }else{
          commonPager.isReadData = false;
        }

        // 更新界面
        commonPager.runOnUiThread();
      }


    });

  }


}
