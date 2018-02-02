package com.example.appstoretest.vm.adapter;

import android.databinding.DataBindingUtil;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.databinding.ItemAppinfoBinding;
import com.example.appstoretest.model.net.AppInfo;
import com.example.appstoretest.model.net.HomeInfo;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.holder.BaseHolder;
import com.example.appstoretest.vm.holder.FooterHolder;
import com.example.appstoretest.vm.holder.HomeHolder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ant on 2018/1/31.
 */

public class HomeAdapter extends BaseRecyclerViewAdapter<AppInfo>{

  private static final int RVHEADER = 0;//头布局
  private final HomeInfo info;
  private final FragmentActivity activity;

  public HomeAdapter(HomeInfo info, FragmentActivity activity) {
    super(info.list);

    this.info = info;
    this.activity = activity;
  }


  @Override
  public int getItemViewType(int position) {
    super.getItemViewType(position);

    if (position == 0){
      viewType = RVHEADER;//头布局
    }
    return viewType;
  }

  @Override
  public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseHolder holder = super.onCreateViewHolder(parent, viewType);
    if(viewType == RVHEADER){
      View view = LayoutInflater.from(activity).inflate(R.layout.adapter_header,parent,false);
      holder = new HeaderHolder(view);
    }
    return holder;
  }

  @Override
  public void onBindViewHolder(BaseHolder holder, int position) {
    super.onBindViewHolder(holder,position);
    if (viewType == RVHEADER){
      holder.setData(info.picture);
    }


  }



  @Override
  public int getItemCount() {
    return info.list.size() + 1 + 1;
  }

  @Override
  protected List<AppInfo> getNextPagerData(String json) {
    HomeInfo nextPager = MyApplication.getGson().fromJson(json, HomeInfo.class);
    return nextPager.list;
  }

  @Override
  protected AppInfo getNomalItemData(int position) {

    return info.list.get(position-1);
  }


  //头布局holder
   class HeaderHolder extends BaseHolder<List<String>> {

    private final SliderLayout sliderLayout;

    public HeaderHolder(View itemView) {
      super(itemView);
      sliderLayout = (SliderLayout)itemView;
      // 高度的获取：保持图片的宽高比例不变
      // 181/480
      int widthPixels = activity.getResources().getDisplayMetrics().widthPixels;
      int height = widthPixels * 181 / 480;// 像素
      RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,UIUtils.px2Dip(height));

      sliderLayout.setLayoutParams(params);

    }

    @Override
    public void setData(List<String> pictures) {

      sliderLayout.removeAllSliders();

      for(String item: pictures){
        DefaultSliderView defaultSliderView = new DefaultSliderView(activity);

        // http://localhost:8080/GooglePlayServer/image?name=
        String imageUrl = getImageUrl(item);
        defaultSliderView.image(imageUrl);
        sliderLayout.addSlider(defaultSliderView);
      }
    }
  }



}
