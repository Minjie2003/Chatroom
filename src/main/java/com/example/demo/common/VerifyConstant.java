package com.example.demo.common;

/**
 * Created with IntelliJ IDEA.
 * Description: 维护与验证码相关的常量
 * User: qumingnan
 * Date: 2023-11-16
 * Time: 15:03
 */
public class VerifyConstant {
    // 邮箱验证码相关常量



    public static final int MAIL_USED = 2; // 邮箱已经被注册了 返回状态
    public static final int MAIL_VERIFY_ERROR = 3;// 邮箱验证码错误 返回状态
    public static final String MAIL_VERIFY = "mailVerify"; // 邮箱验证码键值
    // 图形验证码相关常量
    public static final int CREATE_EXCEPTION = 1;// 获取相应的out stream异常
    public static final String IMAGE_VERIFY = "IMAGE_VERIFY"; // 邮箱验证码键值

}
