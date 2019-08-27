
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 引入页面头 --%>
<%@ include file="/pageHead.jsp" %>


亲爱的${sysUser.loginName},欢迎登录系统。
<a href="${systemctx}/user/getUsers" >获得员工信息</a>





<%-- 引入页面尾 --%>
<%@ include file="/pageFloor.jsp" %>
