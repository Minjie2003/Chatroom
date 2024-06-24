package com.example.demo.VO;

import com.example.demo.enity.Message;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-03
 * Time: 20:41
 */
@Data
public class MessageVO extends Message {
    private String headPath;
    private String username;
}
