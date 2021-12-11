import com.yyin.testfx.utils.KaptchaUtils;
import org.junit.Test;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:44 2021/12/11
 */
public class KaptchaTest {
    @Test
    public void testeGenerateImage(){
        KaptchaUtils.generateKaptchaImage();
    }
}
