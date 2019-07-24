
<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<%-- 引入页面头 --%>
<%@ include file="/pageHead.jsp" %>
<li data-color="#1abc9c">
    <div>
        <img src="/images/001.png"/>
    </div>
</li>
<form action="user/register" method="post">
    <table>
        <tr>
            <td>登录名：</td>
            <td><input type="text"      name="loginName" required maxlength="24" /></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td> <input type="password"  name="password" required maxlength="24" /></td>
        </tr>
        <tr>
            <td>邮箱：</td>
            <td> <input type="text"     name="email"    required maxlength="24" /></td>
        </tr>
        <tr>
            <input type="submit"    value="注 册" />
        </tr>

    </table>
</form>
<%-- 引入页面尾 --%>
<%@ include file="/pageFloor.jsp" %>