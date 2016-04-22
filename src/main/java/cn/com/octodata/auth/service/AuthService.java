package cn.com.octodata.auth.service;

import cn.com.octodata.auth.model.AreaInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by aran on 16-2-29.
 */
public interface AuthService {
    void authNone(HttpServletRequest request);

    void authFree(HttpServletResponse response, String address, String port, String url) throws Exception;

    void authOneKey(HttpServletRequest request, HttpServletResponse response, AreaInfo areaInfo) throws Exception;

    void authWeChatAttention(HttpServletRequest request, HttpServletResponse response, AreaInfo areaInfo, String areaId, String userMAC) throws Exception;

    void authDefault(HttpServletRequest request);
}
