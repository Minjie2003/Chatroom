package com.example.demo.common;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-13
 * Time: 22:36
 */
@Data
public class MyResult {
    private int code;
    private String message;
    private Object data;

    /**
     * 响应返回正确信息,只传data
     */
    public static MyResult success(Object data){
        MyResult myResult=new MyResult();
        myResult.setCode(200);
        myResult.setMessage("");
        myResult.setData(data);
        return myResult;
    }
    /**
     *
     * 响应返回正确信息，可以穿data和Message
     */

    public static MyResult success(String message,Object data){
        MyResult myResult=new MyResult();
        myResult.setCode(200);
        myResult.setMessage(message);
        myResult.setData(data);
        return myResult;
    }
    /**
     * 返回错误信息，只传状态码
     */

    public static MyResult fail(Integer code){
        MyResult myResult=new MyResult();
        myResult.setData(null);
        myResult.setCode(code);
        myResult.setMessage("");
        return myResult;
    }

    /**
     * 返回错误信息，只传状态码，信息
      * @param code 状态码
     * @param message 描述信息
     * @return 返回结果对象
     */
    public static MyResult fail(Integer code,String message){
        MyResult myResult=new MyResult();
        myResult.setData(null);
        myResult.setCode(code);
        myResult.setMessage(message);
        return myResult;
    }
}
