package com.example.demo.VO;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description: 作为搜索用户结果的VO对象
 * User: qumingnan
 * Date: 2023-11-26
 * Time: 23:11
 */
@Data
public class UserSelectVO {
    private String username;
    private String accountNum;
    private String photo;
    private Integer id;
}
