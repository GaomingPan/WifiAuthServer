package cn.com.octodata.auth.service.impl;

import cn.com.octodata.auth.model.AreaInfo;
import cn.com.octodata.auth.model.Extend;
import cn.com.octodata.auth.service.AuthService;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.MD5Util;
import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aran on 16-2-29.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final String WE_CHAT_ATTENTION_AUTH_URL = Config.getWifiAuthHost() + "/auth/pass";

    @Override
    public void authNone(HttpServletRequest request) {
        request.getRequestDispatcher("/default.jsp");
    }

    @Override
    public void authFree(HttpServletResponse response, String address, String port, String url) throws Exception {
        response.sendRedirect("http://" + address + ":" + port + "/wifidog/allow?url=" + url);

    }

    @Override
    public void authOneKey(HttpServletRequest request, HttpServletResponse response, AreaInfo areaInfo) throws Exception {
        request.setAttribute("areaId", areaInfo);
        request.setAttribute("title", areaInfo.getTitle());
        request.setAttribute("background", areaInfo.getBackgroundUrl());
        request.setAttribute("button", areaInfo.getButtonUrl());
        request.setAttribute("portalUrl", areaInfo.getSuccessUrl());
        request.getRequestDispatcher("/link.jsp").forward(request, response);
    }

    @Override
    public void authWeChatAttention(HttpServletRequest request, HttpServletResponse response, AreaInfo areaInfo, String areaId, String userMAC) throws Exception {
        String extendJsonString = JSON.toJSONString(new Extend(areaId, userMAC));
        long timestamp = System.currentTimeMillis();
        String sign = MD5Util.getMD5(areaInfo.getAppId() + +timestamp + areaInfo.getShopId() + WE_CHAT_ATTENTION_AUTH_URL + userMAC + areaInfo.getSecretKey());
        request.setAttribute("appId", areaInfo.getAppId());
        request.setAttribute("extend", extendJsonString);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("sign", sign);
        request.setAttribute("shop_id", areaInfo.getShopId());
        request.setAttribute("authUrl", WE_CHAT_ATTENTION_AUTH_URL);
        request.setAttribute("title", areaInfo.getTitle());
        request.setAttribute("background", areaInfo.getBackgroundUrl());
        request.setAttribute("button", areaInfo.getButtonUrl());
        request.getRequestDispatcher("/portal.jsp").forward(request, response);
    }

    @Override
    public void authDefault(HttpServletRequest request) {
        request.getRequestDispatcher("/default.jsp");
    }
}
