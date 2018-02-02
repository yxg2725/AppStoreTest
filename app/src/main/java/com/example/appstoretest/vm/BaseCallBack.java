package com.example.appstoretest.vm;


import com.example.appstoretest.MyApplication;
import com.example.appstoretest.utils.CommonCacheProcess;
import com.example.appstoretest.vm.base.CommonPager;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by ant on 2018/2/1.
 */

public abstract class BaseCallBack implements Callback {

  private final CommonPager commonPager;
  public BaseCallBack(CommonPager commonPager) {
    this.commonPager = commonPager;
  }

  @Override
  public void onFailure(Call call, IOException e) {
    commonPager.isReadData = false;
    commonPager.runOnUiThread();
  }

  @Override
  public void onResponse(Call call, Response response) throws IOException {
    if(response.code() == 200){
      commonPager.isReadData = true;
      String json = response.body().string();
      //缓存内存
      onSuccess(json);
    }else{
      commonPager.isReadData = false;
    }

    // 更新界面
    commonPager.runOnUiThread();
  }

  public abstract void onSuccess(String json);

}
