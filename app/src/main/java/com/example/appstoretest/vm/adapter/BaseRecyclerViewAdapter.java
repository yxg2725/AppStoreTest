package com.example.appstoretest.vm.adapter;

import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
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
 * Created by ant on 2018/2/1.
 */

public abstract class BaseRecyclerViewAdapter<D> extends RecyclerView.Adapter<BaseHolder<D>> {

  public static final int NORMAL_LAYOUT = 1;//正常布局
  public static final int RVFOOTER = 2;//脚布局
  public int viewType;
  public FooterHolder footerHolder;

  public final List<D> datas;
  public String key;

  public BaseRecyclerViewAdapter(List<D> datas) {
    this.datas = datas;
  }

  @Override
  public int getItemViewType(int position) {

    if(position == datas.size()+1){
      viewType = RVFOOTER;//加载下一页布局
    }else{
      viewType = NORMAL_LAYOUT;//正常布局
    }
    return viewType;
  }

  @Override
  public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    BaseHolder holder = null;
    if (viewType == NORMAL_LAYOUT){
      View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_appinfo,parent,false);
      holder = new HomeHolder(view);
    }else if(viewType == RVFOOTER){
      View view = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.item_loadmore,parent,false);
      holder = footerHolder = new FooterHolder(view,this);
    }
    return holder;
  }

  @Override
  public void onBindViewHolder(BaseHolder holder, int position) {
    if(viewType == NORMAL_LAYOUT){
      D data = getNomalItemData(position);
      holder.setData(data);
    }else if(viewType == RVFOOTER){
      holder.setData(FooterHolder.LOADING);

      //加载下一页数据
      loadNextData(footerHolder);
    }
  }



  @Override
  public int getItemCount() {
    return 0;
  }

  public void parseJson(String json){

    List<D> nextPagerData = getNextPagerData(json);
    if(nextPagerData != null || nextPagerData.size() > 0){

      datas.addAll(nextPagerData);
      MyApplication.handler.post(new Runnable() {
        @Override
        public void run() {
          notifyDataSetChanged();
        }
      });

    }else{
      footerHolder.setData(FooterHolder.NULL);
    }
  }

  /**
   * 获取下一页数据
   */
  protected abstract List<D> getNextPagerData(String json);
  /**
   * 获取正常布局内容
   */
  protected abstract D getNomalItemData(int position);


  public void loadNextData(final FooterHolder footerHolder) {


    //从本地缓存中读取
    key = Constants.Http.HOME + "." + datas.size();
    String json = CommonCacheProcess.getCacheFromLocal(key);

    if(json != null){

      parseJson(json);

    }else{
      //从网络读取
      loadNetData();
    }

  }

  private void loadNetData() {
    HashMap<String, Object> params = new HashMap<>();
    params.put("index", datas.size());
    final Request request = HttpUtils.getRequest(Constants.Http.HOME, params);

    Call call = HttpUtils.getClient().newCall(request);

    call.enqueue(new Callback() {

      @Override
      public void onFailure(Call call, IOException e) {
        SystemClock.sleep(2000);
        footerHolder.setData(FooterHolder.ERROR);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        SystemClock.sleep(2000);
        if (response.code() == 200){
          String json = response.body().string();
          parseJson(json);

          //缓存到内存
          MyApplication.getProtocolCache().put(key,json);
          //缓存到本地
          CommonCacheProcess.cacheToFile(key,json);

        }else{
          footerHolder.setData(FooterHolder.ERROR);
        }

      }
    });
  }
}
