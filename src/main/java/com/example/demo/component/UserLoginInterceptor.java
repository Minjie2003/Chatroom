package com.example.demo.component;

import com.example.demo.enity.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-16
 * Time: 23:12
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo == null){
            response.sendRedirect(contextPath+"/login.html");
            return false;
        }
        return true;
    }
}
