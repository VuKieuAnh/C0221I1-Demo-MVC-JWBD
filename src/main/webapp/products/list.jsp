<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 25/05/2021
  Time: 09:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Danh sach sp</title>
</head>
<body>
<h1>Danh sach sp</h1>
<table>
    <tr>
        <td>id</td>
        <td>name</td>
        <td>price</td>
        <td>action</td>
    </tr>
    <c:forEach items="${dssp}" var="p">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.price}</td>
            <td><a href="/products?action=edit&id=${p.id}">edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
