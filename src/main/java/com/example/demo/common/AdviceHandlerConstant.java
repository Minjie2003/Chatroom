package com.example.demo.common;

/**
 * Created with IntelliJ IDEA.
 * Description: 保存通知信息接受或拒绝时出现异常的状态码
 * User: qumingnan
 * Date: 2023-12-12
 * Time: 18:45
 */
public class AdviceHandlerConstant {
    // 通知已经被处理的静态变量
    public static int ADVICE_HAS_HANDLE= 1;
    // 这个通知不是这个用户该处理的
    public static int NOT_THE_HANDLER = 2;
}
