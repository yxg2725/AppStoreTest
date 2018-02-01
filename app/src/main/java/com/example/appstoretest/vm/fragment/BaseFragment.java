package com.example.appstoretest.vm.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.vm.base.CommonPager;

/**
 * Created by ant on 2018/1/31.
 */

public abstract class BaseFragment extends Fragment {

  public final CommonPager commonPager;

  public BaseFragment() {

    commonPager = new CommonPager() {
      @Override
      public void loadData() {
        BaseFragment.this.loadData();
      }

      @Override
      public void showSuccess() {
        BaseFragment.this.showSuccess();
      }
    };
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return commonPager.containerView;
  }



  public abstract void loadData();
  public abstract void showSuccess();

  @Override
  public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);

    if (isVisibleToUser){
      commonPager.dynamic();
    }

  }


}
