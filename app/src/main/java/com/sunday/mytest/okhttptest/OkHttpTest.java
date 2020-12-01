package com.sunday.mytest.okhttptest;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTest {
    public  static String urlStr = "https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    private static final String TAG = "OkHttpTest";
    public void getOkHttpTest() {
        //请求测试
        RequestTestThread requestTestThread = new RequestTestThread();
        requestTestThread.start();
        //异步请求
        RequestTestThread1 requestTestThread1 = new RequestTestThread1();
        requestTestThread1.start();
    }
    static  class RequestTestThread extends Thread{
        @Override
        public void run() {
                //1.构建OkHttpClient 对象
                OkHttpClient client = new OkHttpClient();
                //2.构造Request对象
                Request request = new Request.Builder()
                        .url(urlStr)
                        .build();
                //3.将 Request 对象封装为Call
                //4.通过call对象同步执行
                try (Response response = client.newCall(request).execute()) {
                    String str =  response.body().string();
                    Log.d(TAG,"str = "+str);
                }catch (IOException e){
                    e.printStackTrace();
                }
        }
    }
    static class RequestTestThread1 extends  Thread{
        @Override
        public void run() {
            //异步请求
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(urlStr).build();
            Call call = client.newCall(request);
            call.enqueue(new EnqueueCallBack());
        }
    }
    static class EnqueueCallBack implements Callback{
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG,"OnFailure :" + e);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.d(TAG,"onResponse :"+response.body().string());
        }
    }
    //post请求
    public void OkHttpPostTestT(){
        OkHttpPostThread okHttpPostThread = new OkHttpPostThread();
        okHttpPostThread.start();
    }
    static class OkHttpPostThread extends Thread{
        @Override
        public void run() {
            new OkHttpTest().OkHttpPostTest();
        }
    }
    public void OkHttpPostTest(){
        MediaType JSON = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        String json = "{ \n" +
                "\"mobile\": \"13818169541\", \n" +
                "\"captcha\": \"LYA1\", \n" +
                "\"sid\": \"f86ae0ed-85ef-4517-b303-4857d8cf7cce\" \n" +
                "}";
        String url = "https://c2bt4.maxuscloud.com/sso/authentication/sid";
        RequestBody requestBody = RequestBody.create(json,JSON);
        try {
            Request request = new Request.Builder().url(url).post(requestBody).build();
            Call call = client.newCall(request);
            call.enqueue(new PostEnqueueCallBack());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    static class PostEnqueueCallBack implements Callback{
        @Override
        public void onFailure(Call call, IOException e) {
            Log.d(TAG,"PostEnqueueCallBack OnFailure :" + e);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.d(TAG,"PostEnqueueCallBack onResponse :"+response.body().string());
        }
    }
}
