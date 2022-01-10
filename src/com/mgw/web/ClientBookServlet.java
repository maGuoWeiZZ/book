package com.mgw.web;

import com.mgw.bean.Book;
import com.mgw.bean.Page;
import com.mgw.service.impl.BookServiceImpl;
import com.mgw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author maguowei
 * @create 2021-08-10 14:48
 */
public class ClientBookServlet extends BaseServlet {

    private BookServiceImpl service = new BookServiceImpl();

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.getPage(pageNo, pageSize);
        req.setAttribute("page",page);
        page.setUrl("client/clientBookServlet?action=page");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);


    }

    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        double min = WebUtils.parseDouble(req.getParameter("min"),0);
        double max = WebUtils.parseDouble(req.getParameter("max"),Double.MAX_VALUE );
        Page<Book> page = service.pageByPrice(min, max, pageNo, pageSize);
        req.setAttribute("page",page);
        page.setUrl("client/clientBookServlet?action=pageByPrice");
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);

    }

}
