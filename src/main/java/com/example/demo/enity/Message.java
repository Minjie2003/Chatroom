package com.example.demo.enity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 17:58
 */
@Data
public class Message {
    private Integer id;// 主键id
    private Integer sendId;// 发送者id
    private Integer sessionId;// 会话id
    private LocalDateTime sendTime;// 发送时间
    private String content;// 发送的内容
    private Integer category;// 信息的种类(文本,图片)
    // 表示种类的静态变量
    public static int TEXT = 0;
    public static int PICTURE = 1;
}
