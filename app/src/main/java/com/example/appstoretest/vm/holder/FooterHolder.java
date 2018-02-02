package com.example.appstoretest.vm.holder;

import android.view.View;
import android.widget.LinearLayout;

import com.example.appstoretest.MyApplication;
import com.example.appstoretest.R;
import com.example.appstoretest.vm.adapter.BaseRecyclerViewAdapter;
import com.example.appstoretest.vm.adapter.HomeAdapter;

/**
 * Created by ant on 2018/2/1.
 */

public class FooterHolder extends BaseHolder<Integer>{
  public static final int LOADING = 1;
  public static final int ERROR = -1;
  public static final int NULL = 0;


  private final LinearLayout loading;
  private final LinearLayout retry;

  public FooterHolder(View itemView, final BaseRecyclerViewAdapter adapter) {
    super(itemView);

    loading = (LinearLayout) itemView.findViewById(R.id.item_loadmore_container_loading);
    retry = (LinearLayout) itemView.findViewById(R.id.item_loadmore_container_retry);

    retry.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        setData(FooterHolder.LOADING);
        //加载下一页数据
        adapter.loadNextData();
      }
    });
  }


  public void setData(final Integer state) {
    MyApplication.handler.post(new Runnable() {
      @Override
      public void run() {
        loading.setVisibility(View.GONE);
        retry.setVisibility(View.GONE);
        switch (state) {
          case LOADING:
            loading.setVisibility(View.VISIBLE);
            break;
          case ERROR:
            retry.setVisibility(View.VISIBLE);
            break;
        }
      }
    });

  }


}
