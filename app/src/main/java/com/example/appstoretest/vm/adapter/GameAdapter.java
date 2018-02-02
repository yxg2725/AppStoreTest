package com.example.appstoretest.vm.adapter;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.utils.Constants;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by ant on 2018/2/2.
 */

public class GameAdapter extends BaseRecyclerViewAdapter<AppInfo> {
  public GameAdapter(List<AppInfo> datas) {
    super(datas);
  }

  @Override
  public int getItemViewType(int position) {
    if (position == (datas.size())){
      viewType = RVFOOTER;
    }else{
      viewType = NORMAL_LAYOUT;
    }
    return viewType;
  }

  @Override
  protected List<AppInfo> getNextPagerData(String json) {
    return MyApplication.getGson().fromJson(json,new TypeToken<List<AppInfo>>(){}.getType());
  }

  @Override
  protected AppInfo getNomalItemData(int position) {
    return datas.get(position);
  }

  @Override
  protected String getPath() {
    return Constants.Http.GAME;
  }

  @Override
  protected int getNomalLayoutDesId() {
    return R.layout.item_appinfo;
  }

  @Override
  public int getItemCount() {
    return datas.size()+1;
  }
}
