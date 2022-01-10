package com.mgw.service;

import com.mgw.bean.Book;
import com.mgw.bean.Page;

import java.sql.Connection;
import java.util.List;

/**
 * @author maguowei
 * @create 2021-08-09 16:42
 */
public interface BookService {

    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> getPage(Integer pageNo,Integer pageSize);

    Page<Book> pageByPrice(Double minPrice, Double maxPrice,Integer pageNo,Integer pageSize);

}
