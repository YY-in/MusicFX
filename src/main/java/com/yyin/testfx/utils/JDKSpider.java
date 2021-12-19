package com.yyin.testfx.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 18:44 2021/12/19
 */
public class JDKSpider {
    /** 对于网络发起get请求的api，以获取json格式的文件
     * @date: 22:56 2021/12/19
     * @param urlString  api接口
     * @return 经过解析但没有处理的JSON节点
     */
    public static JSONObject getJson(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream in = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        StringBuffer rawJson = new StringBuffer();
        while((line = br.readLine())!= null) {
            rawJson.append(line);
        }
        in.close();
        JSONObject object = JSONObject.parseObject(rawJson.toString());
        return object;
    }
    /** 格式化json文本
     * @date: 23:04 2021/12/19
     * @param jsonObject 经过解析但没有进行处理的json节点
     * @return pretty 格式化的Json文本
     */
    public static String getPrettyJsonString(JSONObject jsonObject){
        String pretty = JSON.toJSONString(jsonObject, SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        return pretty;
    }

}
