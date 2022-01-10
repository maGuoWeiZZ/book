package com.mgw.web;

import com.google.gson.Gson;
import com.mgw.bean.Cart;
import com.mgw.bean.CartItem;
import com.mgw.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author maguowei
 * @create 2021-08-10 22:39
 */
public class CartServlet extends BaseServlet {

    private static Cart cart = new Cart();

    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id书名和单价
        int bookId = WebUtils.parseInt(req.getParameter("bookId"), 0);
        String bookName = req.getParameter("bookName");
        BigDecimal bookPrice = new BigDecimal(Double.parseDouble(req.getParameter("bookPrice")));

//        String referer = req.getHeader("Referer");
        //创建商品对象
        CartItem item = new CartItem(bookId, bookName, 1, bookPrice.setScale(2,BigDecimal.ROUND_HALF_UP), bookPrice.setScale(2,BigDecimal.ROUND_HALF_UP));
        System.out.println(item);
        cart.addItem(item);
        System.out.println(cart);

        req.getSession().setAttribute("bookName", item.getName());
        //将购物车对象放进session域
        req.getSession().setAttribute("cart", cart);

        Gson gson = new Gson();
        Map<String, Object> map = new HashMap<>();
        map.put("itemCount", cart.getTotalCount());
        map.put("lastBook", bookName);
        resp.getWriter().write(gson.toJson(map));
        //重定向
//        resp.sendRedirect(referer);

    }

    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String referer = req.getHeader("Referer");
        if (cart != null) {
            cart.clear();
        }
        resp.sendRedirect(referer);


    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String referer = req.getHeader("Referer");
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        if (cart != null) {
            cart.deleteItem(id);
        }

        resp.sendRedirect(referer);

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String referer = req.getHeader("Referer");
        int updateCount = WebUtils.parseInt(req.getParameter("updateCount"), 1);
        int updateId = WebUtils.parseInt(req.getParameter("updateId"), 0);
        if (cart != null) {
            if (cart.getItems().containsKey(updateId)) {
                cart.updateCount(updateId, updateCount);
            }
        }
        resp.sendRedirect(referer);

    }
}
