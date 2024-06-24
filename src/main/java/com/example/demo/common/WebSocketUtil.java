package com.example.demo.common;

import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.ContactSession;
import com.example.demo.service.ContactSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:使用WebSocket向同一会话的所有在线用户发送通知通知
 * User: qumingnan
 * Date: 2023-12-05
 * Time: 20:53
 */
@Component
public class WebSocketUtil {

    @Autowired
    ContactSessionService contactSessionService;

    /**
     * 提醒传入会话的在线成员更新会话列表，并让这个会话的未读信息+1
     * @param sessionId 会话id
     * @param userId 不会提醒的成员用户id
     * @throws IOException
     */
    public void noticeAllSameSessionUser(int sessionId,int userId) throws IOException {
        // 获取这个会话的所有用户
        List<ContactSession> list = contactSessionService.getAllBySessionId(sessionId);
        // 更新所有用户会话的unreadNnm,并且向在线的用户发送更新会话列表信号
        for (ContactSession contactSession : list) {
            // 判断当前会话是否是自己
            if(contactSession.getUserId() != userId){
                ContactSession contactSession1 = new ContactSession();
                contactSession1.setUnreadNum(contactSession.getUnreadNum()+1);
                contactSession1.setIsHide(0);
                contactSession1.setId(contactSession.getId());
                // 更新会话
                contactSessionService.updateContactSession(contactSession1);
                // 如果在线，就发送信号
                WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(contactSession.getUserId().toString());
                if(webSocketServer != null && webSocketServer.session != null){
                    // 发送消息了
                    System.out.println("发送消息了");
                    webSocketServer.sendMessage(WebSocketConstant.UPDATE_CONTACT_SESSION);
                }

            }

        }
    }
    public void hideAllMemberSession(int sessionId) throws IOException {
        // 获取这个会话的所有用户
        List<ContactSession> list = contactSessionService.getAllBySessionId(sessionId);
        // 将所有成员的该会话隐藏,并且向在线的用户发送更新会话列表信号
        for (ContactSession contactSession : list) {
            // 创建更新会话实体
            ContactSession contactSession1 = new ContactSession();
            // 将会话状态设为隐藏
            contactSession1.setIsHide(1);
            // 将会话id设为要修改的会话id
            contactSession1.setId(contactSession.getId());
            // 更新会话
            contactSessionService.updateContactSession(contactSession1);
            // 如果在线，就发送信号
            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(contactSession.getUserId().toString());
            if (webSocketServer != null && webSocketServer.session != null) {
                // 发送消息了
                System.out.println("发送消息了");
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_CONTACT_SESSION);
            }


        }
    }
}
