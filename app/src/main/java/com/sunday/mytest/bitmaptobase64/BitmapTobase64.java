package com.sunday.mytest.bitmaptobase64;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapTobase64 {
    private static String TAG = "BitmapTobase64";
    /*字符串转换成bitmap,需要去掉字符串的data:image/png;base64,*/
    public static Bitmap stringToBitmap(String string){
        Bitmap bitmap = null;
        Log.d(TAG,"string = "+string);
        try{
            byte[] bitmapArray;
            bitmapArray = Base64.decode(string,Base64.DEFAULT);
            //Log.d(TAG,"bitmapArray"+bitmapArray);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray,0,bitmapArray.length);
            Log.d(TAG,"bitmap = "+bitmap);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
    public static String bitmapToString(Bitmap bitmap){
        String string = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        string = Base64.encodeToString(bytes,Base64.DEFAULT);
        Log.d(TAG,"bitmapToString string = "+string);
        return string;
    }
    /*转码并保存成文件*/
    public static void decodeBase64File(String base64Code,String savePath){
        try{
            byte[] buffer = Base64.decode(base64Code,Base64.DEFAULT);
            FileOutputStream out = new FileOutputStream(savePath);
            out.write(buffer);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
