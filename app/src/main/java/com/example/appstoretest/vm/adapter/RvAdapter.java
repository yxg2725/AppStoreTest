package com.example.appstoretest.vm.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appstoretest.model.net.HomeInfo;

/**
 * Created by ant on 2018/1/31.
 */

public class RvAdapter extends RecyclerView.Adapter {
  private final HomeInfo info;
  private final FragmentActivity activity;

  public RvAdapter(HomeInfo info, FragmentActivity activity) {
    this.info = info;
    this.activity = activity;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    TextView view = new TextView(activity);
    return new MyViewHolder(view);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    MyViewHolder holder1 = (MyViewHolder) holder;
    TextView itemView = (TextView) holder1.itemView;
    itemView.setGravity(Gravity.CENTER);
    itemView.setTextSize(30);
    itemView.setText("我是第" + position + "行");
  }

  @Override
  public int getItemCount() {
    return 30;
  }

  static class MyViewHolder extends RecyclerView.ViewHolder{

    public MyViewHolder(View itemView) {
      super(itemView);
    }
  }
}
