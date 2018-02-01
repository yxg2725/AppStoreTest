package com.example.appstoretest.model.net;

import java.util.List;

/**
 * Created by itheima.
 * 应用信息封装
 */
public class AppInfo {

    /**
     * id : 1525489
     * name : 黑马程序员
     * packageName : com.itheima.www
     * iconUrl : app/com.itheima.www/icon.jpg
     * stars : 5
     * size : 91767
     * downloadUrl : app/com.itheima.www/com.itheima.www.apk
     * des : 产品介绍：google市场app测试。
     */

    public long id;
    public String name;
    public String packageName;
    public String iconUrl;
    public float stars;
    public long size;
    public String downloadUrl;
    public String des;


    public String author;//黑马程序员
    public String date;//"2015-06-10"
    public String version;//	1.1.0605.0
    public String downloadNum;//	40万+
    public List<SafeInfo> safe;
    public List<String> screen;

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
