package com.example.demo.common;

import com.example.demo.enity.Message;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * Description: 维护通用错误响应常量
 * User: qumingnan
 * Date: 2023-11-19
 * Time: 20:57
 */
public class CommonConstant {
    public static final int PARAMETER_NULL = 0; // 参数中有空字符串
    public static final Map<Integer, Message> lastMessageMap= new ConcurrentHashMap<>();// 存会话的最后一条信息

}
