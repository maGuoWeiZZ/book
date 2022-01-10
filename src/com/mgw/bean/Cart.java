package com.mgw.bean;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author maguowei
 * @create 2021-08-10 22:11
 */
public class Cart {

    private int totalCount;//总商品数量
    private BigDecimal totalPrice = new BigDecimal(0);//总商品金额
    private Map<Integer,CartItem> items = new LinkedHashMap<>();//购物车商品


    public Cart() {
    }

    /**
     * 添加商品到购物车
     *
     * @return void
     * @author maguowei
     * @date 2021/8/10 22:29
     * @params * @param item:
     */
    public void addItem(CartItem item) {

        //判断购物车是否已经存在该商品，若存在数量累加，金额更新
        if (items.containsKey(item.getId())) {
            //已存在，先找到原来的商品，进行数量和价格累加
            CartItem itemSrc = items.get(item.getId());
            itemSrc.setCount(itemSrc.getCount() + item.getCount());
            itemSrc.setTotalPrice(itemSrc.getPrice().multiply(new BigDecimal(itemSrc.getCount())));
            this.totalCount += item.getCount();
            this.totalPrice = this.totalPrice.add(item.getTotalPrice());
        } else {
            //不存在，说明该商品第一次添加到购物车
            items.put(item.getId(), item);
            //总商品数量 += 添加的商品数
            this.totalCount += item.getCount();
            //总商品金额 += 添加的商品总价
            this.totalPrice = this.totalPrice.add(item.getTotalPrice());
        }
    }

    /**
     * 根据id删除购物车里的商品
     *
     * @return void
     * @author maguowei
     * @date 2021/8/10 22:35
     * @params * @param id:
     */
    public void deleteItem(Integer id) {

        CartItem item = items.remove(id);
        if (item != null) {
            this.totalCount -= item.getCount();
            this.totalPrice = this.totalPrice.subtract(item.getTotalPrice());
        }

    }

    /**
     * 清空购物车
     *
     * @return void
     * @author maguowei
     * @date 2021/8/10 22:36
     * @params
     */
    public void clear() {

        this.items.clear();
        this.totalPrice = new BigDecimal(0);
        this.totalCount = 0;

    }

    /**
     * 修改商品数量
     *
     * @param count:
     * @return void
     * @author maguowei
     * @date 2021/8/10 22:39
     * @params * @param id:
     */
    public void updateCount(Integer id, Integer count) {

        if (count <= 0) {
            count = 1;
        }

        int changeCount = 0;

        if(items.containsKey(id)){
            //存在
            CartItem itemSrc = items.get(id);
            //改变数量后与之前的差值，正数为减少，负数为增加
            changeCount = itemSrc.getCount() - count;
            itemSrc.setCount(count);
            itemSrc.setTotalPrice(itemSrc.getPrice().multiply(new BigDecimal(itemSrc.getCount())));
            this.totalCount += -changeCount;
            this.totalPrice = this.totalPrice.add(itemSrc.getPrice().multiply(new BigDecimal(-changeCount)));
        }



    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + totalCount +
                ", totalPrice=" + totalPrice.toString() +
                ", items=" + items +
                '}';
    }
}
