package com.example.appstoretest.vm.holder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.appstoretest.databinding.ItemAppinfoBinding;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.utils.UIUtils;

/**
 * Created by ant on 2018/2/1.
 */

public class HomeHolder extends BaseHolder<AppInfo> {
  private final ItemAppinfoBinding normalBinding;

  public HomeHolder(View itemView) {
    super(itemView);
    normalBinding = DataBindingUtil.bind(itemView);
  }


  public void setData(AppInfo appInfo) {
    normalBinding.setApp(appInfo);

    String imageUrl = getImageUrl(appInfo.iconUrl);

    Glide.with(UIUtils.getContext()).load(imageUrl).into(normalBinding.itemAppinfoIvIcon);

  }
}
