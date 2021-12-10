package com.yyin.testfx.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Author: YinZhihao
 * @Description:
 * @Date: Created in 19:39 2021/12/4
 */
public class JdbcUtils {
    //创建数据库连接池
    private static DruidDataSource dataSource;
    //创建连接线程
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties properties = new Properties();
            // 读取 jdbc.properties属性配置文件
            FileInputStream res = new FileInputStream("C:\\Users\\13980\\IdeaProjects\\testFX\\src\\main\\java\\com\\yyin\\testfx\\utils\\jdbc.properties");
            // 从流中加载数据
            properties.load(res);
            // 创建 数据库连接 池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }


    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null,说明获取连接失败
     *         有值就是获取连接成功
     */
    public static Connection getConnection(){

        Connection conn = null;

        try {
//            // 加载驱动
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            // 获取连接
//             String url = "jdbc:mysql://127.0.0.1:3306/apsys?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
//            String user = "root";
//            String password = "123527";
//            conn = DriverManager.getConnection(url, user, password);
            conn = dataSource.getConnection();
//            System.out.println("获取数据库连接成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.commit(); // 提交 事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();
    }
    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection connection = conns.get();
        if (connection != null) { // 如果不等于null，说明 之前使用过连接，操作过数据库
            try {
                connection.rollback();//回滚事务
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close(); // 关闭连接，资源资源
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        // 一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }
    /**
     * 关闭连接，放回数据库连接池
     * @param conn
     */
    public static void close(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
