package com.mgw.dao.impl;

import com.mgw.bean.Book;

import java.sql.Connection;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 15:49
 */
public interface BookDAO {

    /**
     *  添加Book对象到数据库表
      * @author maguowei
      * @date 2021/8/9 15:55
      * @params * @param book:
      * @return int
    */
    int addBook(Connection conn,Book book);


    /**
     *  通过id删除图书信息
      * @author maguowei
      * @date 2021/8/9 15:55
      * @params * @param id:
      * @return int
    */
    int deleteBookById(Connection conn,Integer id);

    /**
     *  修改图书信息
      * @author maguowei
      * @date 2021/8/9 15:56
      * @params * @param book:
      * @return int
    */
    int updateBook(Connection conn,Book book);

    /**
     *  通过id查询一条图书信息
      * @author maguowei
      * @date 2021/8/9 15:57
      * @params * @param id:
      * @return com.mgw.bean.Book
    */
    Book queryBookById(Connection conn,Integer id);

    /**
     *  查询所有图书信息
      * @author maguowei
      * @date 2021/8/9 15:57
      * @params
      * @return java.util.List<com.mgw.bean.Book>
    */
    List<Book> queryBooks(Connection conn);

    /**
     *  返回总记录数
      * @author maguowei
      * @date 2021/8/9 22:09
      * @params * @param conn:
      * @return java.lang.Integer
    */
    Integer queryForPageTotalCount(Connection conn);


    /**
     *  返回当页数据集合
      * @author maguowei
      * @date 2021/8/9 22:10 
      * @params * @param conn: 
      * @param begin:
      * @param pageSize:
      * @return java.util.List<com.mgw.bean.Book>
    */
    List<Book> queryForItems(Connection conn,Integer begin,Integer pageSize);

    List<Book> queryForPriceRange(Connection conn,Double minPrice,Double maxPrice,Integer begin,Integer end);

    Integer queryPriceRangeCount(Connection conn,Double minPrice,Double maxPrice);

}
