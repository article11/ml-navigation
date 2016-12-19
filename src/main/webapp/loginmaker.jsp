<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/4
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC"-//W3C//DTD HTML4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <link rel="stylesheet" href="static/res/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/res/select2-3.3.1/select2.css">
    <script src="static/res/js/jquery-1.8.2.js"></script>
    <script src="static/res/select2-3.3.1/select2.min.js" type="text/javascript"></script>
    <script src="static/res/js/bootstrap.min.js"></script>
    <script>
        $(function () {
            getPlatformList();
        });


        var qid = "", sid = "", agent = "";
        function jumoczwithparam() {
            qid = encodeURIComponent($("#username").val());
            sid = $("#serverid").val();
            agent = $("#agent").val();
            gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/rechargemaker.jsp?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename;
            window.location.href = url;
        }


        function getloginurl() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/loginmaker?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename;
            $.get(url, function (data) {
                $("#loginurl").html(data);
                $("#loginhref").attr("href", data);
                var localurl = "http://127.0.0.1:8080/" + data.substring(data.indexOf("login_"));
//                var localurl = "http://127.0.0.1:8080/"+data.substring(data.indexOf("login_common"));
                $("#localurl").html(localurl);
                $("#localhref").attr("href", localurl);
            });
        }

        function getPlatformList() {
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/platform?gameid=" + gameid+ "&gamename=" + gamename;
            $.get(url, function (data) {
                $("#agent").html(data);
                $("#agent").select2({width: "element"});
            });
        }


    </script>
</head>
<body>
<h4><a href="<%=path%>/index.jsp"><span style="color:black;">回到</span> <span style="color:red;">墨龙首页</span></a></h4>

<form class="well" id="frm" name="" action="" method="POST">
    <input type="hidden" id="gameid" name="gameid" value="<%=request.getParameter("gameid")%>"/>
    <input type="hidden" id="gamename" name="gamename" value="<%=request.getParameter("gamename")%>"/>
    平台：<select name="agent" id="agent" class="span4">
    <option>正在加载...</option>
    </select>
    <br><br>
    区服：<input name="serverid" id="serverid"
              value="<%=request.getParameter("serverid")!=null ?request.getParameter("serverid"):1%>"/>
    <br><br>
    账号：<input name="username" id="username"
              value="<%=request.getParameter("username")!=null ?request.getParameter("username"):"test"%>"/>
    <br><br>
    <%--<input type="submit" value="生成" class="btn btn-primary"  onclick="getloginurl();"/>--%>
    <button type="button" class="btn btn-primary" onclick="getloginurl();">生成</button>
    <br><br>
    生成地址：(md5src:)<a name="loginhref" id="loginhref" href="#" target="_blank">可以直接点击此超链接跳转</a>
    <br>
    <textarea name="loginurl" id="loginurl" style="width: 800px; height: 100px;">生成地址 </textarea>
    <br><br>
    生成本地地址：<a name="localhref" id="localhref" href="#" target="_blank">超链接</a>
    <br>
    <textarea name="localurl" id="localurl" style="width: 800px; height: 100px;">本地生成地址 </textarea>
    <br><br>
    <a id="link" href="#" style="color:red;" onclick="jumoczwithparam();">充值地址生成页面>>></a>
</form>
</body>
</html>

