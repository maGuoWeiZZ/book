package com.mgw.web;

import com.google.gson.Gson;
import com.mgw.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author maguowei
 * @create 2021-08-12 13:01
 */


public class AjaxServlet extends BaseServlet {

    public static UserServiceImpl service = new UserServiceImpl();
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(service.isExistUserName(username)){

            Gson gson = new Gson();
            String json = gson.toJson("用户名已存在！");
            resp.getWriter().write(json);

        }
    }
}
