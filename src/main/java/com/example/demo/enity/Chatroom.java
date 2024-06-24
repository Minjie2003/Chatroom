package com.example.demo.enity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:44
 */
@Data
public class Chatroom {
    private Integer sessionId; // 对应的会话id
    private Integer creatorId;// 创建者用户id
    private String name; // 聊天室名称
    private String photo;// 头像路径
    private Integer userNum;// 成员个数
    private LocalDateTime updateTime; // 信息更新时间
    private LocalDateTime createTime; // 创建时间
}
