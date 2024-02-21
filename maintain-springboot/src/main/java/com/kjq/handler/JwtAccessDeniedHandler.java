package com.kjq.handler;

import cn.hutool.json.JSONUtil;
import com.kjq.enums.StatusCodeEnum;
import com.kjq.utils.FFResult;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//用户没有权限访问处理器
@Component
public class JwtAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        FFResult<Object> result = FFResult.error(StatusCodeEnum.JURISDICTION_ERROR);
        outputStream.write(JSONUtil.toJsonStr(result).getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
