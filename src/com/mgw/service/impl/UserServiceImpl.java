package com.mgw.service.impl;

import com.mgw.bean.User;
import com.mgw.dao.impl.UserDAOImpl;
import com.mgw.service.UserService;
import com.mgw.utils.JDBCUtils;

import java.sql.Connection;

/**
 * @author maguowei
 * @create 2021-08-07 21:25
 */
public class UserServiceImpl implements UserService {

    private UserDAOImpl dao = new UserDAOImpl();

    @Override
    public boolean registUser(User user) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.saveUser(conn, user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return false;
    }

    @Override
    public User login(String name, String password) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            User user = dao.queryUserByUserNameAndPassWord(conn, name, password);
            if (user != null) {
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return null;
    }

    @Override
    public boolean isExistUserName(String name) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            if (dao.queryUserByUserName(conn, name) == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
        return true;
    }

}
