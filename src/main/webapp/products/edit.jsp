<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 25/05/2021
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Day la form sua san pham</h1>
<form method="post" action="/products?action=edit&id=${product.id}">
<%--    <input hidden name="id" value="${product.id}">--%>
    <input name="name" value="${product.name}">
    <input name="price" value="${product.price}">
    <select name="category_id">
    <c:forEach items="${categories}" var="c">
        <option value="${c.id}">${c.name}</option>
    </c:forEach>
    </select>
<%--    <input name="category_id" value="${product.category.id}">--%>
    <input type="submit" value="Edit">
</form>

</body>
</html>
