package com.sunday.mytest.jsonparse;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParse {
    private final String TAG = "JsonParse";
    String testStr = "{\n" +
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
     /* 用JSONObject进行解析
      * */
     public  Data getData(String key,String jsonStr){
         Data data = new Data();
         try {
             JSONObject jsonObjectRoot = new JSONObject(jsonStr);
             Log.d(TAG,"jsonObjectRoot = "+jsonObjectRoot);

             JSONArray jsonArray = jsonObjectRoot.getJSONArray("children");
             Log.d(TAG,"jsonArray = "+jsonArray);
             if(jsonArray.length() == 0){
                 data.setChildren(null);
             }else {
                 for (int i = 0; i < jsonArray.length(); i++) {
                 }
             }
             data.setCourseId(jsonObjectRoot.getInt("courseId"));
             data.setId(jsonObjectRoot.getInt("id"));
             String  dataObj = jsonObjectRoot.getString("name");
             data.setName(dataObj);
             data.setOrder(jsonObjectRoot.getLong("order"));
             data.setParentChapterId(jsonObjectRoot.getInt("parentChapterId"));
             data.setUserControlSetTop(jsonObjectRoot.getBoolean("userControlSetTop"));
             data.setVisible(jsonObjectRoot.getInt("visible"));
             Log.d(TAG,"data = "+data);
         }catch (Exception e){
             e.printStackTrace();
         }
         return data;
     }
     /* 使用用JSONObject进行解析*/
     public JsonRootBean getJsonBootBean(String key,String jsonStr){
            JsonRootBean jsonRootBean = new JsonRootBean();
            List<Data> dataList = new ArrayList<>();
            try{
                JSONObject jsonRootBeanObject = new JSONObject(jsonStr);
                Log.d(TAG,"jsonRootBeanObject = "+jsonRootBeanObject);
                JSONArray jsonArray = jsonRootBeanObject.getJSONArray("data");
                Log.d(TAG,"jsonArray = "+jsonArray);
                for (int i = 0;i<jsonArray.length();i++){
                    Log.d(TAG,"jsonArray("+i+") = "+jsonArray.get(i));
                    dataList.add(getData(null,jsonArray.get(i).toString()));
                }
                jsonRootBean.setData(dataList);
            }catch (Exception e){
                e.printStackTrace();
            }
            Log.d(TAG,"jsonRootBean = "+jsonRootBean);
            return jsonRootBean;
     }
     //使用gson解析
    public  Data getDataByGson(String gsonStr){
         Data data = null;
         Gson gson = new Gson();
         data = gson.fromJson(gsonStr,Data.class);
         Log.d(TAG,"getDataByGson data = "+data);
         return  data;
    }
    public JsonRootBean getJsonBootBeanByGson(String gsonStr){
         JsonRootBean jsonRootBean = null;
         Gson gson = new Gson();
         jsonRootBean = gson.fromJson(gsonStr,JsonRootBean.class);
         Log.d(TAG,"getJsonBootBeanByGson  jsonRootBean= "+jsonRootBean);
         return jsonRootBean;
    }

}
