package com.mgw.bean;

import java.math.BigDecimal;

/**
 * @author maguowei
 * @create 2021-08-10 22:14
 */
public class CartItem {

    private Integer id;
    private String name;
    private Integer count;//数量
    private BigDecimal price = new BigDecimal(0);//单价
    private BigDecimal totalPrice = new BigDecimal(0);//总价

    public CartItem() {

    }

    public CartItem(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price.toString() +
                ", totalPrice=" + totalPrice.toString() +
                '}';
    }
}
