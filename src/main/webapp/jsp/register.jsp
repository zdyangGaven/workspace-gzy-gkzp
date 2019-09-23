<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<%-- 引入页面头 --%>
<%@ include file="/jsp/pageHead.jsp" %>

<form action="${systemctx}/user/register" method="post">
    <div class=" row">
        <div class=" col-md-2 col-md-offset-4" ><span id="qwq" style="color:red;font-weight:bold; " >${systemctx}</span></div>
    </div>
    <div class=" row">
        <div class=" col-md-2 col-md-offset-4" ><span id="photo" style="color:red;font-weight:bold; " >${msg}</span></div>
    </div>
        <div class=" row">
            <div class="pull-left col-md-2 col-md-offset-4" >登录名：</div>
            <div class="pull-left " ><input type="text"      name="loginName" required maxlength="24" /></div>
        </div>
        <div class=" row">
            <div class="pull-left  col-md-2 col-md-offset-4" >密码：</div>
            <div class="pull-left " ><input type="password"  name="password" required maxlength="24" /></div>
        </div>
        <div class="row">
            <div class="pull-left col-md-2 col-md-offset-4" >邮箱：</div>
            <div class="pull-left " ><input type="text"     name="email"     maxlength="24" /></div>
        </div>
        <div class="row" >
            <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" type="submit"  >注 册</button></div>

        </div>

</form>
<%-- 引入页面尾 --%>
<%@ include file="/jsp/pageFloor.jsp" %>