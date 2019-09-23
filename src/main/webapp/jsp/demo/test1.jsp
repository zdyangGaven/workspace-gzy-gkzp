<%@ page  language="java" contentType="text/html;charset=UTF-8" %>
<%-- 引入页面头 --%>
<%@ include file="/pageHead.jsp" %>

<form name="form1" id="form1" method="post">

<div class=" row">
    <div class="pull-left col-md-2 col-md-offset-4" >雇员名称：</div>
    <div class="pull-left " ><input type="text"      id="name" name ="name" required maxlength="24" value="楠楠"/></div>
</div>
<div class=" row">
    <div class="pull-left  col-md-2 col-md-offset-4" >雇员地址：</div>
    <div class="pull-left " ><input type="text"  name="adress"  maxlength="24" /></div>
</div>
<div class="row">
    <div class="pull-left col-md-2 col-md-offset-4" >雇员年龄：</div>
    <div class="pull-left " ><input type="text"     name="age"    required maxlength="24"  value="12"/></div>
</div>
<div class="row" >
    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" id="save"  >save</button></div>
    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" id="getIdByName"  >getIdByName</button></div>
    <div class=" col-md-offset-4 col-md-3 "><button class="btn btn-primary btn-lg btn-block" id="select"  >查询</button></div>

</div>

</form>

<script  type="text/javascript">
    $(document).ready(function() {
        $('#save').click(function () {
            alert("保存");

            $.ajax({
                async : false,//默认为true 异步
                cache : false,
                type: 'POST',
                url: '${systemctx}/demo/save.json',
                data: $('#form1').serialize(),
                dataType: 'json',//接收回传的数据格式   //
                success : function(data) {
                    alert(data.messageType);
                    alert(data.message);
                    //var data2=eval('('+data+')');//这段代码就是将字符串转换成json格式

                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {

                    alert(XMLHttpRequest.status);//返回状态码
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                    alert(errorThrown);
                }
            });


        });


        $('#getIdByName').click(function () {
            $("#name").val("张三");
            $.ajax({
                async : false,//默认为true 异步
                cache : false,
                type: 'POST',
                url: '${systemctx}/demo/getIdByName',
                data: $('#form1').serialize(),
                dataType: 'html',
                success: function (data) {
                   // alert(data);
                    //var newWin = window.open('', '_blank');
                    //newWin.document.write(data);
                    window.document.write(data);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(textStatus);

                }
            });


        });


        $('#select').click(function () {
            $.ajax({
                async : false,//默认为true 异步
                cache : false,
                type: 'POST',
                url: '${systemctx}/demo/getIdByName',
                data: $('#form1').serialize(),
                dataType: 'html',
                success: function (data) {
                    // alert(data);
                    //var newWin = window.open('', '_blank');
                    //newWin.document.write(data);
                    window.document.write(data);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(textStatus);

                }
            });
        });
    });

/**参考： https://www.cnblogs.com/liaojie970/p/5481437.html
ajax的dataType：
预期服务器返回的数据类型。如果不指定，jQuery 将自动根据 HTTP 包 MIME 信息来智能判断，比如XML MIME类型就被识别为XML。
在1.4中，JSON就会生成一个JavaScript对象，而script则会执行这个脚本。随后服务器端返回的数据会根据这个值解析后，传递给回调函数。
可用值:
"xml"   : 返回 XML 文档，可用 jQuery 处理。
"html"  : 返回纯文本 HTML 信息；包含的script标签会在插入dom时执行。
"script": 返回纯文本 JavaScript 代码。不会自动缓存结果。除非设置了"cache"参数。'''注意：'''在远程请求时(不在同一个域下)，所有POST请求都将转为GET请求。(因为将使用DOM的script标签来加载)
"json"  : 返回 JSON 数据 。
"jsonp" : JSONP 格式。使用 JSONP 形式调用函数时，如 "myurl?callback=?" jQuery 将自动替换 ? 为正确的函数名，以执行回调函数。
"text"  : 返回纯文本字符串
 */


</script>


<%-- 引入页面尾 --%>
<%@ include file="/pageFloor.jsp" %>