package com.example.demo.VO;

import com.example.demo.enity.Advice;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-16
 * Time: 0:15
 */
@Data
public class ReportVO extends Advice {
    // 发送者的名称
    public String sendName;
    public String receiveName;

}
