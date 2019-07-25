<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<c:set var="systemctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="Cache-Control" content="no-store"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>

    <meta name="viewport" content="width=device-width, initial-scale=1"> <!--Bootstrap 针对移动设备。为了确保适当的绘制和触屏缩放，需要在 <head> 之中添加 viewport 元数据标签。-->
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"><!--Bootstrap 针对移动设备。以禁用其缩放（zooming）功能。这样禁用缩放功能后，用户只能滚动屏幕，就能让你的网站看上去更像原生应用的感觉。注意，这种方式我们并不推荐所有网站使用，还是要看你自己的情况而定！-->

<title>公开招聘系统</title>
    <link rel="icon" href="data:image/ico;base64,aWNv"><!--屏蔽favicon.ico请求-->
    <link href="${systemctx}/lib/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="${systemctx}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="${systemctx}/lib/bootstrap-3.3.7/js/bootstrap.js"></script>


<script type="text/javascript">

</script>
</head>



<body background-color:#fff leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div class="container-fluid">
    <div>
        <img  class="img-responsive center-block"  src="${systemctx}/images/001.png"/>
    </div>
