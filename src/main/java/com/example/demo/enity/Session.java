package com.example.demo.enity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:48
 */
@Data
public class Session {
    private int id; // 主键id
    private Integer category; // 0 聊天室会话,1 用户之间会话
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
    private Integer isDeleted; // 是否删除 0未删除 1 删除了

    // 表示状态的静态变量
    public static int CHATROOM = 0; // 聊天室会话
    public static int USER_TO_USER = 1; // 用户之间会话
}
