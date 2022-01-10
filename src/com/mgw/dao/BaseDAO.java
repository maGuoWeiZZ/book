package com.mgw.dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-07 19:55
 */
public abstract class BaseDAO {

    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update()用来执行 insert、update、delete语句
     *
     * @param sql:
     * @param args:
     * @return int  返回值表示执行sql语句后影响的行数，返回-1表示执行失败
     * @author maguowei
     * @date 2021/8/7 20:03
     * @params * @param conn:
     */
    public int update(Connection conn, String sql, Object... args) {

        try {
            int colCount = queryRunner.update(conn,sql,args);
            return colCount;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;

    }

    /**
     * 查询返回一条记录
     *
     * @param conn:
     * @param sql:
     * @param args:
     * @return T 返回null表示查询失败
     * @author maguowei
     * @date 2021/8/7 20:15
     * @params * @param clazz:
     */
    public <T> T queryForOne(Class<T> clazz, Connection conn, String sql, Object... args) {


        try {
            BeanHandler<T> handler = new BeanHandler(clazz);
            T instance = queryRunner.query(conn, sql, handler, args);
            return instance;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    /**
     * 查询数据库表中的所有记录返回list
     *
     * @param conn:
     * @param sql:
     * @param args:
     * @return java.util.List<T> 返回null表示查询失败
     * @author maguowei
     * @date 2021/8/7 20:22
     * @params * @param clazz:
     */
    public <T> List<T> queryForList(Class<T> clazz, Connection conn, String sql, Object... args) {

        List<T> list = null;
        try {
            BeanListHandler<T> handler = new BeanListHandler(clazz);
            list = queryRunner.query(conn, sql, handler, args);
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 用于查询特殊值
     * @param sql:
     * @param args:
     * @return T 返回null表示查询失败
     * @author maguowei
     * @date 2021/8/7 20:30
     * @params * @param conn:
     */
    public Object queryValue(Connection conn, String sql, Object... args) {

        try {
            ScalarHandler handler = new ScalarHandler();
            Object value = queryRunner.query(conn, sql, handler, args);
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
