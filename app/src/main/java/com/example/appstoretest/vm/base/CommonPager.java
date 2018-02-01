package com.example.appstoretest.vm.base;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.utils.UIUtils;

/**
 * Created by ant on 2018/1/31.
 */

public abstract class CommonPager {

  public FrameLayout containerView;
  public View errorView;
  public View emptyView;
  public View loadingView;

  public boolean isLoading = false;//是否正在加载数据
  public boolean isReadData = false;//是否加载完成数据
  public boolean isDataEmpty = false;//是否数据为空


  public void runOnUiThread(){

    Runnable action = new Runnable() {
      @Override
      public void run() {
        isLoading = false;

        if(isReadData){
          if (isDataEmpty) {
            showEmpty();
          } else {
            showSuccess();
          }
        }else {
          showError();
        }



      }
    };

    MyApplication.handler.post(action);
  }



  public CommonPager() {
    containerView = new FrameLayout(UIUtils.getContext());
  }


  public void dynamic() {

    if (isLoading || isReadData) return;
    isLoading = true;

    showLoading();

    loadData();
  }

  public void showError() {
    errorView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_error, null);
    errorView.findViewById(R.id.error_btn_retry).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        dynamic();
      }
    });

    changView(errorView);
  }

  public void showEmpty() {
    emptyView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_empty, null);
    changView(emptyView);
  }

  public void showLoading() {
    loadingView = LayoutInflater.from(UIUtils.getContext()).inflate(R.layout.pager_loading, null);
    changView(loadingView);
  }


  public void changView(View contentView) {
    containerView.removeAllViews();
    containerView.addView(contentView);
  }


  public abstract void loadData();
  public abstract void showSuccess();

}
