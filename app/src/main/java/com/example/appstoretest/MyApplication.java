package com.example.appstoretest;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

/**
 * Created by ant on 2018/1/31.
 */

public class MyApplication extends Application {

  private static Context context;
  public static Handler handler;
  @Override
  public void onCreate() {
    super.onCreate();

    context = this.getApplicationContext();
    handler = new Handler();
  }

  public static Context getContext(){
    return context;
  }
}
