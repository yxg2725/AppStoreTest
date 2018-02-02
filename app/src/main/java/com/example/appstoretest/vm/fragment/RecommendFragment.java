package com.example.appstoretest.vm.fragment;

import android.view.View;
import android.widget.TextView;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.utils.UIUtils;
import com.example.appstoretest.view.StellarMap;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by ant on 2018/2/2.
 */

public class RecommendFragment extends BaseFragment{
  private List<String> recommends;
  private StellarMap.Adapter adapter = new StellarMap.Adapter(){

    @Override
    public int getCount() {
      return recommends.size();
    }

    @Override
    protected View getView(int index, View convertView) {
      TextView tv = new TextView(UIUtils.getContext());
      tv.setText(recommends.get(index));
      return tv;
    }
  };

  @Override
  public void showSuccess() {
    StellarMap stellarMap = new StellarMap(getContext());
    stellarMap.setAdapter(adapter);
    commonPager.changView(stellarMap);
  }

  @Override
  public String getPath() {
    return Constants.Http.RECOMMEND;
  }

  @Override
  public void parseJson(String json) {

    recommends = MyApplication.getGson().fromJson(json,new TypeToken<List<String>>(){}.getType());
  }
}
