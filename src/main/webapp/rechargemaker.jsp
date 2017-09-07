<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/7
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
            var url = "<%=path%>/loginmaker.jsp?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename;
            window.location.href = url;
        }


        function getrechargeurl() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var roleid = $("#roleid").val();
            var url = "<%=path%>/rechargemaker?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename + "&roleid=" + roleid+"&test=formal";
            $.get(url, function (data) {
                $("#rechargurl").html(data);
                $("#recharghref").attr("href",data);
                var localurl = "http://127.0.0.1:8080/" + data.substring(data.indexOf("recharge_"));
                if("rxfs"==gamename){
                    var localurl = "http://127.0.0.1:8080/interface-rxfs/" + data.substring(data.indexOf("recharge"));
                }

//                var localurl = "http://127.0.0.1:8080/"+data.substring(data.indexOf("login_common"));
                $("#localurl").html(localurl);
                $("#localhref").attr("href", localurl);
            });
        }

        function getrechargeurltest() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var roleid = $("#roleid").val();
            var url = "<%=path%>/rechargemaker?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename + "&roleid=" + roleid+"&test=test";
            $.get(url, function (data) {
                $("#rechargurl").html(data);
                $("#recharghref").attr("href",data);
                var localurl = "http://127.0.0.1:8080/" + data.substring(data.indexOf("recharge_"));
                if("rxfs"==gamename){
                    var localurl = "http://127.0.0.1:8080/interface-rxfs/" + data.substring(data.indexOf("recharge"));
                }
//                var localurl = "http://127.0.0.1:8080/"+data.substring(data.indexOf("login_common"));
                $("#localurl").html(localurl);
                $("#localhref").attr("href", localurl);
            });
        }


        function getroleid() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/userqueryServlet?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename+"&test=formal";
            $.get(url, function (data) {
                $.each(data, function (i, item) {
                    if (i == 0) {
                        $("#userquery").html(item);
                        $("#userqueryhref").attr("href",item);
                    }
                    else if (i == 1) {
                        $("#roleid").html(item);
                    }
                });
            });
        }

        function getroleidtest() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameid").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/userqueryServlet?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename+"&test=test";
            $.get(url, function (data) {
                $.each(data, function (i, item) {
                    if (i == 0) {
                        $("#userquery").html(item);
                        $("#userqueryhref").attr("href",item);
                    }
                    else if (i == 1) {
                        $("#roleid").html(item);
                    }
                });
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

<form class="well" id="" name="" action="" method="POST">
    <input type="hidden" id="gameid" name="gameid" value="<%=request.getParameter("gameid")%>"/>
    <input type="hidden" id="gamename" name="gamename" value="<%=request.getParameter("gamename")%>"/>
    平台：<select name="agent" id="agent" class="span4"></select>
    <br><br>
    区服：<input name="serverid" id="serverid"
              value="<%=request.getParameter("serverid")!=null ?request.getParameter("serverid"):1%>"/> -1 版署1服 -2
    内网测试1服 37内部服 -10</font>
    <br><br>
    账号：<input name="username" id="username"
              value="<%=request.getParameter("username")!=null ?request.getParameter("username"):"test"%>"/>
    <br><br>

    <% if(request.getParameter("gameid").equals("14")){ %>
    <span style="color:red;">请选择角色</span>
    <select name="roleid" id="roleid" >
        <option value="111111" selected="selected">请检查角色</option>
    </select>
    <button type="button" class="btn btn-primary" onclick="getroleid();">检查角色</button>
    <button type="button" class="btn btn-primary" onclick="getroleidtest();">测试服检查角色</button>
    <br><br>
    <%}%>
    <%--<input type="submit" value="生成" class="btn btn-primary" />--%>
    <button type="button" class="btn btn-primary" onclick="getrechargeurl(),getroleid();">生成</button>
    <button type="button" class="btn btn-primary" onclick="getrechargeurltest(),getroleidtest();">测试服生成</button>
    <br><br>
    角色查询地址：<a name="userqueryhref" id="userqueryhref" href="#" target="_blank">可以直接点击此超链接跳转</a>
    <br>
    <textarea name="userquery" id="userquery" style="width: 800px; height: 100px;"></textarea>
    <br><br>



    充值地址：<a name="recharghref" id="recharghref" href="#" target="_blank">可以直接点击此超链接跳转</a>
    <br>
    <textarea name="rechargurl" id="rechargurl" style="width: 800px; height: 100px;"></textarea>
    <br><br>
    生成本地地址：<a name="localhref" id="localhref" href="#" target="_blank">超链接</a>
    <br>
    <textarea name="localurl" id="localurl" style="width: 800px; height: 100px;">本地生成地址 </textarea>
    <br><br>
    <%--新手卡KEY：<?php echo $newcardkey; ?>--%>
    <%--<br>--%>
    <%--新手卡：<input name="newcord" id="newcord" class="span5" value="<?php echo $newcardnum; ?>"/>--%>
    <%--<br><br>--%>
    <a id="link" href="#" style="color:red;" onclick="jumoczwithparam();">登陆链接生成>>></a>

</form>
</body>
</html>
