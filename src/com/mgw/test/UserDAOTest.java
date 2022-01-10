package com.mgw.test;

import com.mgw.bean.User;
import com.mgw.dao.impl.UserDAOImpl;
import com.mgw.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

import static org.junit.Assert.*;

/**
 * @author maguowei
 * @create 2021-08-07 20:58
 */
public class UserDAOTest {

    @Test
    public void queryUserByUserName() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            UserDAOImpl dao = new UserDAOImpl();
            User user = dao.queryUserByUserName(conn, "马国伟");
            if (user != null) {
                System.out.println(user);
            }else{
                System.out.println("该用户不存在");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBCUtils.closeConnection(conn);
        }
    }

    @Test
    public void queryUserByUserNameAndPassWord() {

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            UserDAOImpl dao = new UserDAOImpl();
            User user = dao.queryUserByUserNameAndPassWord(conn, "马国伟","123456");
            if (user != null) {
                System.out.println(user);
            }else{
                System.out.println("该用户不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            JDBCUtils.closeConnection(conn);
        }

    }

    @Test
    public void saveUser() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            UserDAOImpl dao = new UserDAOImpl();
            User user = new User("马国伟", "123456", "3202222@qq.com");
            int i = dao.saveUser(conn, user);
            if (i > 0) {
                System.out.println("添加成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }
}