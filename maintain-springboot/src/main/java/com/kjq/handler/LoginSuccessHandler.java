package com.kjq.handler;

import cn.hutool.json.JSONUtil;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.POJO.User;
import com.kjq.service.UserService;
import com.kjq.utils.FFResult;
import com.kjq.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

//登录认证成功的处理器
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();


        // 生成JWT，并放置到请求头中
        Map<String, String> token = new HashMap<>();
        String jwt = jwtUtils.generateToken(authentication.getName());
        token.put("token", jwt);
        //httpServletResponse.setHeader(jwtUtils.getHeader(), jwt);


        //封装user数据
        User user = userService.getByUsername(authentication.getName());
        user.setUserPassword("");
        FFResult<Map> result = FFResult.success(StatusCodeEnum.SUCCESS, token);

        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
