package com.example.demo.controller;

import com.example.demo.VO.ContactSessionVO;
import com.example.demo.VO.UserSelectVO;
import com.example.demo.common.*;
import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-08
 * Time: 0:53
 */
@RestController
@RequestMapping("/advice")
public class AdviceController {
    @Autowired
    AdviceService adviceService;
    @Autowired
    ContactSessionService contactSessionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ContactGroupService contactGroupService;
    @Autowired
    UserService userService;
    @Autowired
    ChatroomService chatroomService;
    @RequestMapping("/read_advice")
    public MyResult readAdvice(Integer id, HttpSession session, HttpServletRequest request){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 跟新通知
        Advice advice = new Advice();
        advice.setId(id);
        advice.setIsRead(1);
        int num = adviceService.updateAdvice(advice);
        return  MyResult.success(num);
    }
    @RequestMapping("/refuse_advice")
    public MyResult refuseAdvice(Integer id,HttpSession session) throws IOException {
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取通知
        Advice advice = adviceService.getAdviceById(id);
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断该通知是否是当前用户处理的
        if(advice.getReceiveId() != userInfo.getId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不是处理这个请求的用户");
        }

        // 持久化通知处理
        Advice advice1 = new Advice();
        advice1.setId(id);
        advice1.setResult(Advice.REFUSE);
        adviceService.updateAdvice(advice1);
        // 根据通知种类通知当前用户更新通知处理结果
        WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(userInfo.getId().toString());
        if(advice.getCategory() == Advice.FRIEND_REQ){
            if(webSocketServer != null){
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_FRIEND_REQ);
            }
        }else{
            if(webSocketServer != null){
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_CHATROOM_REQ);
            }
        }
        return MyResult.success(null);
    }
    @Transactional
    @RequestMapping("/accept_advice")
    public MyResult acceptAdvice(Integer id,HttpSession session) throws IOException {
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取通知
        Advice advice = adviceService.getAdviceById(id);
        // 判断该通知是否已经被处理
        if(advice.getResult() != 0){
            // 通知已经被处理了
            return MyResult.fail(AdviceHandlerConstant.ADVICE_HAS_HANDLE,"该通知已经被处理了");
        }
        // 判断这个通知是否是当前用户处理的
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo.getId() != advice.getReceiveId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你什么身份就敢处理这个请求？处理这个请求的人不是你");
        }
        // 更新该通知处理结果为接收
        Advice advice1 = new Advice();
        advice1.setResult(Advice.ACCEPT);
        advice1.setId(advice.getId());
        adviceService.updateAdvice(advice1);
        // 获取在线对象的websocket连接
        WebSocketServer sendWeb = WebSocketServer.webSocketMap.get(advice.getSendId().toString());
        WebSocketServer receiveWeb = WebSocketServer.webSocketMap.get(advice.getReceiveId().toString());
        // 根据通知类别建立接收通知后的关系
        if(advice.getCategory() == Advice.FRIEND_REQ){
            // 同意了会话请求
            // 判断是否曾经有过会话
            ContactSessionVO contactSessionVO = contactSessionService.getByUserIdAndContactId(advice.getReceiveId(),advice.getSendId(),Session.USER_TO_USER);

            if(contactSessionVO != null){
                // 获取接收者默认好友分组
                ContactGroup receiveUserGroup = contactGroupService.getDefaultFriendGroup(advice.getReceiveId());
                // 获取发送者默认好友分组
                ContactGroup sendUserGroup = contactGroupService.getDefaultFriendGroup(advice.getSendId());

                // 重新启用这个会话
                ContactSession contactSession = new ContactSession();
                contactSession.setId(contactSessionVO.getId());
                contactSession.setIsHide(0);
                contactSession.setIsDeleted(0);
                contactSession.setGroupId(receiveUserGroup.getId());
                contactSessionService.updateContactSession(contactSession);
                // 对方也要重新启用这个会话
                // 1. 获取这个回话
                contactSessionVO = contactSessionService.getByUserIdAndContactId(advice.getSendId(),advice.getReceiveId(),Session.USER_TO_USER);
                // 实例化一个实体类
                contactSession.setId(contactSessionVO.getId());
                contactSession.setGroupId(sendUserGroup.getId());
                // 重新启用会话
                contactSessionService.updateContactSession(contactSession);
            }else{
                // 建立会话
                Session session1 = new Session();
                session1.setCategory(Session.USER_TO_USER);
                sessionService.setSession(session1);
                // 获取接收者默认好友分组
                ContactGroup receiveUserGroup = contactGroupService.getDefaultFriendGroup(advice.getReceiveId());
                // 获取发送者默认好友分组
                ContactGroup sendUserGroup = contactGroupService.getDefaultFriendGroup(advice.getSendId());
                // 获取发送者用户信息
                UserInfo userInfo1 = userService.getById(advice.getSendId());

                // 建立发送者联系人会话
                ContactSession contactSession = new ContactSession();
                contactSession.setUserId(advice.getSendId());
                contactSession.setContactId(advice.getReceiveId());
                contactSession.setSessionId(session1.getId());
                contactSession.setNickName(userInfo.getUsername());
                contactSession.setGroupId(sendUserGroup.getId());
                // 建立接受者联系人会话
                ContactSession contactSession1 = new ContactSession();
                contactSession1.setSessionId(session1.getId());
                contactSession1.setGroupId(receiveUserGroup.getId());
                contactSession1.setNickName(userInfo1.getUsername());
                contactSession1.setContactId(advice.getSendId());
                contactSession1.setUserId(advice.getReceiveId());
                // 持久化联系人会话
                contactSessionService.setContactSession(contactSession);
                contactSessionService.setContactSession(contactSession1);

            }

            // 提醒receiveId获取新的friend_advice
            if(receiveWeb != null){
                receiveWeb.sendMessage(WebSocketConstant.UPDATE_FRIEND_REQ);
            }



        }else{
            // 聊天室会话

            // 查询是否之前是这个聊天室会话的
            ContactSessionVO contactSessionVO = null;
            // 查询要入群用户的默认的聊天室分组
            ContactGroup contactGroup = null;
            // 请求入群则userId是发送者id
            if(advice.getCategory() == Advice.ADD_CHATROOM_REQ){
                // 获取发送者用户的默认群聊分组
                contactGroup = contactGroupService.getDefaultChatroomGroup(advice.getSendId());

                contactSessionVO = contactSessionService.getByUserIdAndContactId(advice.getSendId(),advice.getChatroomId(),Session.CHATROOM);
            }
            // 邀请入群则userId是接受者id
            if(advice.getCategory() == Advice.CHATROOM_INVITE_REQ){
                // 当前用户就是被邀请用户 获取当前用户的默认群聊分组
                contactGroup = contactGroupService.getDefaultChatroomGroup(advice.getReceiveId());

                contactSessionVO = contactSessionService.getByUserIdAndContactId(advice.getReceiveId(),advice.getChatroomId(),Session.CHATROOM);
            }
            // 判断之前是否有加入
            if(contactSessionVO != null){
                // 重启之前的会话
                ContactSession contactSession = new ContactSession();
                contactSession.setId(contactSessionVO.getId());
                contactSession.setIsHide(0);
                contactSession.setIsDeleted(0);
                contactSession.setGroupId(contactGroup.getId());
                // 更新记录
                contactSessionService.updateContactSession(contactSession);

                // 群聊人数人加一
                // 查询聊天室信息
                Chatroom chatroom = chatroomService.getBySessionId(advice.getChatroomId());
                // 将聊天室成员数+1
                Chatroom chatroom1 =new Chatroom();
                chatroom1.setSessionId(chatroom.getSessionId());
                chatroom1.setUserNum(chatroom.getUserNum()+1);
                chatroomService.updateChatroom(chatroom1);



            }else{
                // 查询聊天室信息
                Chatroom chatroom = chatroomService.getBySessionId(advice.getChatroomId());

                // 将聊天室成员数+1
                Chatroom chatroom1 =new Chatroom();
                chatroom1.setSessionId(chatroom.getSessionId());
                chatroom1.setUserNum(chatroom.getUserNum()+1);
                chatroomService.updateChatroom(chatroom1);
                // 查看之前是否是这个会话的用户

                // 建立联系人会话
                ContactSession contactSession = new ContactSession();
                contactSession.setContactId(advice.getChatroomId());
                contactSession.setSessionId(advice.getChatroomId());
                contactSession.setNickName(chatroom.getName());


                if(advice.getCategory() == Advice.CHATROOM_INVITE_REQ){
                    // 被邀请人入群
                    contactSession.setUserId(userInfo.getId());

                }
                if(advice.getCategory() == Advice.ADD_CHATROOM_REQ){
                    // 发送请求者入群
                    contactSession.setUserId(advice.getSendId());

                }
                // 设置聊天室分组
                contactSession.setGroupId(contactGroup.getId());
                // 持久化联系人会话
                contactSessionService.setContactSession(contactSession);
            }

            // 提醒receiveId获取新的chatroom_advice
            if(receiveWeb != null){
                receiveWeb.sendMessage(WebSocketConstant.UPDATE_CHATROOM_REQ);
            }


        }
        // 提箱接收者和发送者更新contactSession

        if(sendWeb != null){
            sendWeb.sendMessage(WebSocketConstant.UPDATE_CONTACT_SESSION);
        }
        if(receiveWeb != null){
            receiveWeb.sendMessage(WebSocketConstant.UPDATE_CONTACT_SESSION);
        }

        return MyResult.success(null);

    }
    /**
     * 向被发送过来的用户发送邀请进群请求
     * @param id id数组
     * @param chatroomId 聊天室id
     * @param session 会话
     * @return 统一返回值
     * @throws IOException
     */
    @Transactional
    @RequestMapping("invite_chatroom")
    public MyResult inviteChatroom(@RequestParam(value = "id[]") Integer[] id, Integer chatroomId, HttpSession session) throws IOException {
        // 非空校验
        if(id == null || chatroomId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        UserInfo userinfo = (UserInfo) session.getAttribute("userinfo");
        // 插入邀请请求

        // 获取这个会话的所有用户
        List<UserSelectVO> list = contactSessionService.getAllMemberBySessionId(chatroomId);


        for (int i = 0; i < id.length; i++) {
            // 判断这个id是否以及是成员
            for (UserSelectVO u:
                    list) {
                // 是成员
                if(u.getId() == id[i] ){
                    return MyResult.fail(SendAdviceConstant.ALREADY_CONNECTED,"有用户已经是群员了");
                }
            }
            // 插入通知信息
            Advice advice = new Advice();
            advice.setReceiveId(id[i]);
            advice.setSendId(userinfo.getId());
            advice.setCategory(Advice.CHATROOM_INVITE_REQ);
            advice.setContent("");
            advice.setChatroomId(chatroomId);
            // 插入通知信息
            adviceService.setAddChatroomAdvice(advice);
            // 通知在线的接受者
            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(id[i].toString());
            if(webSocketServer != null){
                // 在线
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_CHATROOM_REQ);
            }
        }
        return MyResult.success("发送成功");

    }

    /**
     * 接收举报用户信息
     * @param receiveId 被举报用户id
     * @param content 举报理由
     * @param session 会话
     * @return 统一返回对象
     */
    @RequestMapping("report_info")
    public MyResult reportInfo(Integer receiveId,String content,HttpSession session){
        // 非空校验
        if(receiveId == null || content == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");

        // 构造举报通知
        Advice advice = new Advice();
        advice.setContent(content);
        advice.setCategory(Advice.REPORT_INFO);
        advice.setSendId(userInfo.getId());
        advice.setReceiveId(receiveId);
        // 插入通知信息
        int num = adviceService.setFriendAdvice(advice);

        return MyResult.success(num);
    }

    /**
     * 加入聊天室请求
     * @param receiveId 聊天室群主id
     * @param chatroomId 聊天室会话id
     * @param content 备注信息
     * @param session 会话
     * @return 统一返回值
     * @throws IOException websocket发送消息时可能发生的异常
     */
    @RequestMapping("add_chatroom")
    public MyResult addChatroom(Integer receiveId,Integer chatroomId,String content,HttpSession session) throws IOException {
        // 非空校验
        if(receiveId == null || chatroomId == null || StringUtils.hasLength(content)){
            MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取当前用户id
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断这个群聊会话是否可用
        Session session1 = sessionService.getById(chatroomId);
        if(session1.getIsDeleted() == 1){
            // 该会话不可用
            MyResult.fail(SendAdviceConstant.SESSION_DELETED);
        }
        // 获取这个会话的所有用户
        List<UserSelectVO> list = contactSessionService.getAllMemberBySessionId(chatroomId);

        // 判断当前用户是否以及是成员
        for (UserSelectVO u:
             list) {
            // 是成员
            if(u.getId() == userInfo.getId() ){
                return MyResult.fail(SendAdviceConstant.ALREADY_CONNECTED);
            }
        }
        // 当前用户不是成员
        Advice advice = new Advice();
        // 设置通知信息
        advice.setCategory(Advice.ADD_CHATROOM_REQ);
        advice.setReceiveId(receiveId);
        advice.setSendId(userInfo.getId());
        advice.setContent(content);
        advice.setChatroomId(chatroomId);
        int num = adviceService.setAddChatroomAdvice(advice);
        // 如果群主在线，直接通知
        WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(receiveId.toString());
        if(webSocketServer != null){
            // 在线
            webSocketServer.sendMessage("有新的聊天室请求");
        }
        return MyResult.success(num);
    }
    /**
     * 插入一条好友请求信息
     * @param receiveId 接收者用户id
     * @param content 备注信息
     * @param session 当前会话
     * @return 统一返回值
     */
    @RequestMapping("friend_req")
    public MyResult friendReq(Integer receiveId,String content,HttpSession session) throws IOException {
        // 非空校验
        if(receiveId == null || content==null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 定义新的通知对象
        Advice advice =new Advice();
        // 获取当前用户
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断该用户是否就是本机
        if(receiveId == userInfo.getId()){
            return MyResult.fail(SendAdviceConstant.ALREADY_CONNECTED);
        }
        // 判断该用户是否已经是本机好友
        List<ContactSessionVO> list = contactSessionService.getUserSessionList(userInfo.getId());
        for (ContactSessionVO s:
             list) {
            if(s.getCategory() == Session.USER_TO_USER && s.getContactId() == receiveId && s.getIsDeleted() == 0){
                return MyResult.fail(SendAdviceConstant.ALREADY_CONNECTED);
            }
        }
        // 不是好友，发送请求
        advice.setSendId(userInfo.getId());
        advice.setReceiveId(receiveId);
        advice.setContent(content);
        advice.setCategory(Advice.FRIEND_REQ);

        int num = adviceService.setFriendAdvice(advice);
        // 检查接受用户是否在线
        WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(receiveId.toString());
        if(webSocketServer != null){
            webSocketServer.sendMessage(WebSocketConstant.UPDATE_FRIEND_REQ);
        }

        return MyResult.success(num);
    }

    /**
     * 获取一个用户的所有通知
     * @return 统一返回值
     */
    @RequestMapping("/get_all_friend_advice")
    public MyResult getAllFriendAdvice(HttpSession session){
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 查询所有通知
        List list = adviceService.getAllFriendAdviceByReceiveId(userInfo.getId());
        return MyResult.success(list);
    }

    /**
     * 获取所有的聊天室通知
     * @param session 会话
     * @return 统一返回值
     */
    @RequestMapping("get_all_chatroom_advice")
    public MyResult getAllChatroomAdvice(HttpSession session){
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 查询所有聊天室通知
        List list = adviceService.getAllChatroomAdviceByReceivedId(userInfo.getId());
        return MyResult.success(list);
    }

    /**
     * 获取所有警告
     * @param session 会话
     * @return 统一返回值
     */
    @RequestMapping("get_all_warn_advice")
    public MyResult getAllWarnAdvice(HttpSession session){
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 查询所有警告通知
        List list = adviceService.getAllWarnAdviceByReceiveId(userInfo.getId());
        return MyResult.success(list);
    }

    /**
     * 获取所有的举报信息
     * @param session 当前回话
     * @return 统一返回值
     */
    @RequestMapping("get_all_report_advice")
    public MyResult getAllReportAdvice(HttpSession session){
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 查询所有举报通知
        List list = adviceService.getAllReportByReceivedId(userInfo.getId());
        return MyResult.success(list);
    }
}
