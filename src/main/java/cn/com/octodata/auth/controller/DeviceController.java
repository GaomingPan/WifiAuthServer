package cn.com.octodata.auth.controller;

import cn.com.octodata.auth.model.Device;
import cn.com.octodata.auth.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设备信息控制类
 * Created by aran on 16-2-25.
 */

@RestController
@RequestMapping(value = "device/{deviceId}", produces = "application/json;charset=UTF-8")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    /**
     * 添加设备
     *
     * @param deviceId 添加的设备Id
     * @return 执行结果
     */
    @RequestMapping("add")
    public String add(@PathVariable String deviceId) {
        return deviceService.add(new Device(deviceId));
    }

    /**
     * 删除设备
     *
     * @param deviceId 删除的设备Id
     * @return 执行结果
     */
    @RequestMapping("del")
    public String del(@PathVariable String deviceId) {
        return deviceService.del(deviceId);
    }

    /**
     * 设备参数设置
     *
     * @param deviceJsonString 设备的Json字符串
     * @return 执行结果
     */
    @RequestMapping("set")
    public String set(@RequestBody String deviceJsonString) {
        return deviceService.set(deviceJsonString);
    }

    /**
     * 获取设备信息
     *
     * @param deviceId 获取的设备Id
     * @return 执行结果以及获取到的设备信息Json字符串
     */
    @RequestMapping("get")
    public String get(@PathVariable String deviceId) {
        return deviceService.get(deviceId);
    }

    /**
     * 设备绑定
     *
     * @param deviceId 绑定的设备Id
     * @param areaId   绑定的区域Id
     * @return 执行结果
     */
    @RequestMapping("set/binding/{areaId}")
    public String setBindingAreaId(@PathVariable String deviceId, @PathVariable String areaId) {
        return deviceService.setBindingAreaId(deviceId, areaId);
    }

    /**
     * 设备解绑
     *
     * @param deviceId 解绑的设备Id
     * @return 执行结果
     */
    @RequestMapping("set/unbinding")
    public String setUnbindingAreaId(@PathVariable String deviceId) {
        return deviceService.setUnbindingAreaId(deviceId);
    }

    /**
     * 设备的参数设置
     *
     * @param deviceId                设置的设备Id
     * @param deviceSettingJsonString 设置的参数Json字符串
     * @return 执行结果
     */
    @RequestMapping("set/settings")
    public String setSettings(@PathVariable String deviceId, @RequestBody String deviceSettingJsonString) {
        System.out.println(deviceSettingJsonString);
        return deviceService.setSettings(deviceId, deviceSettingJsonString);
    }

    /**
     * 获取设备设置的参数
     *
     * @param deviceId 获取参数的设备Id
     * @return 执行结果以及获取到的设备参数
     */
    @RequestMapping("get/settings")
    public String getSettings(@PathVariable String deviceId) {
        return deviceService.getSettings(deviceId);
    }

    /**
     * 同步设备设置的参数
     *
     * @param deviceId 同步参数的设备Id
     * @return 执行结果
     */
    @RequestMapping("sync")
    public String sync(@PathVariable String deviceId) {
        return deviceService.sync(deviceId);
    }

    /**
     * 设备重启
     *
     * @param deviceId 重启的设备Id
     * @return 执行结果
     */
    @RequestMapping("reboot")
    public String reboot(@PathVariable String deviceId) {
        return deviceService.reboot(deviceId);
    }
}
