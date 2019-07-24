<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/7/18
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<form action="user/login" method="post">
    <input type="text"      name="loginName" required maxlength="24" />
    <input type="password"  name="password" required maxlength="24" />
    <input type="submit"    value="登 录" />
    <br>
</form>

