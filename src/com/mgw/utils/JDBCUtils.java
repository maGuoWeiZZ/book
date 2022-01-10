package com.mgw.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/** 数据库工具类
 * @author maguowei
 * @create 2021-08-07 19:16
 */


public class JDBCUtils {

    //数据库连接池
    private static DataSource dataSource;

    static{

        try {
            Properties properties = new Properties();
            //读取jdbc配置文件
            //重点，容易出错
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(properties);
//            System.out.println(dataSource.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     *  获取数据库连接
      * @author maguowei
      * @date 2021/8/7 19:17
      * @params
      * @return java.sql.Connection.如果返回null，说明获取失败
    */
    public static Connection getConnection(){

        try {
            Connection conn = dataSource.getConnection();
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     *  关闭数据库连接，放回数据库连接池
      * @author maguowei
      * @date 2021/8/7 19:18
      * @params * @param conn:
      * @return void
    */
    public static void closeConnection(Connection conn){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
