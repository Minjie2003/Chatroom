package com.example.demo.configuration;

import com.example.demo.component.ManagerLoginInterceptor;
import com.example.demo.component.UserLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-16
 * Time: 23:17
 */
@Configuration
public class LoginConfiguration implements WebMvcConfigurer {
    @Autowired
    ManagerLoginInterceptor managerLoginInterceptor;
    @Autowired
    UserLoginInterceptor userLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(userLoginInterceptor)
//                .addPathPatterns("/**")
//                // 请求路径
//                .excludePathPatterns("/user/login")
//                .excludePathPatterns("/user/get_reg_mail_verify")
//                .excludePathPatterns("/user/reg")
//                .excludePathPatterns("/user/get_verify")
//                .excludePathPatterns("/user/get_update_mail_verify")
//                .excludePathPatterns("/user/identify")
//                .excludePathPatterns("/user/update_password")
//                .excludePathPatterns("/manager/*")
//                // 页面路径
//                .excludePathPatterns("/login.html")
//                .excludePathPatterns("/swagger-ui.html")
//                .excludePathPatterns("/manager_login.html")
//                .excludePathPatterns("/manager.html")
//                .excludePathPatterns("/update_password.html")
//                .excludePathPatterns("/reg.html")
//                .excludePathPatterns("/image/**")
//                .excludePathPatterns("/head/**")
//                .excludePathPatterns("/css/**")
//                .excludePathPatterns("/js/**");
        registry.addInterceptor(managerLoginInterceptor)
                // 请求路径
                .addPathPatterns("/manager/*")
                .excludePathPatterns("/manager/manager_login")
                // 页面路径
                .addPathPatterns("/manager.html");
    }
}
