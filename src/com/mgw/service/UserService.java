package com.mgw.service;

import com.mgw.bean.User;

/**
 * @author maguowei
 * @create 2021-08-07 21:20
 */
public interface UserService {

    /**
     * 注册用户
     *
     * @return void
     * @author maguowei
     * @date 2021/8/7 21:22
     * @params * @param user:
     */
    boolean registUser(User user);

    /**
     * 用户登录
     *
     * @return void
     * @author maguowei
     * @date 2021/8/7 21:22
     * @params
     */
    User login(String username,String password);

    /**
     * 判断用户名是否可用
     *
     * @return boolean 返回true表示用户名已存在不可用，返回false表示可用
     * @author maguowei
     * @date 2021/8/7 21:23
     * @params
     */
    boolean isExistUserName(String username);

}
