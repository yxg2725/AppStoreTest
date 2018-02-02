package com.example.appstoretest.utils;

import com.example.appstoretest.MyApplication;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ant on 2018/2/1.
 * 缓存处理工具
 */

public class CommonCacheProcess {

  /**
   * 从内存中获取缓存
   */
  public static String getCacheFromMemory(String key){
    return MyApplication.getProtocolCache().get(key);
  }

  /**
   * 从手机本地获取json数据
   * 包括：内存、本地文件
   *
   * @param key
   * @return
   */
  
  public static String getCacheFromLocal(String key){
    String json = getCacheFromMemory(key);
    
    if (json == null){
      json = getFromFile(key);
    }

    return json;
  }
  
  public static String getFromFile(String key){

    String path = FileUtils.getDir("json");
    File file = new File(path,key);
    if(file.exists()){
      //读取第一行
      try {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        String lastTime = bufferedReader.readLine();
        Long lastTimeMillis = Long.valueOf(lastTime);
        long currentTimeMillis = System.currentTimeMillis();

        if(currentTimeMillis - lastTimeMillis < Constants.DURATION){
          String json = bufferedReader.readLine();

          //缓存到内存中
          MyApplication.getProtocolCache().put(key,json);

          return json;
        }

      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    return MyApplication.getProtocolCache().get(key);
  }

  /**
   * 缓存网络数据到本地文件中
   * @param key
   * @param json
   */
  public static void cacheToFile(String key, String json) {
    String path = FileUtils.getDir("json");
    File file = new File(path,key);
    try {
      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

      bufferedWriter.write(System.currentTimeMillis() + "");
      bufferedWriter.newLine();
      bufferedWriter.write(json);
      bufferedWriter.flush();

      IOUtils.close(bufferedWriter);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
