package com.mgw.dao.impl;

import com.mgw.bean.User;

import java.sql.Connection;

/**
 * @author maguowei
 * @create 2021-08-07 20:33
 */
public interface UserDAO {


    /**
     * 根据userName查询用户信息
     *
     * @return com.mgw.bean.User 如果返回null，说明没有这个用户
     * @author maguowei
     * @date 2021/8/7 20:36
     * @params
     */
    User queryUserByUserName(Connection conn,String name);


    /**
     * 根据用户名和密码查询用户信息
     * @return com.mgw.bean.User 如果返回null，说明没有这个用户
     * @author maguowei
     * @date 2021/8/7 20:39
     * @params
     */
    User queryUserByUserNameAndPassWord(Connection conn,String name,String password);

    /**
     * 保存用户信息
     *
     * @return int
     * @author maguowei
     * @date 2021/8/7 20:38
     * @params * @param user:
     */
    int saveUser(Connection conn,User user);

}
