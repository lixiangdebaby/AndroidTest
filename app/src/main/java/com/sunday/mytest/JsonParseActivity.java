package com.sunday.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.sunday.mytest.jsonparse.GsonTest;
import com.sunday.mytest.jsonparse.JsonParse;
import com.sunday.mytest.jsonparse.JsonRootBean;

public class JsonParseActivity extends AppCompatActivity {
    private static final String TAG = "JsonParseActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String testStr = "{\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"courseId\": 13,\n" +
                "\t\t\"id\": 409,\n" +
                "\t\t\"name\": \"郭霖\",\n" +
                "\t\t\"order\": 190001,\n" +
                "\t\t\"parentChapterId\": 407,\n" +
                "\t\t\"userControlSetTop\": false,\n" +
                "\t\t\"visible\": 1\n" +
                "\t}\n";
        String testStr0 = "{\n" +
                "\t\"data\": [{\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"courseId\": 13,\n" +
                "\t\t\"id\": 408,\n" +
                "\t\t\"name\": \"鸿洋\",\n" +
                "\t\t\"order\": 190000,\n" +
                "\t\t\"parentChapterId\": 407,\n" +
                "\t\t\"userControlSetTop\": false,\n" +
                "\t\t\"visible\": 1\n" +
                "\t}, {\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"courseId\": 13,\n" +
                "\t\t\"id\": 409,\n" +
                "\t\t\"name\": \"郭霖\",\n" +
                "\t\t\"order\": 190001,\n" +
                "\t\t\"parentChapterId\": 407,\n" +
                "\t\t\"userControlSetTop\": false,\n" +
                "\t\t\"visible\": 1\n" +
                "\t},{\n" +
                "\t\t\"children\": [],\n" +
                "\t\t\"courseId\": 13,\n" +
                "\t\t\"id\": 434,\n" +
                "\t\t\"name\": \"Gityuan\",\n" +
                "\t\t\"order\": 190013,\n" +
                "\t\t\"parentChapterId\": 407,\n" +
                "\t\t\"userControlSetTop\": false,\n" +
                "\t\t\"visible\": 1\n" +
                "\t}],\n" +
                "\t\"errorCode\": 0,\n" +
                "\t\"errorMsg\": \"\"\n" +
                "}\n";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parse);
        JsonRootBean jsonRootBean = null;
        JsonParse jsonParse = new JsonParse();
        //使用 JSONObject 解析
        //jsonParse.getData(null,testStr);
        //jsonParse.getJsonBootBean(null,testStr0);

        //使用gson解析json
        jsonParse.getDataByGson(testStr);
        jsonRootBean = jsonParse.getJsonBootBeanByGson(testStr0);
        Log.d(TAG,"data = "+jsonRootBean.getData());

        GsonTest gsonTest = new GsonTest();
        gsonTest.BaseDataToGson();
    }

}