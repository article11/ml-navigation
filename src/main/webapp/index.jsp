<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/7
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9" />
    <title>墨龙导航[2013-10-22]</title>
    <link href="static/css/common.css" rel="stylesheet">
    <link href="static/css/style.css" rel="stylesheet">
    <script type="text/javascript" src="static/js/jquery-1.4.2.min.js" ></script>
    <script src="static/js/layer/layer.js"></script>
</head>
<body>
<div id="top" class="cssshadow1">
    <img src="static/images/moloong.png">
    <img src="static/images/logo5.png">
</div>
<div class="main">

    <div id="14" class="gameico cssshadow2">
        <img src="static/images/lhfs.jpg">
        <span>烈火封神</span>
    </div>

    <%--<div id="15" class="gameico cssshadow2">--%>
        <%--<img src="static/images/njws.png">--%>
        <%--<span>怒剑无双</span>--%>
    <%--</div>--%>

    <div id="24" class="gameico cssshadow2">
        <img src="static/images/rxfs.jpg">
        <span>热血封神</span>
    </div>

    <div id="28" class="gameico cssshadow2">
        <img src="static/images/lwtz.jpg">
        <span>龙纹天尊</span>
    </div>

</div>

</div>
<div class="main">
    <div id="backstage" class="gameico cssshadow2">
        <a href="http://gccp.moloong.com/">
            <img src="static/images/backstage_ico.png">
            <span>后台入口</span>
        </a>
    </div>

</div>
<script type="text/javascript">
    $(function() {
        //更改class
        $(".main").children("div").mouseover(function(){
            $(this).attr("class","gameico cssshadow1");
        })
        $(".main").children("div").mouseout(function(){
            $(this).attr("class","gameico cssshadow2");
        })

        //根据获取id变换url链接
        $(".main").children("div").click(function(){
            var ids =  $(this).attr("id");
            var agent = "";
            var title="";
            var other="";
            var backstage="";
            if( ids == "14"){
                title="烈火封神";
                var gamename = "lhfs";
//                backstage = '<li style="border:none"><a id="u4" href="http://qmrht.moloong.com/QMRBackend/login.jsp" target="_blank">秦美人国内</a></li>';
//                backstage +='<li style="border:none"><a id="u4" href="http://211.237.12.103:8080/QMRBackend/login.jsp" target="_blank">秦美人韩国</a></li>';
            }
            if( ids == "15"){
                title="怒剑无双";
                var gamename = "njws";
//                backstage = '<li style="border:none"><a id="u4" href="http://qmrht.moloong.com/QMRBackend/login.jsp" target="_blank">秦美人国内</a></li>';
//                backstage +='<li style="border:none"><a id="u4" href="http://211.237.12.103:8080/QMRBackend/login.jsp" target="_blank">秦美人韩国</a></li>';
            }

            if( ids == "24"){
                title="热血封神";
                var gamename = "rxfs";
//                backstage = '<li style="border:none"><a id="u4" href="http://qmrht.moloong.com/QMRBackend/login.jsp" target="_blank">秦美人国内</a></li>';
//                backstage +='<li style="border:none"><a id="u4" href="http://211.237.12.103:8080/QMRBackend/login.jsp" target="_blank">秦美人韩国</a></li>';
            }

            if( ids == "28"){
                title="龙纹天尊封神";
                var gamename = "lwtz";
            }

            var content=document.getElementById("new_window");

            var content  = '<div id="menu">';
            content += '<ul class="menu_ul1">';
            content += '<li style="margin-bottom:20px;">';
            content += '<span><font style="font-size:16px;font-weight:bolder;">链接生成:</font></span>';
            content += '<ul class="chil_menu">';
//            content += '<li><a id="u1" href="/loginmaker.jsp?gameid='+ids+'" target="_blank">登录链接生成</a></li>';
            content += '<li><a id="u1" href="<%=path%>/loginmaker.jsp?gameid='+ids+'&gamename='+gamename+'" target="_blank">登录链接生成</a></li>';
            content += '<li><a id="u2" href="<%=path%>/rechargemaker.jsp?gameid='+ids+'&gamename='+gamename+'" target="_blank">充值链接生成</a></li>';
            content += '<li><a id="u3" href="<%=path%>/agentkeymaker.jsp?gameid='+ids+'&gamename='+gamename+'" target="_blank">登录充值key生成</a></li>';
//            content += '<li style="border:none"><a id="u4" href="/mediacard.jsp?gameid='+ids+'" target="_blank">媒体卡生成</a></li>';
            content += other;
            content += '</ul>';
            content += '</li>';
            content += '<li><hr style="width:100%;color:gray;" /></li>';
            content += '<li style="margin-top:20px;">';
            content += '<span><font style="font-size:16px;font-weight:bolder;">后台管理:</font></span>';
            content += '<ul class="chil_menu">';
            content += backstage;
            content += '</ul>';
            content += '</li>';
            content += '</ul>';
            content += '</div>';

            if(ids != "backstage"){
                show_window(title,content);
            }

        })

        //弹出框
        function show_window(title,content){
            layer.open({
                type: 1, //page层
                area: ['500px', '300px'],
                title: title,
                shade: 0.6, //遮罩透明度
                moveType: 1, //拖拽风格，0是默认，1是传统拖动
                shift: 1, //0-6的动画形式，-1不开启
                content: content
            });
        }

    })

</script>
</body>
</html>
