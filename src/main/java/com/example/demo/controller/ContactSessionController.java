package com.example.demo.controller;

import com.example.demo.VO.ChatroomVO;
import com.example.demo.VO.ContactSessionVO;
import com.example.demo.common.*;
import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static java.time.LocalDateTime.now;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 23:22
 */
@RestController
@RequestMapping("/contact_session")
public class ContactSessionController {
    @Autowired
    ContactSessionService contactSessionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    ChatroomService chatroomService;
    @Autowired
    ContactGroupService contactGroupService;
    @Autowired
    UserService userService;
    @Autowired
    WebSocketUtil webSocketUtil;
    @RequestMapping("/ban_chatroom")
    public MyResult banChatroom(Integer sessionId,HttpSession session) throws IOException {
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 判断这个用户是否是这个聊天室的主人
        Chatroom chatroom = chatroomService.getBySessionId(sessionId);
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(chatroom == null || chatroom.getCreatorId() != userInfo.getId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER);
        }

        // 封禁这个会话
        int num = sessionService.banSessionById(sessionId);
        // 将这个会话的所有成员会话给隐藏
        webSocketUtil.hideAllMemberSession(sessionId);
        return MyResult.success(num);
    }
    /**
     * 切换联系人分组
     * @param id 联系人id
     * @param groupId 分组id
     * @param session 会话
     * @return
     */
    @RequestMapping("/switch_group")
    public MyResult switchGroup(Integer id,Integer groupId,HttpSession session){
        // 非空校验
        if(id == null || groupId == null){
            return MyResult.success(CommonConstant.PARAMETER_NULL);
        }
        // 获取会话
        ContactSession contactSession = contactSessionService.getById(id);
        // 获取当前用户
        UserInfo userInfo= (UserInfo) session.getAttribute("userinfo");
        // 获取要切换的分组
        ContactGroup contactGroup = contactGroupService.getContactGroupById(groupId);
        // 判断是否是当前用户的会话
        if(contactSession.getUserId() != userInfo.getId()){
            MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不是这个会话的主人啊，谁让你来的换的？");
        }
        // 判断是否是当前用户的分组
        if(contactGroup.getUserId() != userInfo.getId()){
            MyResult.fail(3,"你要切换的分组都不是你的，你切啥呢");
        }
        //切换分组
        ContactSession contactSession1 = new ContactSession();
        contactSession1.setId(contactSession.getId());
        contactSession1.setGroupId(groupId);
        int num = contactSessionService.updateContactSession(contactSession1);
        return MyResult.success(num);

    }

    /**
     * 将选中会话状态设置为显示
     * @param id 会话id
     * @param session 会话
     * @return
     */
    @RequestMapping("/show_session")
    public MyResult showSession(Integer id,HttpSession session){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取联系人回话
        ContactSession contactSession = contactSessionService.getById(id);
        // 判断该当前用户是否是这个会话的主人
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo.getId() != contactSession.getUserId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不能发送显示别人会话的请求");
        }
        // 更新会话
        ContactSession contactSession1 = new ContactSession();
        contactSession1.setId(id);
        contactSession1.setIsHide(0);
        contactSessionService.updateContactSession(contactSession1);
        return MyResult.success(null);
    }

    /**
     * 删除好友
     * @param sessionId 会话id
     * @param session 会话
     * @return
     */
    @Transactional
    @RequestMapping("/delete_friend")
    public MyResult deleteFriend(Integer sessionId,HttpSession session) throws IOException {
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取联系人会话
        List<ContactSession> list = contactSessionService.getAllBySessionId(sessionId);

        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断当前用户是否是两者中的一个
        int flag = 0;
        for (ContactSession c:
                list) {
            if(c.getUserId() == userInfo.getId()){
                flag = 1;
            }
        }
        if(flag == 0 ){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不能帮别人删除好友");
        }
        ContactSession contactSession = new ContactSession();
        // 删除回话
        for (ContactSession c:
             list) {
            // 删除用户会话关系
            contactSession.setId(c.getId());
            contactSession.setIsDeleted(1);
            contactSessionService.updateContactSession(contactSession);
            // 主动提醒在线用户自己被删了
            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(c.getContactId().toString());
            if(webSocketServer != null){
                // 该用户在线
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_CONTACT_SESSION);
            }
        }


        return MyResult.success(null);

    }

    /**
     * 退出聊天室
     * @param id 聊天室id
     * @param session 会话
     * @return
     */
    @RequestMapping("/exit_chatroom")
    public MyResult exitChatroom(Integer id,HttpSession session){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取联系人回话
        ContactSession contactSession = contactSessionService.getById(id);
        // 判断该当前用户是否是这个会话的主人
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo.getId() != contactSession.getUserId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不能发送让被人退出群聊的请求");
        }
        // 更新聊天室人数
        Chatroom chatroom = chatroomService.getBySessionId(contactSession.getSessionId());
        Chatroom chatroom1 = new Chatroom();
        chatroom1.setSessionId(contactSession.getSessionId());
        chatroom1.setUserNum(chatroom.getUserNum()-1);
        chatroomService.updateChatroom(chatroom1);
        // 退出这个聊天室会话
        int num = contactSessionService.deleteContactSessionByUserIdAndSessionId(contactSession.getUserId(),contactSession.getSessionId());
        return MyResult.success(num);
    }

    /**
     * 修改会话备注
     * @param nickName 备注
     * @param id 用户id
     * @param session 会话
     * @return
     */
    @RequestMapping("/update_nick_name")
    public MyResult updateNickName(String nickName,Integer id,HttpSession session){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 根据id获取contactSession
        ContactSession contactSession = contactSessionService.getById(id);
        // 判断是否是本用户的会话
        UserInfo  userInfo = (UserInfo) session.getAttribute("userinfo");
        if(contactSession.getUserId() != userInfo.getId()){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你什么身份敢改别人的备注");
        }
        // 修改
        ContactSession contactSession1 = new ContactSession();
        contactSession1.setId(id);
        contactSession1.setNickName(nickName);
        int num = contactSessionService.updateContactSession(contactSession1);
        return MyResult.success(num);
    }

    /**
     * 移除用户
     * @param id 移除用户的id列表
     * @param chatroomId 聊天室id
     * @param session 会话
     * @return
     * @throws IOException
     */
    @RequestMapping("/remove_members")
    public MyResult removeMember(@RequestParam(value = "id[]") Integer[] id, Integer chatroomId, HttpSession session) throws IOException {
        // 非空校验
        if(id == null || chatroomId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        for (int i = 0; i < id.length; i++) {
            // 删除用户与会话的关系
            contactSessionService.deleteContactSessionByUserIdAndSessionId(id[i],chatroomId);
            // 如果在线，就发送信号
            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(id[i].toString());
            if(webSocketServer != null && webSocketServer.session != null){
                // 发送消息了
                webSocketServer.sendMessage("重新请求用户会话列表");
            }
        }
        // 修改聊天室人数
        Chatroom chatroom = chatroomService.getBySessionId(chatroomId);
        // 定义修改后的chatroom
        Chatroom chatroom1 = new Chatroom();
        chatroom1.setSessionId(chatroomId);
        chatroom1.setUpdateTime(now());
        chatroom1.setUserNum(chatroom.getUserNum()-id.length);
        // 更新聊天室
        int num = chatroomService.updateChatroom(chatroom1);
        // 提醒在线的成员修改会话信息

        return MyResult.success(num);
    }

    /**
     * 将会话的状态设为隐藏
     * @param id 联系人会话id
     * @return
     */
    @RequestMapping("/hide_session")
    public MyResult hideSession(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);

        }
        // 定义新会话
        ContactSession contactSession = new ContactSession();
        contactSession.setId(id);
        contactSession.setIsHide(1);
        // 更新会话内容
        int num = contactSessionService.updateContactSession(contactSession);
        return MyResult.success(num);
    }

    /**
     * 获取聊天室成员
     * @param sessionId 会话id
     * @return
     */
    @RequestMapping("get_chatroom_person")
    public MyResult getChatroomPerson(Integer sessionId){
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询查询成员用户信息
        List list = contactSessionService.getAllMemberBySessionId(sessionId);
        return MyResult.success(list);
    }

    /**
     * 获取联系人会话列表
     * @param httpSession 会话
     * @return
     */
    @RequestMapping("get_contact_session_list")
    public MyResult getUserSessionList(HttpSession httpSession){
        UserInfo userInfo =(UserInfo)httpSession.getAttribute("userinfo");
        Integer userId = userInfo.getId();
        // 获取到userSessionList
        List<ContactSessionVO> list = contactSessionService.getUserSessionList(userId);
        // 获取每个userSession的头像路径
        for (ContactSessionVO u:
             list) {
            int sessionId = u.getSessionId();
            // 获取到session
            Session session = sessionService.getById(sessionId);
            // 设置会话是否被删除
            if(session.getIsDeleted() == 1){
                u.setIsDeleted(session.getIsDeleted());
            }

            // 判断会话种类
            if(session.getCategory() == 0){
                // 聊天室会话
                ChatroomVO chatroom = chatroomService.getBySessionId(sessionId);
                // 设置会话头像路径
                u.setHeadPath(chatroom.getPhoto());
                // 设置会话的原本名称
                u.setUsername(chatroom.getName());
            }else{
                // 用户之间会话

                // 查询联系人用户信息
                UserInfo userInfo1 = userService.getById(u.getContactId());
                // 设置会话头像路径
                u.setHeadPath(userInfo1.getPhoto());
                // 设置会话的原本名称
                u.setUsername(userInfo1.getUsername());
            }
        }
        // 返回会话列表
        return MyResult.success("用户会话列表",list);
    }

    /**
     * 清空未读信息
     * @param id 联系人会话id
     * @return
     */
    @RequestMapping("/clear_unread_num")
    public MyResult clearUnreadNum(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"Id没有被接收到");
        }
        // 置空unreadNum
        ContactSession contactSession = new ContactSession();
        contactSession.setId(id);
        contactSession.setUnreadNum(0);
        contactSessionService.updateContactSessionUnReadNum(contactSession);
        // 返回结果
        return MyResult.success("更新成功",null);
    }
}
