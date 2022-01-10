package com.mgw.web;

import com.mgw.bean.Book;
import com.mgw.bean.Page;
import com.mgw.service.impl.BookServiceImpl;
import com.mgw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 17:07
 */
public class BookServlet extends BaseServlet {

    private BookServiceImpl service = new BookServiceImpl();

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取数据库图书列表
        List<Book> books = service.queryBooks();
        //将图书列表放进域中
        req.setAttribute("books", books);
        //跳转到列表页面由jsp获取列表并遍历输出
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;

        //将获取的参数map注入一个Book对象，并将该对象保存在数据库
        service.addBook(WebUtils.copyParamterToBean(req.getParameterMap(), new Book()));
//        req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req,resp);
        //使用重定向，防止重复提交添加
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);


    }

    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        service.deleteBookById(id);
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="  + req.getParameter("pageNo"));
    }

    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = WebUtils.copyParamterToBean(req.getParameterMap(), new Book());
        service.updateBook(book);
        //使用重定向，防止重复提交修改
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }


    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = service.queryBookById(id);
        req.setAttribute("book", book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);

    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = service.getPage(pageNo, pageSize);
        req.setAttribute("page",page);
        page.setUrl("manager/bookServlet?action=page");
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

    }


}
