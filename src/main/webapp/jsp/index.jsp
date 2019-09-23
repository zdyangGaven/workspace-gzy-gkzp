<%@ page import="com.nsoft.gkzp.syscore.web.UserContext" %>
<%@ page import="javax.mail.Session" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 引入页面头 --%>
<%@ include file="/jsp/pageHead.jsp" %>



亲爱的${sysUser.loginName},欢迎登录系统。
<a href="${systemctx}/user/getUsers" >获得员工信息</a>

<div>
    <%=((UserContext)request.getSession().getAttribute("userContext")).getSysUser().getLoginName()%>
</div>



<%-- 引入页面尾 --%>
<%@ include file="/jsp/pageFloor.jsp" %>
