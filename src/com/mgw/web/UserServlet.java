package com.mgw.web;

import com.mgw.bean.User;
import com.mgw.service.impl.UserServiceImpl;
import com.mgw.utils.WebUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


/**
 * @author maguowei
 * @create 2021-08-09 13:27
 */
public class UserServlet extends BaseServlet {

    private UserServiceImpl service = new UserServiceImpl();

    /**
     *  注册Servlet
      * @author maguowei
      * @date 2021/8/9 13:43
      * @params * @param req:
      * @param resp:
      * @return void
    */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单信息
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //获取验证码
        String codeKey = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //马上删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //判断验证码
        if (codeKey != null && codeKey.equalsIgnoreCase(code)) {
            //正确
            if (service.isExistUserName(username)) {
                //已存在
                System.out.println("用户名[" + username + "]已存在");
                //回显数据保存在域中
                req.setAttribute("message", "用户名已存在");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //可用
                //将map的值注入到对应的JavaBean中
                User user = WebUtils.copyParamterToBean(req.getParameterMap(), new User());
                service.registUser(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        }  else {
            //不正确
            //跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            //回显数据保存在域中
            req.setAttribute("message", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);//必须以/开头,默认web路径

        }

    }

    /**
     *  登录Servlet
      * @author maguowei
      * @date 2021/8/9 13:43
      * @params * @param req:
      * @param resp:
      * @return void
    */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = service.login(username, password);
        System.out.println(user);
        if (user != null) {
            //保存用户登录之后的信息
            req.getSession().setAttribute("user",user);
            System.out.println(user.getUsername());
            //存在用户登陆成功，跳转成功成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);

        } else {
            //不存在，登录失败，用户名或密码错误，跳回登录页面
            //回显数据
            req.setAttribute("username", username);
            req.setAttribute("message", "用户名或密码错误");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }

    }
    /**
     *  注销登录
      * @author maguowei
      * @date 2021/8/10 20:40
      * @params * @param req:
      * @param resp:
      * @return void
    */
    protected void destroy(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁session
        HttpSession session = req.getSession();
        session.invalidate();
        //重定向到主页
        resp.sendRedirect(req.getContextPath());
    }

}
