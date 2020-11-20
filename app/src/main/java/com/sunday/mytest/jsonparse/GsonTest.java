package com.sunday.mytest.jsonparse;

import android.util.Log;

import com.google.gson.Gson;

public class GsonTest {
    private final static  String TAG = "GsonTest";
    public void BaseDataToGson(){
        Gson gson = new Gson();
        String gsonNumber = gson.toJson(10);
        String jsonStr = gson.toJson("testStr");
        Log.d(TAG,"jsonStr = "+jsonStr);
        //生成JSON
        Gson gson1 = new Gson();
        User user = new User("test",15,"shanghai");
        String userStr = gson1.toJson(user);
        Log.d(TAG,"userStr = "+userStr);

        //解析JSON
        Gson gson2 = new Gson();
        String userStrJson = " {\"age\":19,\"emailAddress\":\"xinjiang\",\"name\":\"test2\"}";
        User user1 = gson2.fromJson(userStrJson,User.class);
        Log.d(TAG,"user1.emailAdress = "+user1.emailAddress);

        /*
        * 解析如下数据:
        * {
            "code": "0000",
            "msg": "success",
            "data": {
                "qrcode": "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQAQAAAACoxAthAAACFElEQVR42u3bbW7DIAyAYW7g+9/SN2Ab4A9UFmmacP68rdSmDU/+WBBjSOt/fjUIBAKBQCAQCOSBaBsv+T6Sn/f4Hv+MYz8LKSYyP0fz0Xp8yWgtdhZSTzSdmuFbcZ0X8rOQN8j4XNGbqMX/kNfI7Eezq43W3YdByCvEoMV0BXP+9ThaQi6SlQzo+f2UXUAuEn/Nsc8iqNbJnrJxyD2y0ujIpb19ijCknszMwLOEmSSk25OcRkvIddJapAOaYxoXgtQT72N5njOjKymakGrSfbrjJRvrYzNL0M9UAXKdqEUxEoNo2iyikGpi/Wof7PxXBBZSS1Yrz6Stv/WcZEPKia78uUWdc92RtK3CwUf0IddJRMra9G3+k6ekkDqSJzl9z7FlmcNoCSkgUXxePSugzVQh5aR7Z5J90Vl8UDxk45D7JPqQR0/6vhQNqSexIcB3A0gqSafrQepIuhs12wQgmqegCnmBbKtpPS1Ax43psJQGuU3WwGe7M6I84MnDcekZcptYmmaTneZ9zn5/9EpIEVmxWkn0Vq6xWiikmmhasHHUY5vGeWkAcpf0fUaat2WqjYkKKScpYF53TpXp01QUUkEkD4C+ydxThCjcQGqJb2mOjcx++EuqAKkjkoo48WxGyiAgb5Hu+5r3WY9CXiCRP/t8tGt6wuy0RRNynexbzXO9QKKGA6kmPCkMgUAgEAgEAvk3+QI9/OsaeZ0+hwAAAABJRU5ErkJggg==",
                "expire": "600",
                "sid": "5b71491d-1f77-4fa9-be43-9f6ae31f3786"
            },
            "hcmData": null
        }
        * */

        String qrCodeStr = "{\n" +
                "\"code\": \"0000\",\n" +
                "\"msg\": \"success\",\n" +
                "\"data\": {\n" +
                "\"qrcode\": \"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQAQAAAACoxAthAAACFElEQVR42u3bbW7DIAyAYW7g+9/SN2Ab4A9UFmmacP68rdSmDU/+WBBjSOt/fjUIBAKBQCAQCOSBaBsv+T6Sn/f4Hv+MYz8LKSYyP0fz0Xp8yWgtdhZSTzSdmuFbcZ0X8rOQN8j4XNGbqMX/kNfI7Eezq43W3YdByCvEoMV0BXP+9ThaQi6SlQzo+f2UXUAuEn/Nsc8iqNbJnrJxyD2y0ujIpb19ijCknszMwLOEmSSk25OcRkvIddJapAOaYxoXgtQT72N5njOjKymakGrSfbrjJRvrYzNL0M9UAXKdqEUxEoNo2iyikGpi/Wof7PxXBBZSS1Yrz6Stv/WcZEPKia78uUWdc92RtK3CwUf0IddJRMra9G3+k6ekkDqSJzl9z7FlmcNoCSkgUXxePSugzVQh5aR7Z5J90Vl8UDxk45D7JPqQR0/6vhQNqSexIcB3A0gqSafrQepIuhs12wQgmqegCnmBbKtpPS1Ax43psJQGuU3WwGe7M6I84MnDcekZcptYmmaTneZ9zn5/9EpIEVmxWkn0Vq6xWiikmmhasHHUY5vGeWkAcpf0fUaat2WqjYkKKScpYF53TpXp01QUUkEkD4C+ydxThCjcQGqJb2mOjcx++EuqAKkjkoo48WxGyiAgb5Hu+5r3WY9CXiCRP/t8tGt6wuy0RRNynexbzXO9QKKGA6kmPCkMgUAgEAgEAvk3+QI9/OsaeZ0+hwAAAABJRU5ErkJggg==\",\n" +
                "\"expire\": \"600\",\n" +
                "\"sid\": \"5b71491d-1f77-4fa9-be43-9f6ae31f3786\"\n" +
                "},\n" +
                "\"hcmData\": null\n" +
                "}";
        Gson gson3 = new Gson();
        JsonRootBeanQrCode jsonRootBeanQrCode = gson3.fromJson(qrCodeStr,JsonRootBeanQrCode.class);
        Log.d(TAG,"jsonRootBeanQrCode.sid = "+jsonRootBeanQrCode.getData().getSid());
    }
    class User{
        public String name;
        public int age;
        public String emailAddress;

        User(String name,int age,String emailAddress){
            this.name = name;
            this.age = age;
            this.emailAddress = emailAddress;
        }
    }
}
