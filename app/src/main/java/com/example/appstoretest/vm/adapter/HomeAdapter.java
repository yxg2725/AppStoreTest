package com.example.appstoretest.vm.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstoretest.model.net.HomeInfo;

/**
 * Created by ant on 2018/1/31.
 */

public class HomeAdapter extends RecyclerView.Adapter{

  private static final int RVHEADER = 0;
  private static final int NORMAL_LAYOUT = 1;
  private final HomeInfo info;
  private final FragmentActivity activity;

  private int itemType;

  public HomeAdapter(HomeInfo info, FragmentActivity activity) {

    this.info = info;
    this.activity = activity;
  }


  @Override
  public int getItemViewType(int position) {

    if (position == 0){
      itemType = RVHEADER;
    }else{
      itemType = NORMAL_LAYOUT;
    }
    return itemType;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }


}
