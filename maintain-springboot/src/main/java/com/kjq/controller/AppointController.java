package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.kjq.POJO.Appoint;
import com.kjq.service.AppointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class AppointController {

    @Autowired
    AppointService appointService;

    //查询预约列表
    @Secured({"ROLE_user"})
    @GetMapping("/getAppoints")
    public String getAppoints(
            @RequestParam("page") Integer page,
            @RequestParam("num") Integer num,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "skill", required = false) String skill,
            @RequestParam(value = "time", required = false) String time
    ){
        return JSONUtil.toJsonStr(appointService.getAppoints(page, num, name, city, skill, time));
    }

    @PutMapping("/updateStatus")
    public String updateStatus(@RequestBody Map<String, String> newAppoint){
        return JSONUtil.toJsonStr(appointService.updateStatus(newAppoint));
    }

    //获取总页数
    @GetMapping("/getTotal")
    public String getTotal(@RequestParam("status") Integer status){
        return JSONUtil.toJsonStr(appointService.getTotal(status));
    }

    //获取数据
    @GetMapping("/getStatusAppoint")
    public String getStatusAppoint(
            @RequestParam("page") Integer page,
            @RequestParam("num") Integer num,
            @RequestParam("status") Integer status
    ){
        return JSONUtil.toJsonStr(appointService.getListAppoint(page, num, status));
    }

    //取消预约
    @PutMapping("/cancelAppoint")
    public String cancelAppoint(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        return JSONUtil.toJsonStr(appointService.cancelAppoint(id));
    }

    //确认价格
    @PutMapping("/tPrice")
    public String tPrice(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        return JSONUtil.toJsonStr(appointService.tPrice(id));
    }

    //评分
    @PostMapping("/mark")
    public String mark(@RequestBody JSONObject jsonObject){
        Integer id = jsonObject.getInt("id");
        Integer rate = jsonObject.getInt("rate");
        return JSONUtil.toJsonStr(appointService.mark(id, rate));
    }

    //维修师查询预约信息
    @Secured({"ROLE_maintain"})
    @GetMapping("/maintain/getAppoints")
    public String getAppoints(
            @RequestParam("status") Integer status,
            @RequestParam("page") Integer page,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "title", required = false) String title
    ){
        return JSONUtil.toJsonStr(appointService.getMaintainAppoints(status, page, limit, title));
    }

    //删除预约消息
    @Secured({"ROLE_maintain"})
    @DeleteMapping("/maintain/appoint")
    public String deleteAppoint(@RequestParam Integer id){
        return JSONUtil.toJsonStr(appointService.deleteAppoint(id));
    }

    //修改数据
    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/appoint")
    public String updateAppoint(@RequestParam String content, @RequestParam String appointTime, @RequestParam Integer id){
        return JSONUtil.toJsonStr(appointService.updateAppoint(content, appointTime, id));
    }

    //修改数据
    @Secured({"ROLE_maintain"})
    @PostMapping("/maintain/appoint")
    public String addAppoint(@RequestParam String content, @RequestParam String appointTime){
        return JSONUtil.toJsonStr(appointService.addAppoint(content, appointTime));
    }

    //添加价格
    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/addPrice")
    public String addPrice(@RequestParam Integer price, @RequestParam Integer id){
        return JSONUtil.toJsonStr(appointService.addPrice(id, price));
    }

    //提交订单
    @Secured({"ROLE_maintain"})
    @PutMapping("/maintain/subAppoint")
    public String subAppoint(@RequestParam Integer id){
        return JSONUtil.toJsonStr(appointService.subAppoint(id));
    }
}
