package com.example.demo.controller;


import com.example.demo.VO.ChatroomVO;
import com.example.demo.common.*;
import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.*;
import com.example.demo.service.ChatroomService;
import com.example.demo.service.ContactGroupService;
import com.example.demo.service.ContactSessionService;
import com.example.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.time.LocalDateTime.now;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-26
 * Time: 23:33
 */
@RequestMapping("/chatroom")
@RestController
public class ChatroomController {
    @Autowired
    ChatroomService chatroomService;
    @Autowired
    ContactSessionService contactSessionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ContactGroupService contactGroupService;
    @RequestMapping("/create_chatroom")
    public MyResult createChatroom(String name,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(name)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取当前用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 创建回话
        Session session1 = new Session();
        session1.setCategory(Session.CHATROOM);
        sessionService.setSession(session1);

        // 创建聊天室
        Chatroom chatroom = new Chatroom();
        chatroom.setSessionId(session1.getId());
        chatroom.setName(name);
        chatroom.setCreatorId(userInfo.getId());
        chatroomService.setChatroom(chatroom);
        // 获取默认群聊分组
        ContactGroup contactGroup = contactGroupService.getDefaultChatroomGroup(userInfo.getId());
        // 创建联系人会话
        ContactSession contactSession = new ContactSession();
        contactSession.setGroupId(contactGroup.getId());
        contactSession.setNickName(name);
        contactSession.setUserId(userInfo.getId());
        contactSession.setContactId(session1.getId());
        contactSession.setSessionId(session1.getId());

        contactSessionService.setContactSession(contactSession);

        return MyResult.success(null);
    }
    @RequestMapping("/update_chatroom_name")
    public MyResult updateChatroomName(Integer chatroomId,String name,HttpSession session){
        // 非空校验
        if(chatroomId == null || name == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断用户是否创建者
        ChatroomVO chatroomVO = chatroomService.getBySessionId(chatroomId);
        if(chatroomVO.getCreatorId() != userInfo.getId()){
            return MyResult.fail(UpdateChatroomConstant.NOT_THE_OWNER,"你不是这个聊天室的群主");
        }
        // 更新聊天室名称
        Chatroom chatroom = new Chatroom();
        chatroom.setSessionId(chatroomId);
        chatroom.setName(name);
        int num = chatroomService.updateChatroom(chatroom);
        return MyResult.success(num);
    }
    @RequestMapping("/update_chatroom_head")
    public MyResult updateChatroomHead(MultipartFile photo, Integer chatroomId, HttpSession session) throws IOException {
        // 非空校验
        if(photo == null || chatroomId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 判断发出请求的是否是群主
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        Chatroom chatroom = chatroomService.getBySessionId(chatroomId);
        if(chatroom.getCreatorId() != userInfo.getId()){
            return MyResult.fail(UpdateChatroomConstant.NOT_THE_OWNER,"不是群主");
        }
        /* 更新头像 */
        // 获取后缀名
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf('.'));
        // 形成唯一名称
        String newName = UUID.randomUUID().toString()+suffix;
        // 创建新的头像文件
        File file =new File(PathConstant.HEAD_PATH+File.separator+newName);
        file.createNewFile();
        // 创建文件输出流
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(photo.getBytes());
        // 更新chatroom
        Chatroom chatroom1 = new Chatroom();
        chatroom1.setSessionId(chatroomId);
        chatroom1.setPhoto("head"+File.separator+newName);
        chatroom1.setUpdateTime(now());
        int num = chatroomService.updateChatroom(chatroom1);
        // 提醒所有在线的群员更新会话
        List<ContactSession> list = contactSessionService.getAllBySessionId(chatroomId);

        for (ContactSession c:
             list) {
            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(c.getUserId().toString());
            if(webSocketServer != null){
                webSocketServer.sendMessage("重新请求用户会话列表");
            }
        }
        return MyResult.success(num);
    }
    /**
     * 根据聊天室名称查询用户
     * @param key 关键字
     * @return 统一返回值
     */
    @RequestMapping("/select_chatroom")
    public MyResult select_chatroom(String key){
        // 非空判断
        if(!StringUtils.hasLength(key)){
            MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取聊天室列表
        List<Chatroom> list = chatroomService.getAllByNameAndSessionId(key);
        // 返回结果
        return MyResult.success(list);
    }

    /**
     * 返回聊天室信息
     * @param sessionId 会话id
     * @return 统一返回值
     */
    @RequestMapping("/get_chatroom_info")
    public MyResult getChatroomInfo(Integer sessionId){
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 搜索聊天室信息
        ChatroomVO chatroomVO = chatroomService.getBySessionId(sessionId);
        return MyResult.success(chatroomVO);

    }
}
