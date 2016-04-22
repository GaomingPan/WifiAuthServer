<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String gw_address = request.getParameter("gw_address");
    String gw_port = request.getParameter("gw_port");
    String gw_id = request.getParameter("gw_id");
    String mac = request.getParameter("mac");

    String successUrl = (String) request.getAttribute("successUrl");

    String areaId = (String) request.getAttribute("areaId");
    String title = (String) request.getAttribute("title");
    String backgroundUrl = (String) request.getAttribute("backgroundUrl");
    String buttonUrl = (String) request.getAttribute("buttonUrl");

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>

<head>

    <base href="<%=basePath%>">

    <title><%=title%>
    </title>

    <link rel="shortcut icon" href="images/head.png" type="image/png">

    <style type="text/css">
        body {
            margin: 0;
            overflow: hidden;
            background-position: center;
            background-size: auto 100%;
        }

        #content {
            position: absolute;
            left: 0;
            top: 55%;
            width: 100%;
            height: 24%;
            text-align: center;
        }

        #button {
            width: auto;
            height: 100%;
            display: none;
        }

    </style>


</head>

<body>

<div id="content">
    <img id="button" src='' onclick="auth()"/>
</div>

<iframe id=success_id width="0" height="0">

</iframe>

</body>

<script type="text/javascript" src="js/jquery-2.2.0.min.js">

</script>

<script type="text/javascript">

    setTimeout("$('body').css('background-image', 'url(<%=backgroundUrl%>)')", 500);//延时0.5秒

    setTimeout("document.getElementById('button').src = '<%=buttonUrl%>';", 500);//延时0.5秒

    $("body").height(document.body.scrollHeight);

    $("#button").css("display", "inline-block");

    var url = "http://" + "<%=gw_address%>" + ":" + "<%=gw_port%>" + "/wifidog/allow?url=" + "<%=successUrl%>";

    function auth() {
        $.ajax({
            url: "auth/click",
            data: {
                areaId: "<%=areaId%>",
                mac: "<%=mac%>"
            },
            success: function () {
                window.location.href = url
            },
            error: function () {
                alert("认证服务器异常");
            }
        });
    }
</script>

</html>
