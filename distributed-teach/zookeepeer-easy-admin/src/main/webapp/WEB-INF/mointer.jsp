<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"/>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"/>
<html>
<html>
<head>
    <title>监控</title>
</head>
<body>


<body>
<h1><center>Zookeeper运维命令</center></h1>
<a class="btn" href="conf">conf</a>
<a class="btn" href="cons">cons</a>
<a class="btn" href="crst">crst</a>
<a class="btn" href="dump">dump</a>
<a class="btn" href="envi">envi</a>
<a class="btn" href="ruok">ruok</a>
<a class="btn" href="srst">srst</a>
<a class="btn" href="srvr">srvr</a>
<a class="btn" href="stat">stat</a>
<a class="btn" href="wchs">wchs</a>
<a class="btn" href="wchp">wchp</a>
<a class="btn" href="mntr">mntr</a>

<table class="table table-striped">
    <tr style="font-weight:bold">
        <td>zk1</td>
    </tr>

        <tr>
            <td class="warning">${zk1}</td>
        </tr>

</table>

</body>

</html>