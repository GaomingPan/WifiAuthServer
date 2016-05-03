package cn.com.octodata.auth.controller;

import cn.com.octodata.auth.model.Area;
import cn.com.octodata.auth.service.AreaService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 区域信息控制类
 * Created by aran on 16-2-25.
 */
@RestController
@RequestMapping(value = "area/{areaId}", produces = "application/json;charset=UTF-8")
public class AreaController {

    private static final Log LOG = LogFactory.getLog(AreaController.class);

    @Autowired
    AreaService areaService;

    @RequestMapping("add")
    public String add(@PathVariable String areaId) {

        LOG.info("AreaController.add(...), String areaId = " + areaId);

        return areaService.add(new Area(areaId));
    }

    @RequestMapping("del")
    public String del(@PathVariable String areaId) {
        LOG.info("AreaController.del(...), String areaId = " + areaId);
        return areaService.del(areaId);
    }

    @RequestMapping("set")
    public String set(@RequestBody String areaJsonString) {
        LOG.info("AreaController.set(...), String areaJsonString = " + areaJsonString);
        return areaService.set(areaJsonString);
    }

    @RequestMapping("get")
    public String get(@PathVariable String areaId) {
        LOG.info("AreaController.get(...), String areaId = " + areaId);
        return areaService.get(areaId);
    }

    @RequestMapping("set/info")
    public String setInfo(@PathVariable String areaId, @RequestBody String areaInfoJsonString) {
        LOG.info("AreaController.setInfo(...), String areaId = " + areaId + ", " +
                "String areaInfoJsonString = " + areaInfoJsonString);
//        System.out.println(areaInfoJsonString);
        return areaService.setInfo(areaId, areaInfoJsonString);
    }

    @RequestMapping("get/info")
    public String getInfo(@PathVariable String areaId) {
        LOG.info("AreaController.getInfo(...), String areaId = " + areaId);
        return areaService.getInfo(areaId);
    }

    @RequestMapping("set/list")
    public String setList(@PathVariable String areaId, @RequestBody String blackAndWhiteListJsonString) {

        LOG.info("AreaController.setList(...), String areaId = " + areaId + ", " +
                "String blackAndWhiteListJsonString = " + blackAndWhiteListJsonString);

        return areaService.setList(areaId, blackAndWhiteListJsonString);
    }

    @RequestMapping("get/list")
    public String getList(@PathVariable String areaId) {
        LOG.info("AreaController.getList(...), String areaId = " + areaId);
        return areaService.getList(areaId);
    }

    @RequestMapping("get/devicelist")
    public String getDeviceList(@PathVariable String areaId) {
        LOG.info("AreaController.getDeviceList(...), String areaId = " + areaId);
        return areaService.getDeviceList(areaId);
    }

    @RequestMapping("offline/{userMac}")
    public String offline(@PathVariable String areaId, @PathVariable String userMac) {

        LOG.info("AreaController.offline(...), String areaId = " + areaId + ", " +
                "String userMac = " + userMac);
        return areaService.offline(areaId, userMac);
    }
}
