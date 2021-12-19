import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Header;
import org.junit.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

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
    @Test
    public void GetTotalTime() throws LineUnavailableException, IOException, UnsupportedAudioFileException, BitstreamException {
        URL url = new URL("http://music.163.com/song/media/outer/url?id=1455101010.mp3");
        URLConnection con = url.openConnection();
        int b = con.getContentLength();// 得到音乐文件的总长度

        BufferedInputStream bis = new BufferedInputStream(con.getInputStream());

        Bitstream bt = new Bitstream(bis);

        Header h = bt.readFrame();

        int time = (int) h.total_ms(b);

        System.out.println(time / 1000);


    }
}
