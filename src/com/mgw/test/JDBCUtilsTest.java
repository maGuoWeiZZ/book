package com.mgw.test;

import com.mgw.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author maguowei
 * @create 2021-08-07 19:49
 */
public class JDBCUtilsTest {


    @Test
    public void test() {

        for (int i = 0; i < 100; i++) {
            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
            JDBCUtils.closeConnection(conn);
        }

    }

}
