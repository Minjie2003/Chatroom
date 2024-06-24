package com.example.demo.enity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-22
 * Time: 18:43
 */
@Data
public class Manager {
    private int id;
    private String accountNum;
    private String password;
    private LocalDateTime updateTime; // 更新时间
    private LocalDateTime createTime; // 创建时间
}
