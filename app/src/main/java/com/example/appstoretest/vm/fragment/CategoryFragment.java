package com.example.appstoretest.vm.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.model.net.CategoryInfo;
import com.example.appstoretest.utils.Constants;
import com.example.appstoretest.view.RecyclerViewFactory;
import com.example.appstoretest.vm.adapter.CategoryAdapter;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by ant on 2018/2/2.
 */

public class CategoryFragment extends BaseFragment {

  private List<CategoryInfo> datas;

  @Override
  public void showSuccess() {
    RecyclerView mRv = RecyclerViewFactory.createVertical();
    CategoryAdapter rvAdapter = new CategoryAdapter(datas);
    mRv.setAdapter(rvAdapter);

    commonPager.changView(mRv);
  }

  @Override
  public String getPath() {
    return Constants.Http.CATEGORY;
  }

  @Override
  public void parseJson(String json) {

    try {
      datas = new ArrayList<>();
      JSONArray array1=new JSONArray(json);
      for(int i=0;i<array1.length();i++) {
        JSONObject object = array1.getJSONObject(i);
        // 标题项处理
        String title = object.getString("title");
        CategoryInfo titleItem=new CategoryInfo();
        titleItem.title=title;
        titleItem.isTitle=true;
        datas.add(titleItem);

        // 分类项处理
        JSONArray infos = object.getJSONArray("infos");
        for(int j=0;j<infos.length();j++){
          JSONObject infosJSONObject = infos.getJSONObject(j);
          /**
           * name1	休闲
           name2	棋牌
           name3	益智
           url1	image/category_game_0.jpg
           url2	image/category_game_1.jpg
           url3	image/category_game_2.jpg
           */
          CategoryInfo item=new CategoryInfo();
          item.url1=infosJSONObject.getString("url1");
          item.url2=infosJSONObject.getString("url2");
          item.url3=infosJSONObject.getString("url3");

          item.name1=infosJSONObject.getString("name1");
          item.name2=infosJSONObject.getString("name2");
          item.name3=infosJSONObject.getString("name3");

          datas.add(item);
        }
      }


      if (datas != null && datas.size() > 0) {
        commonPager.isDataEmpty = false;
      } else {
        commonPager.isDataEmpty = true;
      }
    } catch (JSONException e) {
      e.printStackTrace();
      commonPager.isReadData = false;
    }
  }
}
