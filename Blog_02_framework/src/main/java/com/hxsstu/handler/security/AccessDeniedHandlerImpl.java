package com.hxsstu.handler.security;

import com.alibaba.fastjson.JSON;
import com.hxsstu.domain.ResponseResult;
import com.hxsstu.enums.AppHttpCodeEnum;
import com.hxsstu.utils.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ClassName: AccessDeniedHandlerImpl
 * Package: com.hxsstu.handler.security
 * Description:
 *
 * @Author HuangXuSen
 * @Create 2023/8/20-17:18
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NO_OPERATOR_AUTH);
        WebUtils.renderString(response, JSON.toJSONString(result));
    }
}
