package com.mgw.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author maguowei
 * @create 2021-08-09 14:50
 */
public class WebUtils {

    /**
     *  将map的值注入到对应的JavaBean中
      * @author maguowei
      * @date 2021/8/9 15:12
      * @params * @param map:
      * @param bean:
      * @return void
    */
    public static <T> T copyParamterToBean(Map map, T bean){
        System.out.println("注入前" + bean);
        try {
            //注入map数据到对象
            BeanUtils.populate(bean,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("注入后" + bean);
        return bean;
    }

    /**
     *  字符串转int，转换成功则返回，失败返回defaultValue默认值
      * @author maguowei
      * @date 2021/8/9 19:15
      * @params * @param str:
 * @param defaultValue:
      * @return int
    */
    public static int parseInt(String str,int defaultValue){

        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     *  字符串转double，转换成功则返回，失败返回defaultValue默认值
      * @author maguowei
      * @date 2021/8/10 16:22
      * @params * @param str:
 * @param defaultValue:
      * @return double
    */
    public static double parseDouble(String str,double defaultValue){

        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
