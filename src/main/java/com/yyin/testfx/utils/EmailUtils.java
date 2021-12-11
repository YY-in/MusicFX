package com.yyin.testfx.utils;

import org.apache.commons.mail.HtmlEmail;

import java.util.Random;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:25 2021/12/10
 */
public class EmailUtils {

   private static String email;

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        EmailUtils.email = email;
    }

    private static final String BASIC = "123456789qwertyuiopasdfghjklzxcvbnm";
   /**
    * @Description: 生成随机6位验证码
    * @Date: 20:35 2021/12/10
    */
   public static String generateVerificationCode(){
       char[] basicArray = BASIC.toCharArray();
       Random random = new Random();
       char[] result = new char[6];
       for (int i = 0; i < result.length; i++) {
           int index = random.nextInt(100) % (basicArray.length);
           result[i] = basicArray[index];
       }
       return new String(result);
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
