package com.example.demo.VO;

import com.example.demo.enity.ContactSession;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 13:36
 */
@Data
public class ContactSessionVO extends ContactSession {
    // 最后一条消息内容
    private String lastContent;
    // 最后一条消息的种类
    private String messageType;
    // 最后一条消息时间
    private LocalDateTime sendTime;
    // 会话的头像路径
    private String headPath;
    // 该会话的原本名称
    private String username;
    // 会话种类
    private int category;
}
