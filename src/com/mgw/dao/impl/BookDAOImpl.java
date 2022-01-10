package com.mgw.dao.impl;

import com.mgw.bean.Book;
import com.mgw.dao.BaseDAO;

import java.sql.Connection;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 15:53
 */
public class BookDAOImpl extends BaseDAO implements BookDAO {

    @Override
    public int addBook(Connection conn, Book book) {
        String sql = "insert into t_book(name,price,author,sales,stock,img_path) values(?,?,?,?,?,?)";
        int colCount = update(conn, sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath());
        return colCount;
    }

    @Override
    public int deleteBookById(Connection conn,Integer id) {
        String sql = "delete from t_book where id = ?";
        int colCount = update(conn, sql, id);
        return colCount;
    }

    @Override
    public int updateBook(Connection conn,Book book) {
        String sql = "update t_book set name=?,price=?,author=?,sales=?,stock=?,img_path=? where id = ?";
        int colCount = update(conn, sql, book.getName(), book.getPrice(), book.getAuthor(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return colCount;
    }

    @Override
    public Book queryBookById(Connection conn,Integer id) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where id = ?";
        Book book = queryForOne(Book.class, conn, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks(Connection conn) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book";
        List<Book> books = queryForList(Book.class, conn, sql);
        return books;
    }

    @Override
    public Integer queryForPageTotalCount(Connection conn) {
        String sql = "select count(*) from t_book";
        Number pageTotalCount = (Number) queryValue(conn, sql);
        return pageTotalCount.intValue();
    }

    @Override
    public List<Book> queryForItems(Connection conn, Integer begin, Integer pageSize) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book limit ?,?";
        List<Book> books = queryForList(Book.class, conn, sql, begin, pageSize);
        return books;
    }

    @Override
    public List<Book> queryForPriceRange(Connection conn,Double minPrice,Double maxPrice,Integer begin,Integer end) {
        String sql = "select id,name,price,author,sales,stock,img_path from t_book where price between ? and ? order by price limit ?,?";
        List<Book> books = queryForList(Book.class, conn, sql, minPrice, maxPrice,begin,end);
        return books;
    }

    @Override
    public Integer queryPriceRangeCount(Connection conn, Double minPrice, Double maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number o = (Number)queryValue(conn, sql, minPrice, maxPrice);
        return o.intValue();
    }
}
