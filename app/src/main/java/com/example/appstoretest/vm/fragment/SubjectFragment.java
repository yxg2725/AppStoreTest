package com.example.appstoretest.vm.fragment;

import android.support.v7.widget.RecyclerView;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.model.net.SubjectInfo;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.adapter.HomeAdapter;
import com.example.appstoretest.vm.adapter.SubjectAdapter;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import static android.R.id.list;

/**
 * Created by ant on 2018/2/2.
 */

public class SubjectFragment extends BaseFragment {

  private List<SubjectInfo> datas;

  @Override
  public void showSuccess() {
    RecyclerView mRv = RecyclerViewFactory.createVertical();
    SubjectAdapter rvAdapter = new SubjectAdapter(datas);
    mRv.setAdapter(rvAdapter);

    commonPager.changView(mRv);
  }

  @Override
  public String getPath() {
    return Constants.Http.SUBJECT;
  }

  @Override
  public void parseJson(String json) {
    datas = MyApplication.getGson().fromJson(json, new TypeToken<List<SubjectInfo>>() {}.getType());

    if ((datas != null && datas.size() > 0)) {
      commonPager.isDataEmpty = false;
    } else {
      commonPager.isDataEmpty = true;
    }
  }
}
