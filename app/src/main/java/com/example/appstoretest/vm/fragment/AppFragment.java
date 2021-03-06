package com.example.appstoretest.vm.fragment;


import android.support.v7.widget.RecyclerView;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.model.net.HomeInfo;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.BaseCallBack;
import com.example.appstoretest.vm.adapter.AppAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.id.list;

/**
 * Created by ant on 2018/1/31.
 */

public class AppFragment extends BaseFragment {

  private RecyclerView mRv;
  private List<AppInfo> datas;

  @Override
  public void showSuccess() {
    mRv = RecyclerViewFactory.createVertical();

    AppAdapter rvAdapter = new AppAdapter(datas);
    mRv.setAdapter(rvAdapter);
    commonPager.changView(mRv);
  }


  @Override
  public String getPath() {
    return Constants.Http.APP;
  }

  @Override
  public void parseJson(String json){
    datas = MyApplication.getGson().fromJson(json, new TypeToken<List<AppInfo>>(){}.getType());

    if ((datas != null && datas.size() > 0) ) {
      commonPager.isDataEmpty = false;
    } else {
      commonPager.isDataEmpty = true;
    }

  }

}
