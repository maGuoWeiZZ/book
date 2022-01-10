package com.mgw.test;

import com.mgw.bean.Book;
import com.mgw.dao.impl.BookDAOImpl;
import com.mgw.utils.JDBCUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 16:16
 */
public class BookDAOTest {

    private BookDAOImpl dao = new BookDAOImpl();

    @Test
    public void addTest(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            int addBook = dao.addBook(conn, new Book(null,"恶意", new BigDecimal(59.9), "东野圭吾", 100, 900, null));
            if (addBook > 0) {
                System.out.println("添加成功");
            }else {
                System.out.println("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Test
    public void deleteTest(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            int deleteBook = dao.deleteBookById(conn,20);
            if (deleteBook > 0) {
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }

    }

    @Test
    public void updateTest(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            int updateBook = dao.updateBook(conn,new Book(21,"恶意", new BigDecimal(99.9), "东野圭吾", 100, 900, null));
            if (updateBook > 0) {
                System.out.println("修改成功");
            }else {
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }

    }

    @Test
    public void queryTest(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Book book = dao.queryBookById(conn, 21);
            if (book != null) {
                System.out.println(book);
            }else {
                System.out.println("不存在该图书");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }

    }

    @Test
    public void queryAllTest(){

        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Book> books = dao.queryBooks(conn);
            if (books != null) {
                books.forEach(System.out::println);
            }else {
                System.out.println("图书信息为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }

    }

    @Test
    public void queryForPageTotalCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Integer pageTotalCount = dao.queryForPageTotalCount(conn);
            System.out.println("pageTotalCount = " + pageTotalCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }

    @Test
    public void queryForItems() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Book> books = dao.queryForItems(conn, 0, 4);
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(conn);
        }
    }
}
