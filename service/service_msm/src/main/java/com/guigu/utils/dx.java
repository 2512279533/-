package com.guigu.utils;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;

public class dx {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
        void dx(String phone){
            String host = "https://dfsns.market.alicloudapi.com";
            String path = "/data/send_sms";
            String method = "POST";
            String appcode = "b635ff0a90924a13a18bc9e10f074a05";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:2473");
        bodys.put("phone_number", phone);
        bodys.put("template_id", "TPL_0000");


        try {
            HttpResponse response = dx2.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());

            //将phone:code存入redis
          redisTemplate.opsForValue().set(phone,"2473");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
