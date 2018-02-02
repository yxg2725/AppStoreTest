package com.example.appstoretest.vm.holder;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.appstoretest.databinding.ItemAppinfoBinding;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.activity.DetailActivity;

/**
 * Created by ant on 2018/2/1.
 */

public class HomeHolder extends BaseHolder<AppInfo> {
  private final ItemAppinfoBinding normalBinding;

  public HomeHolder(View itemView) {
    super(itemView);
    normalBinding = DataBindingUtil.bind(itemView);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
        intent.putExtra("packageName", normalBinding.getApp().packageName);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        UIUtils.getContext().startActivity(intent);
      }
    });
  }


  public void setData(AppInfo appInfo) {
    normalBinding.setApp(appInfo);

    String imageUrl = getImageUrl(appInfo.iconUrl);

    Glide.with(UIUtils.getContext()).load(imageUrl).into(normalBinding.itemAppinfoIvIcon);

  }
}
