
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
//    @Test
//    public void testPersonQuery(){
//        PersonDao personDao = new PersonDaoImpl();
//
//        if (personDao.queryByPersonName("尹","智豪")==null){
//            System.out.println("用户名可用");
//        }else{
//            System.out.println("用户名已经存在");
//        }
//    }

}
