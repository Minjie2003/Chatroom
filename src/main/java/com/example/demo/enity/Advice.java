package com.example.demo.enity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:04
 */
@Data

public class Advice {

    private int id;// 主键id
    private Integer sendId;// 发送者id
    private Integer receiveId;// 接收者id
    private String content; // 通知内容
    private Integer category;//0 好友请求 1 邀请加入聊天室请求 2 举报 3 警告 4 主动请求加入聊天室
    private Integer isRead;// 0 未读 ，1 已读
    private Integer result;// 通知处理结果
    private LocalDateTime sendTime;
    private Integer chatroomId; // 聊天室id，如果是加入聊天室请求
    // 通知类型
    public static int FRIEND_REQ = 0;
    public static int CHATROOM_INVITE_REQ=1;
    public static int ADD_CHATROOM_REQ = 2;
    public static int REPORT_INFO = 3;
    public static int WARNING_INFO = 4;
    // 通知未处理
    public static int  UNTREATED= 0;
    // 请求处理
    public static int REFUSE = 1;
    public static int ACCEPT = 2;
    // 举报处理

    public static int WARN = 1;
    public static int BAN = 2;


}
