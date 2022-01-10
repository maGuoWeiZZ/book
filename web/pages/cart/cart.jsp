<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%--静态包含头文件--%>
    <%@include file="/pages/common/head.jsp" %>
    <script type="text/javascript">
        <%--删除确认--%>
        $(function () {
            $(".delItem").click(function () {
                return (confirm("是否删除【" + $(this).parent().parent().find("td:first").text() + "】"));
            });


            $("#clea").click(function () {
                return (confirm("确认清空购物车吗？"));
            });

            $(".updateItem").click(function () {
                var updateCount = $(this).parent().find("input:first").val();

                var updateId = $(this).parent().parent().find("td:last").text();
                location.href = "${basePath}client/cartServlet?action=update&updateCount="+updateCount+"&updateId="+updateId;
            });
        });


    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%--静态包含登陆成功页面--%>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">

    <c:if test="${not empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${sessionScope.cart.items}" var="entry">
                <tr>
                    <td>${entry.value.name}</td>
                    <td><input type="text" value="${entry.value.count}" style="width: 60px">
                        <input type="button" value="修改" class="updateItem"></td>
                    <td>${entry.value.price}</td>
                    <td>${entry.value.totalPrice}</td>
                    <td><a href="client/cartServlet?action=delete&id=${entry.key}" class="delItem">删除</a></td>
                    <td hidden>${entry.value.id}</td>
                </tr>
            </c:forEach>


        </table>
    </c:if>
    <c:if test="${empty sessionScope.cart.items}">
        <table>
            <tr>
                <td>商品名称</td>
                <td>数量</td>
                <td>单价</td>
                <td>金额</td>
                <td>操作</td>
            </tr>
            <td colspan="5" align="center"><a href="${basePath}" style="color: #666666">亲，您的购物车为空，快去看看喜欢的书吧！</a></td>
        </table>

    </c:if>


    <c:if test="${not empty sessionScope.cart.items}">
        <div class="cart_info">
        <span class="cart_span">购物车中共有<span class="b_count">
                ${not empty sessionScope.cart.totalCount?sessionScope.cart.totalCount:0}
        </span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">
                    ${not empty sessionScope.cart.totalPrice?sessionScope.cart.totalPrice:0}
            </span>元</span>
            <span class="cart_span"><a href="${basePath}client/cartServlet?action=clear" id="clea">清空购物车</a>


        </span>
            <span class="cart_span"><a href="pages/cart/checkout.jsp">去结账</a></span>

        </div>
    </c:if>


</div>


<%--静态包含页脚--%>
<%@include file="/pages/common/foot.jsp" %>
</body>
</html>