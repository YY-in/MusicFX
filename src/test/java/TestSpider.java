import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 18:47 2021/12/19
 */
public class TestSpider {
    @Test
    public void JDKget() throws Exception{
        String urlString ="https://api.paugram.com/netease/?id=1455101010";
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        InputStream in = connection.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = br.readLine();
        System.out.println(line);
        in.close();
        //解析json
        JSONObject object = JSONObject.parseObject(line);
        System.out.println("cover = "+object.get("cover"));
        String pretty = JSON.toJSONString(object, SerializerFeature.PrettyFormat,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);

        System.out.println(pretty);
    }
}
