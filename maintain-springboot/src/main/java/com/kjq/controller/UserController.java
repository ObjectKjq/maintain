package com.kjq.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.service.UserService;
import com.kjq.utils.FFResult;
import com.kjq.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @GetMapping("/info")
    public String info(@RequestParam("token") String token){
        Claims claims = jwtUtils.getClaimsByToken(token);
        return JSONUtil.toJsonStr(userService.getUserByAccount((String) claims.get("sub")));
    }

    @PostMapping("/signin")
    public String signin(HttpServletRequest req, @RequestBody JSONObject jsonObject){
        //获取用户的openid表示用户的账号，张三表示默认用户名
        String code = jsonObject.getStr("code");
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid", "wx0098d5972a06b2de");  //开发者设置中的appId
        requestUrlParam.put("secret", "c69f0d59d4f11fec8c92831dd2ebfc9c"); //开发者设置中的appSecret
        requestUrlParam.put("js_code", code); //小程序调用wx.login返回的code
        requestUrlParam.put("grant_type", "authorization_code");    //默认参数 authorization_code
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        String param = "";

        for (String key : requestUrlParam.keySet()) {
            param += key + "=" + requestUrlParam.get(key) + "&";
        }

        try {
            URL realUrl = new URL(requestUrl);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Map map = JSON.parseObject(result.toString(), Map.class);

        //实现登录返回token
        return JSONUtil.toJsonStr(userService.getToken(req, (String)map.get("openid")));
    }

    @PostMapping("/files")
    public String files(@RequestParam("file") MultipartFile file){
        return JSONUtil.toJsonStr(userService.upload(file));
    }

    @PutMapping("/updateUser")
    public String updateUser(@RequestBody JSONObject jsonObject){
        String name = jsonObject.getStr("name");
        String sex = jsonObject.getStr("sex");
        return JSONUtil.toJsonStr(userService.updateUser(name, sex));
    }

    @Secured({"ROLE_admin"})
    @GetMapping("/admin/getUsers")
    public String getUsers(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit){
        return JSONUtil.toJsonStr(userService.getAdminUsers(page, limit));
    }

    @Secured({"ROLE_maintain", "ROLE_admin"})
    @PostMapping("/upload/avatar")
    public String upload(@RequestParam("file") MultipartFile file){
        System.out.println(file.getOriginalFilename());
        return JSONUtil.toJsonStr(userService.addUpload(file));
    }

    @Secured({"ROLE_maintain", "ROLE_admin"})
    @PutMapping("/updateMaintainAdminUser")
    public String updateMaintainAdminUser(@RequestBody JSONObject jsonObject){
        String username = jsonObject.getStr("username");
        String password = jsonObject.getStr("password");
        String nPassword = jsonObject.getStr("nPassword");
        String vPassword = jsonObject.getStr("vPassword");
        return JSONUtil.toJsonStr(userService.updateMaintainAdminUser(username, password, nPassword, vPassword));
    }
}
