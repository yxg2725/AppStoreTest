package com.example.appstoretest.vm.holder;

import android.databinding.DataBindingUtil;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.appstoretest.R;
import com.example.appstoretest.databinding.ItemSubjectBinding;
import com.example.appstoretest.model.net.SubjectInfo;
import com.example.appstoretest.utils.ImageUtils;
import com.example.appstoretest.utils.UIUtils;

/**
 * Created by ant on 2018/2/2.
 */

public class SubjectHolder extends BaseHolder<SubjectInfo> {

  private final ItemSubjectBinding binding;

  public SubjectHolder(View itemView) {
    super(itemView);
    binding = DataBindingUtil.bind(itemView);
  }

  @Override
  public void setData(SubjectInfo data) {
    binding.setSubject(data);

    String imageUrl = getImageUrl(data.url);
    ImageUtils.loadIntoUseFitWidth(UIUtils.getContext(),imageUrl, R.drawable.ic_default, binding.itemSubjectIvIcon);
  }
}
