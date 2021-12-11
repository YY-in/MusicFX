import com.yyin.testfx.dao.UserDaoImpl;
import com.yyin.testfx.dao.in.UserDao;
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
}
