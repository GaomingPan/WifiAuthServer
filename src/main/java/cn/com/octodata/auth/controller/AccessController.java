package cn.com.octodata.auth.controller;

import cn.com.octodata.auth.dao.AreaDao;
import cn.com.octodata.auth.dao.BindingDao;
import cn.com.octodata.auth.dao.CommandDao;
import cn.com.octodata.auth.model.Output;
import cn.com.octodata.auth.model.PingData;
import cn.com.octodata.auth.service.AccessService;
import cn.com.octodata.auth.util.Config;
import cn.com.octodata.auth.util.HttpRequestUtil;
import com.alibaba.fastjson.JSON;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AC管理器控制类
 * Created by aran on 16-2-29.
 */
@RestController
@RequestMapping(value = "ac", produces = "application/json;charset=UTF-8")
public class AccessController {
    private static final String MQ_PING_DATA_URL = Config.getWifiMqHost() + "/v1/mq/ping";

    private static final Log LOG = LogFactory.getLog(AccessController.class);

    @Autowired
    HttpRequestUtil httpRequestUtil;
    @Autowired
    AccessService accessService;
    @Autowired
    BindingDao bindingDao;
    @Autowired
    AreaDao areaDao;
    @Autowired
    CommandDao commandDao;

    @RequestMapping("ping")
    public String ping(@RequestBody String pingDataJsonString) {
        try {
            PingData pingData = JSON.parseObject(pingDataJsonString, PingData.class);
            LOG.info(pingDataJsonString);
            pingData.setAreaId(bindingDao.get(pingData.getGw_id()));
            httpRequestUtil.sendPost(MQ_PING_DATA_URL, JSON.toJSONString(pingData));
            if (pingData.getWifidog_status().getClients().size() != 0) {
                String offlineClientCommandString = accessService.offlineClients(pingData.getGw_id(), pingData.getWifidog_status().getClients());
                if (offlineClientCommandString != null) {
                    LOG.info(offlineClientCommandString);
                    return offlineClientCommandString;
                }
            }
            String commandString = accessService.getCommandString(pingData.getGw_id());
            if (commandString != null) {
                LOG.info(commandString);
                return commandString;
            }
            return "Pong";
        } catch (Exception e) {
            LOG.warn(e);
            return "Pong";
        }
    }

    /**
     * 指令返回输出处理
     *
     * @param outputJsonString 返回的指令JsonString
     * @return 固定格式
     */
    @RequestMapping("output")
    public String output(@RequestBody String outputJsonString) {
        try {
            LOG.info(outputJsonString);
            Output output = JSON.parseObject(outputJsonString, Output.class);
            if (!output.isStatus()) {
                return "Success";
            }
            switch (output.getCmd_id()) {
                case 0://设备重启
                    break;
                case 1://同步设备参数设置
                    accessService.updateSync(output.getGw_ac_id(), output.getData());
                    break;
                case 2://更新无线参数设置
                    accessService.updateWireless(output.getGw_ac_id(), output.getData());
                    break;
                case 3://更新DHCP设置
                    accessService.updateDHCP(output.getGw_ac_id(), output.getData());
                    break;
                case 4://更新网络设置
                    accessService.updateNetwork(output.getGw_ac_id(), output.getData());
                    break;
                case 5://更新定时重启设置
                    accessService.updateRebootTime(output.getGw_ac_id(), output.getData());
                    break;
                case 6://更新黑白名单
                    break;
                default:
                    break;
            }
            return "Success";
        } catch (Exception e) {
            LOG.warn(e);
            return "Success";
        }
    }
}
