package com.ballinapp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;

import com.ballinapp.jwt.JWTUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * User: dusan <br/> Date: 3/20/18
 */
@Component
public class DefaultRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o) throws Exception {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(authorizationHeader == null) {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().write("No token provided!");
            return false;
        }
        String token = authorizationHeader.substring(7, authorizationHeader.length());
        Map<Boolean, String> verifyMap = JWTUtil.verifyJWT(token);
        boolean result = false;
        for(Boolean b : verifyMap.keySet()) {
            result = b;
        }

        if(!result) {
            String message = verifyMap.get(result);
            if(message.contains("has expired")) {
                httpServletResponse.getWriter().write("Token expired!");
            } else {
                httpServletResponse.getWriter().write("Invalid token provided!");
            }
            httpServletResponse.setStatus(401);
        }

        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
            ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
            Object o, Exception e) throws Exception {

    }
}
