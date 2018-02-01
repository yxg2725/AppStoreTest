package com.example.appstoretest.utils;

import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ant on 2018/1/31.
 */

public class HttpUtils {
  private static OkHttpClient client = new OkHttpClient();

  public static OkHttpClient getClient() {
    return client;
  }

  /**
   * get请求
   * @param path
   * @param params
   * @return
   */
  public static Request getRequest(String path, Map<String, Object> params) {
    StringBuffer urlBuffer = new StringBuffer(Constants.Http.HOST);
    urlBuffer.append(path);
    urlBuffer.append(getUrlParamsByMap(params));
    Request request = new Request.Builder().get().url(urlBuffer.toString()).build();
    return request;
  }

  public static String getUrlParamsByMap(Map<String, Object> map) {
    if (map == null) {
      return "";
    }
    StringBuffer sb = new StringBuffer("?");
    for (Map.Entry<String, Object> entry : map.entrySet()) {
      sb.append(entry.getKey() + "=" + entry.getValue());
      sb.append("&");
    }
    String s = sb.toString();
    if (s.endsWith("&")) {
      s = s.substring(0, s.length() - 1);
    }
    return s;
  }
}
