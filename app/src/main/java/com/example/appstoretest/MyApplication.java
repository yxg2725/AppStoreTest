package com.example.appstoretest;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.google.gson.Gson;

import java.util.HashMap;

/**
 * Created by ant on 2018/1/31.
 */

public class MyApplication extends Application {

  private static Context context;
  public static Handler handler;
  private static Gson gson;
  private static HashMap<String, String> protocolCache;

  @Override
  public void onCreate() {
    super.onCreate();

    context = this.getApplicationContext();
    handler = new Handler();
    gson = new Gson();
    protocolCache = new HashMap<>();
  }

  public static Context getContext(){
    return context;
  }

  public static Gson getGson(){
    return gson;
  }

  public static HashMap<String,String> getProtocolCache(){
    return protocolCache;
  }
}
