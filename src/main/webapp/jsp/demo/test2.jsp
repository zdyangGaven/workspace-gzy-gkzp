<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<%-- 引入页面头 --%>
<%@ include file="/pageHead.jsp" %>

<form name="form1" id="form1" method="post">
    <div class=" row">
        <div class="pull-left col-md-2 col-md-offset-4" >雇员ID：</div>
        <div class="pull-left " ><input type="text"      name="id" required maxlength="24" />${id}</div>
    </div>
<div class=" row">
    <div class="pull-left col-md-2 col-md-offset-4" >雇员名称：</div>
    <div class="pull-left " ><input type="text"      name="name" required maxlength="24" /></div>
</div>
<div class=" row">
    <div class="pull-left  col-md-2 col-md-offset-4" >雇员地址：</div>
    <div class="pull-left " ><input type="text"  name="adress" required maxlength="24" /></div>
</div>
<div class="row">
    <div class="pull-left col-md-2 col-md-offset-4" >雇员年龄：</div>
    <div class="pull-left " ><input type="text"     name="age"    required maxlength="24" /></div>
</div>
<div class="row" >
    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" name="save"  >保存</button></div>
    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" name="getIdByName"  >getIdByName</button></div>

    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" name="select"  >查询</button></div>

</div>

</form>

<script  type="text/javascript">
    $(document).ready(function(){

        $("save").click(function () {
            alert("保存");

            $.ajax({
                type: 'POST',
                url: '${systemctx}/demo/save',
                data: $('#form1').serializeObject(),
                dataType: 'json',
                success: function (data) {
                   alert(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    try {

                    } catch (e) {}
                }
            });


        });

        $("select").click(function () {
            alert("查询");
            $("form").submit();
        });

    });


</script>


<%-- 引入页面尾 --%>
<%@ include file="/pageFloor.jsp" %>