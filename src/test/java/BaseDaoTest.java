import com.yyin.testfx.MainApplication;
import com.yyin.testfx.dao.UserDaoImpl;
import com.yyin.testfx.dao.in.UserDao;
import com.yyin.testfx.models.User;
import com.yyin.testfx.utils.ImageUtils;
import com.yyin.testfx.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 20:06 2021/12/4
 */
public class BaseDaoTest {
    @Test
    public void testJdbcConnection(){
        for (int i = 0; i < 100; i++){
            Connection connection = JdbcUtils.getConnection();
            System.out.println(connection);
            JdbcUtils.close(connection);
        }
    }

    @Test
    public void testUpdatePassword(){
        UserDao userDao = new UserDaoImpl();
        userDao.updateUserPasswordByEmail("1398035515@qq.com","098765");
    }
    @Test
    public void testSaveUser(){
        User yiin = new User(null,"yiin123","yyin12345678","12345678@qq.com", "C:\\Users\\13980\\IdeaProjects\\testFX\\src\\main\\resources\\com\\yyin\\testfx\\img\\alipay.png");
        UserDao userDao = new UserDaoImpl();
        userDao.saveUser(yiin);
    }
    @Test
    public void testLoadImageFromDB(){
        UserDao userDao =new UserDaoImpl();
        System.out.println();
        ImageUtils.binaryToImg(MainApplication.class.getResource("img/").toString()+"Al.png",userDao.queryUserImgByUserName("yiin").getUser_img());
    }
}
