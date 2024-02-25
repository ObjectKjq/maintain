package com.kjq.controller;

import cn.hutool.json.JSONUtil;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.service.UserService;
import com.kjq.utils.FFResult;
import com.kjq.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
