<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String gw_address = request.getParameter("gw_address");
    String gw_port = request.getParameter("gw_port");
    String mac = request.getParameter("mac");

    String appId = (String) request.getAttribute("appId");
    String extend = (String) request.getAttribute("extend");
    Object timestamp = request.getAttribute("timestamp");
    String sign = (String) request.getAttribute("sign");
    String shop_id = (String) request.getAttribute("shop_id");
    String authUrl = (String) request.getAttribute("authUrl");
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

    <script type="text/javascript">
        var loadIframe = null;
        var noResponse = null;
        function createIframe() {
            var iframe = document.createElement("iframe");
            iframe.style.cssText = "display:none;width:0px;height:0px;";
            document.body.appendChild(iframe);
            loadIframe = iframe;
        }
        function jsonpCallback(result) {
            if (result && result.success) {
                var ua = navigator.userAgent;
                if (ua.indexOf("iPhone") != -1 || ua.indexOf("iPod") != -1 || ua.indexOf("iPad") != -1) {
                    document.location = result.data;
                } else {
                    createIframe();
                    loadIframe.src = result.data;
                    noResponse = setTimeout(function () {
                        errorJump();
                    }, 3000);
                }
            } else if (result && !result.success) {
                alert(result.data);
            }
        }
        function Wechat_GotoRedirect(appId, extend, timestamp, sign, shopId, authUrl, mac, ssid, bssid) {
            var url = "https://wifi.weixin.qq.com/operator/callWechatBrowser.xhtml?appId=" + appId + "&extend=" + extend + "&timestamp=" + timestamp + "&sign=" + sign;
            if (authUrl && shopId) {
                url = "https://wifi.weixin.qq.com/operator/callWechat.xhtml?appId=" + appId + "&extend=" + extend + "&timestamp=" + timestamp + "&sign=" + sign + "&shopId=" + shopId + "&authUrl=" + encodeURIComponent(authUrl) + "&mac=" + mac + "&ssid=" + ssid + "&bssid=" + bssid;
            }
            var script = document.createElement('script');
            script.setAttribute('src', url);
            document.getElementsByTagName('head')[0].appendChild(script);
        }
    </script>

</head>

<body>

<div id="content">
    <img id="button" src='' onclick="auth()">
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

    function auth() {
        $.ajax({
            url: "auth/click",
            data: {
                areaId: "<%=areaId%>",
                mac: "<%=mac%>"
            },
            success: function () {
                release()
            },
            error: function () {
                alert("认证服务器异常!稍后重试！");
            }
        });
    }

    function release() {
        var url = "http://" + "<%=gw_address%>" + ":" + "<%=gw_port%>" + "/wifidog/release";
        $.ajax({
            url: url,
            dataType: "jsonp",
            error: function (XMLHttpRequest) {
                if (XMLHttpRequest.status == 200) {
                    document.getElementById("success_id").src = "http://captive.apple.com/";
                    Wechat_GotoRedirect('<%=appId%>', '<%=extend%>', '<%=timestamp%>', '<%=sign%>', '<%=shop_id%>', '<%=authUrl%>', '<%=mac%>', "");
                   /* alert("Call WeChat!")*/
                } else {
                    alert("路由器异常!稍后重试！");
                }
            }
        });
    }

</script>

</html>
