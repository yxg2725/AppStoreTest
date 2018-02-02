package com.example.appstoretest.vm.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appstoretest.R;
import com.example.appstoretest.databinding.ItemCategoryNormalBinding;
import com.example.appstoretest.model.net.CategoryInfo;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.holder.BaseHolder;

import java.util.List;

/**
 * Created by ant on 2018/2/2.
 */

public class CategoryAdapter extends RecyclerView.Adapter<BaseHolder> {

  private static final int TITLE = 0;
  private static final int NOMAL = 1;
  private final List<CategoryInfo> datas;

  public CategoryAdapter(List<CategoryInfo> datas) {
    this.datas = datas;
  }

  @Override
  public int getItemViewType(int position) {
    CategoryInfo category = datas.get(position);
    if(category.isTitle){
      return TITLE;
    }
    return NOMAL;
  }

  @Override
  public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseHolder holder = null;
    switch (viewType) {
      case TITLE:
        holder = new TitleHolder(View.inflate(UIUtils.getContext(), android.R.layout.simple_list_item_1, null));
        break;
      case NOMAL:
        holder = new CategoryHolder(View.inflate(UIUtils.getContext(), R.layout.item_category_normal, null));
        break;
    }
    return holder;
  }

  @Override
  public void onBindViewHolder(BaseHolder holder, int position) {
    CategoryInfo category = datas.get(position);
    if(category.isTitle){
      ((TitleHolder)holder).setData(category.title);
    }else{
      ((CategoryHolder)holder).setData(category);
    }
  }

  @Override
  public int getItemCount() {
    return datas.size();
  }

  /**
   * 标题条目
   */
  class TitleHolder extends BaseHolder<String> {

    public TitleHolder(View itemView) {
      super(itemView);
    }

    @Override
    public void setData(String data) {
      ((TextView) itemView).setText(data);
      ((TextView) itemView).setTextColor(Color.DKGRAY);
    }

  }

  /**
   * 分类条目
   */
  class CategoryHolder extends BaseHolder<CategoryInfo> {
    ItemCategoryNormalBinding binding;

    public CategoryHolder(View itemView) {
      super(itemView);
      itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
      binding = DataBindingUtil.bind(itemView);
    }

    @Override
    public void setData(CategoryInfo data) {
      binding.setCategory(data);
      Glide.with(UIUtils.getContext()).load(getImageUrl(data.url1)).into(binding.itemCategoryIcon1);
      Glide.with(UIUtils.getContext()).load(getImageUrl(data.url2)).into(binding.itemCategoryIcon2);
      Glide.with(UIUtils.getContext()).load(getImageUrl(data.url3)).into(binding.itemCategoryIcon3);
    }

  }
}
