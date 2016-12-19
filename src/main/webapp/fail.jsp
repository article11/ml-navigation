<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/4
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<!DOCTYPE html>
<!-- saved from url=(0024)http://www.kanzhihu.com/ -->
<html lang="zh-CN"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<head>
    <meta charset="utf-8"/>
    <style>
        body {
            text-align: center
        }

        .div {
            margin: 0 auto;
            width: 400px;
            height: 100px;
            border: 1px solid #F00
        }

        /* css注释：为了观察效果设置宽度 边框 高度等样式 */
    </style>
</head>
<body>
<div>
    <header>
        <div><a href="<%=path%>/index.jsp"><img class="logo" width="451" height="115" src="static/images/logo5.png" alt="logo"
                                                style="max-width: 1900px;"></a><br></div>
    </header>
</div>
<div style="text-align:center">
    <div>
        <h2>没有权限 请联系公共技术部</h2>

        </h3>
    </div>
</div>
</body>
</html>
