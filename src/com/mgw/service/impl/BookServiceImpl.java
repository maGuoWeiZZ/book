package com.mgw.service.impl;

import com.mgw.bean.Book;
import com.mgw.bean.Page;
import com.mgw.dao.impl.BookDAOImpl;
import com.mgw.service.BookService;
import com.mgw.utils.JDBCUtils;

import java.sql.Connection;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 16:45
 */
public class BookServiceImpl implements BookService {

    private BookDAOImpl dao = new BookDAOImpl();

    @Override
    public void addBook(Book book) {

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.addBook(conn, book);
        } finally {
            JDBCUtils.closeConnection(conn);
        }

    }

    @Override
    public void deleteBookById(Integer id) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteBookById(conn, id);
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public void updateBook(Book book) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.updateBook(conn, book);
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public Book queryBookById(Integer id) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Book book = dao.queryBookById(conn, id);
            return book;
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public List<Book> queryBooks() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Book> books = dao.queryBooks(conn);
            return books;
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public Page<Book> getPage(Integer pageNo, Integer pageSize) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            //当前页码和每页数量
            Page<Book> page = new Page<>();
            //总记录数
            page.setPageTotalCount(dao.queryForPageTotalCount(conn));
            Integer pageTotalCount = page.getPageTotalCount();
            //总页数
            Integer pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
            page.setPageTotal(pageTotal);

//            数据边界的有效性检查
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
            }
            page.setPageSize(pageSize);
            page.setPageTotal(pageTotal);
            page.setPageNo(pageNo);
            //每页内容(begin：（当前页码-1）*每页数量)
            List<Book> books = dao.queryForItems(conn, (pageNo - 1) * pageSize, pageSize);
            page.setItems(books);
            return page;
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Override
    public Page<Book> pageByPrice(Double minPrice, Double maxPrice, Integer pageNo, Integer pageSize) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            //当前页码和每页数量
            Page<Book> page = new Page<>();
            //总记录数
            page.setPageTotalCount(dao.queryPriceRangeCount(conn,minPrice,maxPrice));
            Integer pageTotalCount = page.getPageTotalCount();
            //总页数
            Integer pageTotal = pageTotalCount % pageSize == 0 ? pageTotalCount / pageSize : pageTotalCount / pageSize + 1;
            page.setPageTotal(pageTotal);

//            数据边界的有效性检查
            if (pageNo < 1) {
                pageNo = 1;
            }
            if (pageNo > pageTotal) {
                pageNo = pageTotal;
            }
            page.setPageSize(pageSize);
            page.setPageTotal(pageTotal);
            page.setPageNo(pageNo);
            //每页内容(begin：（当前页码-1）*每页数量)
            List<Book> books = dao.queryForPriceRange(conn, minPrice,maxPrice,(pageNo - 1) * pageSize, pageSize);
            page.setItems(books);
            return page;
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }


}
