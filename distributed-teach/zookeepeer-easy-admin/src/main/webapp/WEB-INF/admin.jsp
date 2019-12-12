<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<html>
<html>
<head>
    <title>SpringMVC</title>
</head>
<body>


<body>
<h1><center>Zk服务注册中心后台管理</center></h1>
<table class="table table-striped">
    <tr style="font-weight:bold">
        <td>服务名</td>
        <td>ip</td>
        <td>端口</td>
        <td>状态</td>
        <td>客服端</td>
        <td>节点</td>
    </tr>
    <c:forEach items="${servetList}"  var="server">
        <tr>
            <td class="warning">${server.serverName}</td>
            <td  class="success">${server.ip}</td>
            <td class="danger">${server.port}</td>
            <td class="warning">${server.status}</td>
            <td class="danger">${server.clientName}</td>
            <td class="success">${server.node}</td>

        </tr>

    </c:forEach>
</table>

</body>

</html>