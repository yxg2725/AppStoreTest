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
import com.example.appstoretest.vm.adapter.HomeAdapter;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * Created by ant on 2018/1/31.
 */

public class HomeFragment extends BaseFragment {

  private HomeInfo info;

  @Override
  public void showSuccess() {
    RecyclerView mRv = RecyclerViewFactory.createVertical();
    HomeAdapter rvAdapter = new HomeAdapter(info,this.getActivity());
    mRv.setAdapter(rvAdapter);

    commonPager.changView(mRv);
  }

  @Override
  public String getPath() {
    return Constants.Http.HOME;
  }

  @Override
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
