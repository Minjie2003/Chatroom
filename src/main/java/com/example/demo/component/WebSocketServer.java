package com.example.demo.component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.example.demo.enity.Message;
import com.example.demo.enity.UserInfo;
import com.example.demo.service.MessageService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;



@ServerEndpoint("/server/{userId}")
@Component
public class WebSocketServer {
    private static UserService userService ;

    private static MessageService messageService;
    @Autowired
    public void setMessageService(MessageService messageService){
        this.messageService = messageService;
    }
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    public static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    public Session session;
    /**接收userId*/
    private Integer userId;

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("userId") Integer id) {
        // 记录连接用户id
        userId = id;
        // 将用户的状态改为在线状态
        UserInfo userInfo =new UserInfo();
        userInfo.setId(id);
        // 判断该用户是否已经被封禁
        UserInfo userInfo1 = userService.getById(id);
        if(userInfo1.getState() == UserInfo.BAN){
            return;
        }
        userInfo.setState(UserInfo.ONLINE);
        userService.updateUserinfo(userInfo);
        // 插入webSocketMap
        webSocketMap.put(userId.toString(),this);
        // 保存session
        this.session = session;
        System.out.println("连接脸上了");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // 将用户状态切换为离线状态
        UserInfo userInfo =new UserInfo();
        userInfo.setId(userId);
        userInfo.setState(UserInfo.OFFLINE);
        userService.updateUserinfo(userInfo);
        // 去除当前websocket
        webSocketMap.remove(userId.toString());
        System.out.println("连接断开了！！！！");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) throws JsonProcessingException {

    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {

        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {

    }


}
