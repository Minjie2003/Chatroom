package com.example.demo.enity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:47
 */
@Data
public class ContactSession {
    private int id; // 主键id
    private Integer userId; // 用户id
    private Integer sessionId;// 会话id
    private Integer isHide;// 是否被隐藏 0 否，1 是
    private Integer isDeleted;// 是否被删除了 0否 1 是
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime; // 更新时间
    private LocalDateTime createTime;// 创建时间
    private String nickName; // 用户对会话的备注
    private Integer unreadNum;// 该会话的未读信息

    private Integer groupId; // 分组id
    private Integer contactId; // 联系人Id



}
