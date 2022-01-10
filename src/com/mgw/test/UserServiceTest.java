package com.mgw.test;

import com.mgw.bean.User;
import com.mgw.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author maguowei
 * @create 2021-08-07 21:49
 */
public class UserServiceTest {
    UserServiceImpl service = new UserServiceImpl();
    @Test
    public void registUser() {


        User user = new User("马国伟", "456123", "320@qq.com");
        service.registUser(user);

    }

    @Test
    public void login() {

        User user = service.login("马国伟", "123456");

    }

    @Test
    public void isExistUserName() {

        boolean existUserName = service.isExistUserName("马国伟");
        System.out.println(existUserName);

    }
}