package com.kjq.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
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
}
