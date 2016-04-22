package cn.com.octodata.auth.controller;

import cn.com.octodata.auth.model.AreaInfo;
import cn.com.octodata.auth.model.Extend;
import cn.com.octodata.auth.model.UserAuthInfo;
import cn.com.octodata.auth.service.AreaInfoService;
import cn.com.octodata.auth.service.BindingService;
import cn.com.octodata.auth.service.UserAuthInfoService;
import cn.com.octodata.auth.util.AuthType;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.MD5Util;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 认证服务控制类
 * Created by aran on 16-2-29.
 */

@RestController
@RequestMapping(value = "auth", produces = "application/json;charset=UTF-8")
public class AuthController {

    //接口的访问要记录日志：
    private static final Log LOG = LogFactory.getLog(AccessController.class);

    //路由器参数
    private static final String GW_ID = "gw_id";//路由器Id
    private static final String GW_ADDRESS = "gw_address";//路由器Ip
    private static final String GW_PORT = "gw_port";//wifidog端口号
    //用户参数
    private static final String USER_MAC = "mac";//用户Mac
    private static final String USER_URL = "url";//用户访问Url

    private static final int TEMPORARY_TIME = 30000;//临时放行时长（毫秒）

    private static final String WE_CHAT_ATTENTION_AUTH_URL = Config.getWifiAuthHost() + "/auth/pass";//微信回调的认证接口

    @Autowired
    BindingService bindingService;

    @Autowired
    AreaInfoService areaInfoService;

    @Autowired
    UserAuthInfoService userAuthInfoService;

    @RequestMapping("login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        System.out.println("----------CALL LOGIN----------");
        LOG.info("Call Login,");


        String gwId = request.getParameter(GW_ID);
        String userMac = request.getParameter(USER_MAC);
        String address = request.getParameter(GW_ADDRESS);
        String port = request.getParameter(GW_PORT);
        String url = request.getParameter(USER_URL);

        if (gwId == null || userMac == null || address == null || port == null || url == null) {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return;
        }
        String areaId = bindingService.get(gwId);
        if ("0".equals(areaId)) {
            request.getRequestDispatcher("/unbinding.jsp").forward(request, response);
            return;
        }
        UserAuthInfo userAuthInfo = userAuthInfoService.hGetUserAuthInfo(areaId, userMac);
        if (userAuthInfo != null && userAuthInfo.getOfflineTime() > System.currentTimeMillis()) {
            response.sendRedirect("http://" + address + ":" + port + "/wifidog/allow?url=" + url);
            System.out.println("----------ALLOW LOGIN----------");
            return;
        }
        AreaInfo areaInfo = areaInfoService.getAreaInfo(areaId);
        switch (areaInfo.getAuthType()) {
            case AuthType.AUTH_NONE:
                request.getRequestDispatcher("/default.jsp").forward(request, response);
                break;
            case AuthType.AUTH_FREE:
                response.sendRedirect("http://" + address + ":" + port + "/wifidog/allow?url=" + url);
                break;
            case AuthType.AUTH_ONE_KEY:
                request.setAttribute("areaId", areaId);
                request.setAttribute("title", areaInfo.getTitle());
                request.setAttribute("backgroundUrl", areaInfo.getBackgroundUrl());
                request.setAttribute("buttonUrl", areaInfo.getButtonUrl());
                request.setAttribute("successUrl", areaInfo.getSuccessUrl());
                request.getRequestDispatcher("/link.jsp").forward(request, response);
                break;
            case AuthType.AUTH_WE_CHAT_ATTENTION:
                String extendJsonString = JSON.toJSONString(new Extend(areaId, userMac));
                long timestamp = System.currentTimeMillis();
                String sign = MD5Util.getMD5(areaInfo.getAppId() + extendJsonString + timestamp + areaInfo.getShopId() + WE_CHAT_ATTENTION_AUTH_URL + userMac + areaInfo.getSecretKey());
                request.setAttribute("appId", areaInfo.getAppId());
                request.setAttribute("extend", extendJsonString);
                request.setAttribute("timestamp", timestamp);
                request.setAttribute("sign", sign);
                request.setAttribute("shop_id", areaInfo.getShopId());
                request.setAttribute("authUrl", WE_CHAT_ATTENTION_AUTH_URL);
                request.setAttribute("areaId", areaId);
                request.setAttribute("title", areaInfo.getTitle());
                request.setAttribute("backgroundUrl", areaInfo.getBackgroundUrl());
                request.setAttribute("buttonUrl", areaInfo.getButtonUrl());
                request.getRequestDispatcher("/portal.jsp").forward(request, response);
                System.out.println("----------AUTH_WE_CHAT_ATTENTION----------");
                break;
            default:
                request.getRequestDispatcher("/default.jsp").forward(request, response);
                break;
        }
    }

    @RequestMapping("click")
    public void click(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String areaId = request.getParameter("areaId");
        String userMAC = request.getParameter(USER_MAC);
        AreaInfo areaInfo = areaInfoService.getAreaInfo(areaId);
        UserAuthInfo userAuthInfo = userAuthInfoService.hGetUserAuthInfo(areaId, userMAC);
        if (userAuthInfo == null) {
            userAuthInfo = new UserAuthInfo();
        }
        userAuthInfo.setLoginTime(System.currentTimeMillis());
        switch (areaInfo.getAuthType()) {
            case AuthType.AUTH_NONE:
                break;
            case AuthType.AUTH_FREE:
                break;
            case AuthType.AUTH_ONE_KEY:
                userAuthInfo.setLoginTime(System.currentTimeMillis());
                userAuthInfo.setOfflineTime(userAuthInfo.getLoginTime() + areaInfo.getLimitTime());
                break;
            case AuthType.AUTH_WE_CHAT_ATTENTION:
                userAuthInfo.setOfflineTime(userAuthInfo.getLoginTime() + TEMPORARY_TIME);
                userAuthInfo.setBackupTime(userAuthInfo.getLoginTime() + areaInfo.getLimitTime());
                break;
            default:
                break;
        }
        userAuthInfoService.hSet(areaId, userMAC, userAuthInfo);
        response.setStatus(200);
    }


    @RequestMapping("pass")
    public void pass(HttpServletRequest request, HttpServletResponse response) {
        String extendJsonString = request.getParameter("extend");
        Extend extend = JSON.parseObject(extendJsonString, Extend.class);
        UserAuthInfo userAuthInfo = userAuthInfoService.hGetUserAuthInfo(extend.getAreaId(), extend.getUserMac());
        userAuthInfo.setOfflineTime(userAuthInfo.getBackupTime());
        userAuthInfoService.hSet(extend.getAreaId(), extend.getUserMac(), userAuthInfo);
        response.setStatus(200);
    }
}
