package com.example.appstoretest.vm.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.HttpUtils;

import java.util.HashMap;

/**
 * Created by ant on 2018/2/1.
 */

public abstract class BaseHolder<D> extends RecyclerView.ViewHolder {
  public BaseHolder(View itemView) {
    super(itemView);
  }

  public abstract void setData(D data);

  /**
   * 获取图片url
   * @param name
   * @return
   */
  public String getImageUrl(String name) {
    StringBuffer iconUrlBuffer = new StringBuffer(Constants.Http.HOST);
    HashMap<String, Object> params = new HashMap<>();
    params.put("name", name);
    iconUrlBuffer.append(Constants.Http.IMAGE).append(HttpUtils.getUrlParamsByMap(params));
    return iconUrlBuffer.toString();
  }
}
