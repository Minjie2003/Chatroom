package com.example.demo.common;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-21
 * Time: 12:49
 */
public class IdentifyConstant {
    public static final int VERIFY_ERROR = 1; // 验证码错误 状态码
    public static final int VERIFY_NOT_GET = 2; // 用户未获取验证码就点击了确认 状态码
    public static final int ACCOUNT_NOT_SAME = 3;// 获取验证码的账号和发送的账号不一致
    public static final String PERMISSION = "permission"; // 确认账号成功后在session中设置的权限键值
}
