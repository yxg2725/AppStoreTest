package com.example.appstoretest.vm.fragment;

import android.support.v7.widget.RecyclerView;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.BaseCallBack;
import com.example.appstoretest.vm.adapter.AppAdapter;
import com.example.appstoretest.vm.adapter.GameAdapter;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import okhttp3.Call;

/**
 * Created by ant on 2018/2/2.
 */

public class GameFragment extends BaseFragment {
  private RecyclerView mRv;
  private List<AppInfo> datas;



  @Override
  public void showSuccess() {
    mRv = RecyclerViewFactory.createVertical();

    GameAdapter rvAdapter = new GameAdapter(datas);
    mRv.setAdapter(rvAdapter);
    commonPager.changView(mRv);
  }

  @Override
  public String getPath() {
    return Constants.Http.GAME;
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
