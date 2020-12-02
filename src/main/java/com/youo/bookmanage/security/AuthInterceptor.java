package com.youo.bookmanage.security;

import cn.hutool.core.util.ArrayUtil;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.youo.bookmanage.Utils.JWTUtil;
import com.youo.bookmanage.handler.BusinessException;
import com.youo.bookmanage.handler.InterceptorException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.youo.bookmanage.response.ResultCode.USER_NO_ACCESS;
import static com.youo.bookmanage.response.ResultCode.USER_NO_RECORD;

@Slf4j
public class AuthInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
//        取cookie
        Cookie cookie1 = null;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if((cookie.getName()).equals("token")){
                cookie1 = cookie;
            }
        }


        if(cookie1 != null){
            String token = cookie1.getValue();
            JWTUtil.verify(token);
            DecodedJWT claims = JWTUtil.getTokenInfo(token);
            String id = claims.getClaim("role").asString();
            // 获取出方法上的Role注解
            Role role = method.getAnnotation(Role.class);
            // 如果注解为null, 说明不需要拦截, 直接放过
            if (role == null) {
                return true;
            } else {
                if (ArrayUtil.contains(role.roles(),id)) {
                    return true;
                } else {
                    throw new InterceptorException(USER_NO_ACCESS.getCode(), USER_NO_ACCESS.getMessage());
                }
            }
        }else{
            throw new BusinessException(USER_NO_RECORD.getCode(), USER_NO_RECORD.getMessage());
        }
    }
}
