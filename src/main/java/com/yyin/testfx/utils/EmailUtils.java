package com.yyin.testfx.utils;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:25 2021/12/10
 */
public class EmailUtils {
   private static String emailPattern ="^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
   private static final String BASIC = "123456789qwertyuiopasdfghjklzxcvbnm";
   /**
    * @Description: 生成随机6位验证码
    * @Date: 20:35 2021/12/10
    */
   public static String generateVerificationCode(){
       char[] basicArray = BASIC.toCharArray();
       Random random = new Random(1024);
       char[] result = new char[6];
       for (int i = 0; i < result.length; i++) {
           int index = random.nextInt(100) % (basicArray.length);
           result[i] = basicArray[index];
       }
       return new String(result);
   }
   /**
    * @Description: 检查邮箱是否合法
    * @Date: 20:36 2021/12/10
    * @param email：输入的邮箱
    * @return： boolean
    */
   public static boolean checkEmail(String email){
       return Pattern.matches(emailPattern,email);
   }

    public static boolean sendEmail(String email,String sendHeader,String sendMessage){
        try {
            HtmlEmail emailSender = new HtmlEmail();
            emailSender.setHostName("smtp.qq.com");
            emailSender.setCharset("utf-8");
            emailSender.addTo(email);
            emailSender.setFrom("1398035515@qq.com", "yyin");
            emailSender.setAuthentication("1398035515@qq.com", "jtlagjyyajccghdd");
            emailSender.setSubject(sendHeader);
            emailSender.setMsg(sendMessage);
            emailSender.send();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

}
