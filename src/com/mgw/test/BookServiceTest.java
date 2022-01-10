package com.mgw.test;


import com.mgw.bean.Book;
import com.mgw.bean.Page;
import com.mgw.dao.impl.BookDAOImpl;

import com.mgw.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;


/**
 * @author maguowei
 * @create 2021-08-09 16:51
 */
public class BookServiceTest {

    private BookServiceImpl service = new BookServiceImpl();

    @Test
    public void addTest(){

        service.addBook(new Book(null,"白夜行",new BigDecimal(79.9),"东野圭吾",500,500,null));

    }

    @Test
    public void deleteTest(){

        service.deleteBookById(14);

    }

    @Test
    public void updateTest(){

        service.updateBook(new Book(22,"白夜行",new BigDecimal(89.9),"东野圭吾",500,500,null));

    }

    @Test
    public void queryTest(){

        System.out.println(service.queryBookById(22));

    }

    @Test
    public void queryAllTest(){

        for (Book queryBook : service.queryBooks()) {
            System.out.println(queryBook);
        }

    }

    @Test
    public void getpage(){

        Page<Book> page = service.getPage(2, 4);
        System.out.println("总记录数" + page.getPageTotalCount());
        System.out.println("总页码" + page.getPageTotal());
        System.out.println("当前页码" + page.getPageNo());
        System.out.println("每页数量" + page.getPageSize());
        for (Book item : page.getItems()) {
            System.out.println(item);
        }


    }



}
