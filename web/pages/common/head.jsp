<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/8/8
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--动态获取协议、ip、端口号、工程路径--%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
    pageContext.setAttribute("basePath",basePath);
%>
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css"/>
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
