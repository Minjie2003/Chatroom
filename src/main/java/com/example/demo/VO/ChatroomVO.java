package com.example.demo.VO;

import com.example.demo.enity.Chatroom;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-06
 * Time: 22:11
 */
@Data
public class ChatroomVO extends Chatroom {
    private String username;
    private String headPath;
    private String accountNum;
}
