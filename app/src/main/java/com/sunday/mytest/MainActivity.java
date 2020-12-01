package com.sunday.mytest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.sunday.mytest.bitmaptobase64.BitmapTobase64;
import com.sunday.mytest.networkutil.NetWorkUtil;
import com.sunday.mytest.okhttptest.OkHttpTest;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Bitmap mbitmap = null;
    String mBitmapBase64Str = null;
    private static String TAG = "MainActivity";
    private static Bitmap mBase64ToBitmap = null;
    private static final  int UPDATE_IMAGE = 1 ;
    private static ImageView mimageView = null;
    private displayImageHandler mdisplayImageHandler = new displayImageHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbitmap = BitmapFactory.decodeResource(getResources(),R.drawable.qrcode);
        ThreadBitmapToBase threadBitmapToBase = new ThreadBitmapToBase();
        threadBitmapToBase.start();
        mimageView = (ImageView)findViewById(R.id.img_test);
        Log.d(TAG,"mBase64ToBitmap = "+mBase64ToBitmap);
        boolean isConnect = NetWorkUtil.isConnected(getApplicationContext());
        Log.d(TAG,"isConnect = "+isConnect);
        OkHttpTest okHttpTest = new OkHttpTest();
        okHttpTest.getOkHttpTest();
        //post 请求
        okHttpTest.OkHttpPostTestT();
    }

    class ThreadBitmapToBase extends Thread{
        @Override
        public void run() {
            Log.d(TAG,"mbitmap="+mbitmap);
            if(mbitmap != null){
                mBitmapBase64Str = BitmapTobase64.bitmapToString(mbitmap);
                if(mBitmapBase64Str!=null){
                    mBase64ToBitmap = BitmapTobase64.stringToBitmap(mBitmapBase64Str);
                    Message message= new Message();
                    message.what = UPDATE_IMAGE;
                    mdisplayImageHandler.sendMessage(message);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mdisplayImageHandler.removeMessages(1);
    }

    private static class displayImageHandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case UPDATE_IMAGE:
                    if(mBase64ToBitmap!=null){
                        mimageView.setImageBitmap(mBase64ToBitmap);
                    }
                    break;
            }

        }
    }
    //判断网络是否正常连接
    public boolean isNetWorkConnected(Context context){
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){
            isConnected = networkInfo.isAvailable();
        }
        return  isConnected;
    }
}