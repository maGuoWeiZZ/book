package com.mgw.dao.impl;

import com.mgw.bean.User;
import com.mgw.dao.BaseDAO;

import java.sql.Connection;

/**
 * @author maguowei
 * @create 2021-08-07 20:43
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {

    @Override
    public User queryUserByUserName(Connection conn,String name) {
        String sql = "select id,name username,password,email from t_user where name = ?";
        User user = queryForOne(User.class, conn, sql, name);
        return user;
    }

    @Override
    public User queryUserByUserNameAndPassWord(Connection conn,String name,String password) {
        String sql = "select id,name username,password,email from t_user where name = ? and password = ?";
        User user = queryForOne(User.class, conn, sql, name, password);
        return user;
    }

    @Override
    public int saveUser(Connection conn,User user) {
        String sql = "insert into t_user(name,password,email) values(?,?,?)";
        int colCount = update(conn, sql, user.getUsername(), user.getPassword(), user.getEmail());
        return colCount;
    }

}
