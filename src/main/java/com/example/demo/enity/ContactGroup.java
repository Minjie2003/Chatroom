package com.example.demo.enity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:50
 */
@Data
public class ContactGroup {
    private int id;// 主键id
    private String name;// 组名
    private Integer userId; // 分组拥有者id
    private Integer isDefault; // 是否是用户默认的好友组别
    private Integer category; // 种类 0 用户分组 1 聊天室分组
    private LocalDateTime updateTime; // 更新时间
    private LocalDateTime createTime; // 创建时间

    // 表示状态的静态变量
    public static int CHATROOM = 0; // 聊天室会话
    public static int USER_TO_USER = 1; // 用户之间会话
}
