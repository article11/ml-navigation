<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/7
  Time: 14:23
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
    <script src="static/res/js/jquery.form.js"></script>
    <script>
        $(function () {
            getPlatformList();
        });

        function getloginurl() {
            var qid = encodeURIComponent($("#username").val());
            var sid = $("#serverid").val();
            var agent = $("#agent").val();
            var gameid = $("#gameId").val();
            var gamename = $("#gamename").val();
            var url = "<%=path%>/loginmaker?gameid=" + gameid + "&serverid=" + sid + "&agent=" + agent + "&username=" + qid + "&gamename=" + gamename;
            $.get(url, function (data) {
                $("#loginurl").html(data);
            });
        }


        function getagentkey() {
            var gameid = $("#gameId").val();
            var agent = $("#agent").val();
            var url = "<%=path%>/platform?gameid=" + gameid + "&agent=" + agent + "&agentkey=1";
            $.get(url, function (data) {
                $.each(data, function (i, item) {
                    if (i == 0) {
                        $("#loginKey").html(item);
                    }
                    else if (i == 1) {
                        $("#rechargeKey").html(item);
                    } else if (i == 2) {
                        $("#newsCardKey").html(item);
                    }
                });
            });
        }


        function getPlatformList() {
            var gameid = $("#gameId").val();
            var url = "<%=path%>/platform?gameid=" + gameid;
            $.get(url, function (data) {
                $("#agent").html(data);
                $("#agent").select2({width: "element"});
            });
        }


        function saveReport() {
            // jquery 表单提交
//            $('#frm').ajaxSubmit(ajax_option);
            $("#frm").ajaxSubmit(function (message) {
                // 对于表单提交成功后处理，message为提交页面saveReport.htm的返回内容
                alert(message);
            });
//            return false; // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转
        }

    </script>
</head>
<body>
<h4><a href="<%=path%>/index.jsp"><span style="color:black;">回到</span> <span style="color:red;">墨龙首页</span></a></h4>

<form class="well" id="frm" name="frm" action="<%=path%>/agentkeymaker" method="POST" onsubmit="return saveReport();">
    <input type="hidden" id="gameId" name="gameId" value="<%=request.getParameter("gameid")%>"/>
    <input type="hidden" id="gamename" name="gamename" value="<%=request.getParameter("gamename")%>"/>
    平台：
    <select name="agent" id="agent" class="span4"></select>
    <button type="button" class="btn btn-primary" onclick="getagentkey();">查询Key</button>
    <br><br>
    登录key：
    <b name="loginKey" id="loginKey"></b>
    <br><br>
    充值key：
    <b name="rechargeKey" id="rechargeKey"></b>
    <br><br>
    新手卡：
    <b name="newsCardKey" id="newsCardKey"></b>
    <br><br>
    <table>
        <tr>
        <tr>
            <td>平台标识：</td>
            <td><input name="platformName" id="platformName" class="span6" value=" "/></td>
        </tr>
        <tr>
            <td>平台名称：</td>
            <td><input name="platformDisplayName" id="platformDisplayName" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>内部登录key：</td>
            <td><input name="innerLoginKey" id="innerLoginKey" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>cdn地址：</td>
            <td><input name="cdn" id="cdn" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>充值地址：</td>
            <td><input name="rechargeUrl" id="rechargeUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>防沉迷地址：</td>
            <td><input name="fcmUrl" id="fcmUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>论坛地址：</td>
            <td><input name="bbsUrl" id="bbsUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>新手卡地址：</td>
            <td><input name="newUserCradUrl" id="newUserCradUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>gm地址：</td>
            <td><input name="gmUrl" id="gmUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>官网地址：</td>
            <td><input name="websiteUrl" id="websiteUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>客服地址：</td>
            <td><input name="kefuUrl" id="kefuUrl" class="span6" value=""/></td>
        </tr>
        <tr>
            <td>微端下载地址：</td>
            <td><input name="loginExeUrl" id="loginExeUrl" class="span6" value=""/></td>
        </tr>
    </table>
    是否关闭充值：
    <select name="isCloseRecharge" id="isCloseRecharge" class="span4">
        <option value="0">否</option>
        <option value="1">是</option>
    </select>
    <br>
    是否删除：
    <select name="isDeleted" id="isDeleted" class="span4">
        <option value="0">否</option>
        <option value="1">是</option>
    </select>
    <br><br>
    <%--<input type="submit" value="生成" class="btn btn-primary"  />--%>
    <button type="button" class="btn btn-primary" onclick="saveReport();">生成</button>
    <br><br>
    登陆ip白名单
    <br>
    <textarea name="loginAllowIp" id="loginAllowIp" id="loginurl" style="width: 800px; height: 100px;"></textarea>
    <br><br>
    充值ip白名单：
    <br>
    <textarea name="rechargeAllowIp" id="rechargeAllowIp" style="width: 800px; height: 100px;"></textarea>
    <br><br>
    登陆账号白名单：
    <br>
    <textarea name="loginAllowAccount" id="loginAllowAccount" style="width: 800px; height: 100px;"></textarea>
    <br><br>
    充值账号白名单：
    <br>
    <textarea name="rechargeAllowAccount" id="rechargeAllowAccount" style="width: 800px; height: 100px;"></textarea>
    <br><br>

</form>
</body>
</html>

