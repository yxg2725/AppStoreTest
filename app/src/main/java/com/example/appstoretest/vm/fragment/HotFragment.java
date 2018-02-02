package com.example.appstoretest.vm.fragment;

import android.support.v4.widget.NestedScrollView;
import android.widget.TextView;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.view.FlowLayout;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by ant on 2018/2/2.
 */

public class HotFragment extends BaseFragment{

  private List<String> hots;

  @Override
  public void showSuccess() {

    NestedScrollView nestedScrollView = new NestedScrollView(this.getActivity());
    FlowLayout flowLayout = new FlowLayout(this.getActivity());

    for(String item:hots){
      TextView tv = new TextView(UIUtils.getContext());
      tv.setText(item);

      int padding = UIUtils.dip2Px(5);
      tv.setPadding(padding,padding,padding,padding);

      flowLayout.addView(tv);
    }

    nestedScrollView.addView(flowLayout);
    commonPager.changView(nestedScrollView);
  }

  @Override
  public String getPath() {
    return Constants.Http.HOT;
  }

  @Override
  public void parseJson(String json) {
    hots = MyApplication.getGson().fromJson(json, new TypeToken<List<String>>() {
    }.getType());
  }
}
