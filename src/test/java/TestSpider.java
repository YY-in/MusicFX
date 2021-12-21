import com.alibaba.fastjson.JSONObject;
import com.yyin.testfx.mediaplayer.Config;
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
        System.out.println(object.get("cover").toString());
        String preety = JDKSpider.getPrettyJsonString(object);
        System.out.println(preety);
    }
    @Test
    public void GetTotalTime() throws LineUnavailableException, IOException, UnsupportedAudioFileException, BitstreamException {
        int time =SongUtils.getSongPlayTimeByWeb(1455101010);
        System.out.println(time);
    }
    @Test
    public void testConfig() throws IOException {
        Config config = new Config();
        System.out.println(config.getSongURL(2131213));
        System.out.println(config.getSingerURL(11972054));
        System.out.println(config.getAlbumURL(35046112));
        System.out.println(config.getUserURL(246437214));
        System.out.println(config.getGroupURL(307039766));
        System.out.println(config.getSongDetailURL(123342,4353456,34575647,5465467));
    }

    @Test
    public void testSongUtils() throws IOException, BitstreamException {
        SongUtils.createPlayListSong(1370359829);
    }
}
