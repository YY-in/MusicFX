import com.alibaba.fastjson.JSONObject;
import com.yyin.testfx.utils.JDKSpider;
import com.yyin.testfx.utils.SongUtils;
import javazoom.jl.decoder.BitstreamException;
import org.junit.Test;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 18:47 2021/12/19
 */
public class TestSpider {
    @Test
    public void JDKget() throws Exception{
        String urlString ="https://api.paugram.com/netease/?id=1455101010";
        JSONObject object= JDKSpider.getJson(urlString);
        String preety = JDKSpider.getPrettyJsonString(object);
        System.out.println(preety);
    }
    @Test
    public void GetTotalTime() throws LineUnavailableException, IOException, UnsupportedAudioFileException, BitstreamException {
        int time =SongUtils.getSongPlayTimeByWeb("http://music.163.com/song/media/outer/url?id=1455101010.mp3");
        System.out.println(time);
    }
}
