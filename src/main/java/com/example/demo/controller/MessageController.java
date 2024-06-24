package com.example.demo.controller;

import com.example.demo.VO.MessageVO;
import com.example.demo.common.*;
import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.ContactSession;
import com.example.demo.enity.Message;
import com.example.demo.enity.UserInfo;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-03
 * Time: 20:48
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    WebSocketUtil webSocketUtil;
    @Autowired
    MessageService messageService;
    @Autowired
    ContactSessionService contactSessionService;
    @Autowired
    SessionService sessionService;
    ManagerService managerService;
    ContactGroupService contactGroupService;
    UserService userService;
    ChatroomService chatroomService;

    /**
     * 获取200条信息之后的所有信息
     * @param sessionId 信息所属的会话id
     * @return 统一返回值
     */
    @RequestMapping("/get_rest_message")
    public MyResult getRestMessage(Integer sessionId,HttpSession session){
        if(session.getAttribute("permit") == null ){
            return MyResult.fail(MessageConstant.UN_PERMIT,"没有权限请求历史聊天记录");
        }
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取剩余消息列表
        List<MessageVO> list = messageService.getRestBySessionId(sessionId);
        return MyResult.success(list);
    }

    /**
     * 获取最多前200条的消息列表
     * @param sessionId 消息所属的会话id
     * @return 统一返回值
     */
    @RequestMapping("/get_message_list")
    public MyResult getMessageList(Integer sessionId){
        // 非空校验
        if(sessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取最多前两百条消息列表
        List<MessageVO> list = messageService.getAllBySessionId(sessionId);
        return MyResult.success(list);
    }

    /**
     * 接收客户端发送的消息，并通知同会话的所有在线用户
     * @param message 消息实体
     * @param session httpSession
     * @return 统一返回值
     * @throws IOException
     */
    @RequestMapping("/send_message")
    public MyResult sendMessage(Message message,Integer contactSessionId,HttpSession session) throws IOException {
        // 非空校验
        if(message == null || message.getContent() == null ){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询联系人会话
        ContactSession contactSession = contactSessionService.getById(contactSessionId);
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 检验身份，判断这个会话是不是当前用户的，且会话是否还可用
        if(userInfo.getId() != contactSession.getUserId() || contactSession.getIsDeleted() != 0 ){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你什么身份敢发这个消息？");
        }
        // 设置当前消息的sendId
        message.setSendId(userInfo.getId());
        // 设置当期那消息的种类
        message.setCategory(Message.TEXT);
        // 设置当前消息的会话id
        message.setSessionId(contactSession.getSessionId());
        // 持久化message
        messageService.setMessage(message);
        // 获取这个会话的所有用户
        webSocketUtil.noticeAllSameSessionUser(message.getSessionId(),userInfo.getId());
        return MyResult.success("发送成功",null);
    }

    /**
     * 接收客户端发送的图片信息，并通知同会话的所有在线用户
     * @param file 图片数据
     * @param sessionId 图片消息所属的sessionId
     * @param session httpSession
     * @return 统一返回值
     * @throws IOException
     */
    @RequestMapping("/send_picture")
    public MyResult sendPicture(MultipartFile file,Integer sessionId,Integer contactSessionId,HttpSession session) throws IOException {
        // 非空校验
        if(file == null || sessionId == null || contactSessionId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"后端未获取到信息");
        }
        // 检验身份
        // 查询联系人会话
        ContactSession contactSession = contactSessionService.getById(contactSessionId);
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(contactSession.getUserId() != userInfo.getId() || contactSession.getIsDeleted() != 0){
            return MyResult.fail(AdviceHandlerConstant.NOT_THE_HANDLER,"你不是这个会话的主人");
        }
        // 生成文件的唯一名称
        String newName = UUID.randomUUID().toString();
        // 获取源文件的后缀名
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
        newName = newName+suffix;
        File pictureFile = new File(PathConstant.MESSAGE_PATH+File.separator+newName);
        // 将数据存入文件
        try (OutputStream outputStream = new FileOutputStream(pictureFile)){
            // 创建文件
            pictureFile.createNewFile();
            // 将数据存入文件中
            outputStream.write(file.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 构造信息实体
        Message message =new Message();
        message.setCategory(Message.PICTURE);

        message.setSendId(userInfo.getId());
        message.setSessionId(sessionId);
        message.setContent("image"+File.separator+newName);
        // 持久化信息实体
        messageService.setMessage(message);
        // 向其他在线的用户发送信息
        webSocketUtil.noticeAllSameSessionUser(sessionId,userInfo.getId());
        return MyResult.success("发送文件成功",null);

    }

}
