package com.example.demo.common;

/**
 * Created with IntelliJ IDEA.
 * Description: 登录过程中的错误状态码
 * User: qumingnan
 * Date: 2023-11-19
 * Time: 21:04
 */
public class LoginConstant {
    public static final int USER_NOT_EXIST = 1;// 账号不存在返回状态码
    public static final int USER_BANED = 2; // 账号被封禁
    public static final int PASSWORD_ERROR = 3; // 密码错误
    public static final int VERIFY_ERROR = 4; // 验证码错误
}
