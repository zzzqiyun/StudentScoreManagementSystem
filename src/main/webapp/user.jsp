<%@ page import="java.util.List" %>
<%@ page import="model.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: qiyunzhou
  Date: 2018/5/23
  Time: 09:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户显示页面</title>
</head>
<body>
<h1 align="center">显示所有的用户信息</h1>
<table  border="2" align="center">
    <tr>
        <th width="30%">用户id</th>
        <th width="30%">用户名</th>
        <th width="30%">用户类型</th>
    </tr>
    <% List<User> users = (List<User>) request.getAttribute("users");

        for (User user : users) {
    %>
    <tr>
        <td><%= user.getId()%></td>
        <td><%= user.getUserName()%></td>
        <td><%= user.getUserType()%></td>
    </tr>
    <%
        }
    %>
</table>



</body>
</html>
