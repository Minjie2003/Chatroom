package com.example.demo.controller;

import com.example.demo.VO.ReportVO;
import com.example.demo.VO.UserSelectVO;
import com.example.demo.common.*;
import com.example.demo.component.WebSocketServer;
import com.example.demo.enity.*;

import com.example.demo.service.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-15
 * Time: 1:13
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    ManagerService managerService;
    @Autowired
    UserService userService;
    @Autowired
    ChatroomService chatroomService;
    @Autowired
    AdviceService adviceService;
    @Autowired
    SessionService sessionService;
    /**
     * 管理员登录接口
     * @param accountNum 账号
     * @param password，密码
     * @param verify 验证码
     * @param session 会话
     * @return 统一返回值
     */
    @RequestMapping("/manager_login")
    public MyResult managerLogin(String accountNum,String password,String verify,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(accountNum) || !StringUtils.hasLength(password) || !StringUtils.hasLength(verify)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 检查验证码
        String key = (String) session.getAttribute(VerifyConstant.IMAGE_VERIFY);
        if(key == null || !key.equals(verify)){
            return MyResult.fail(LoginConstant.VERIFY_ERROR);
        }
        // 获取管理员数据
        Manager manager = managerService.getManagerByAccountNum(accountNum);
        if(manager == null){
            return MyResult.fail(LoginConstant.USER_NOT_EXIST);
        }
        // 比对密码
        if(manager.getPassword().equals(password)){
            session.setAttribute("manager",manager);
            return  MyResult.success("登录成功");
        }else{
            return MyResult.fail(LoginConstant.PASSWORD_ERROR);
        }
    }

    /**
     * 获取对应页号的举报信息
     * @param index 页号
     * @return
     */
    @RequestMapping("/get_page_report")
    public MyResult getPageReport(Integer index){
        // 非空校验
        if(index == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }

        // 查询举报信息列表
        List<ReportVO> list = adviceService.getReportListByIndex(index);
        // 返回聊天室列表
        return MyResult.success(list);
    }

    /**
     * 获取对应页号的聊天室列表
     * @param index 页号
     * @return
     */
    @RequestMapping("/get_page_chatroom")
    public MyResult getPageChatroom(Integer index){
        // 非空校验
        if(index == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }

        // 查询聊太室列表
        List<Chatroom> list = chatroomService.getChatroomListByIndex(index);
        // 返回聊天室列表
        return MyResult.success(list);
    }

    /**
     * 获取对应页号的用户信息
     * @param index 页号
     * @return
     */
    @RequestMapping("/get_page_user")
    public MyResult getPageUser(Integer index){
        // 非空校验
        if(index == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询用户列表
        List<UserInfo> list = userService.getUserListByPageIndex(index);
        // 返回用户列表
        return MyResult.success(list);

    }
    /**
     * 获取举报信息页面的最大下标
     * @return
     */
    @RequestMapping("/get_report_max_index")
    public MyResult get_report_max_index(){
        // 查询通知信息的行数
        int lines = adviceService.getTotalReportCount();
        int max_index = lines/8 + 1;
        return MyResult.success(max_index);
    }
    /**
     * 获取聊天室页面的最大下标
     * @return
     */
    @RequestMapping("/get_chatroom_max_index")
    public MyResult get_chatroom_max_index(){
        // 查询聊天室信息的行数
        int lines = chatroomService.getTotalCount();
        int max_index = lines/8 + 1;
        return MyResult.success(max_index);
    }

    /**
     * 获取用户页面的最大下标
     * @return
     */
    @RequestMapping("/get_user_max_index")
    public MyResult get_user_max_index(){
        // 查询用户信息的行数
        int lines = userService.getTotalLine();
        int max_index = lines/8 + 1;
        return MyResult.success(max_index);
    }

    /**
     * 查询用户信息
     * @param key 关键字字符串
     * @return
     */
    @RequestMapping("/search_user")
    public MyResult searchUser(String key){
        // 非空校验
        if(!StringUtils.hasLength(key)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询用户列表
        List<UserSelectVO> list = userService.getByAccountNumOrUsername(key);
        // 返回查询结果
        return MyResult.success(list);
    }

    /**
     * 查询聊天室
     * @param key 关键字字符串
     * @return
     */
    @RequestMapping("/search_chatroom")
    public MyResult searchChatroom(String key){
        // 非空校验
        if(!StringUtils.hasLength(key)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询chatroom列表
        List<Chatroom> list = chatroomService.getAllByNameAndSessionId(key);
        return MyResult.success(list);
    }

    /**
     * 查询举报信息
     * @param key 关键字符串
     * @return
     */
    @RequestMapping("/search_report")
    public MyResult searchReport(String key){
        // 非空校验
        if(!StringUtils.hasLength(key)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查询chatroom列表
        List<ReportVO> list = adviceService.getReportByKey(key);
        return MyResult.success(list);
    }

    /**
     * 封禁用户
     * @param id 用户id
     * @param adviceId 通知id
     * @return
     */
    @RequestMapping("/ban_user")
    public MyResult banUser(Integer id,Integer adviceId) throws IOException {
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 封禁用户
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setState(UserInfo.BAN);
        int num = userService.updateUserinfo(userInfo);
        // 判断是否是因举报而封禁
        if(adviceId != null){
            // 跟新举报处理结果
            Advice report  = new Advice();
            report.setResult(Advice.BAN);
            report.setId(adviceId);
            adviceService.updateAdvice(report);
            // 如果举报者在线，提醒举报者
            Advice advice = adviceService.getAdviceById(adviceId);

            WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(advice.getSendId().toString());
            // 判断是否在线
            if(webSocketServer != null){
                webSocketServer.sendMessage(WebSocketConstant.UPDATE_REPORT);
            }
        }
        return MyResult.success(num);
    }

    /**
     * 封禁聊天室
     * @param id 聊天室id
     * @return
     */
    @RequestMapping("/ban_chatroom")
    public MyResult banChatroom(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 封禁聊天室
        int num = sessionService.banSessionById(id);
        return MyResult.success(num);
    }

    /**
     * 解封用户
     * @param id 用户id
     * @return
     */
    @RequestMapping("/recover_user")
    public MyResult recoverUser(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 解封用户
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setState(UserInfo.OFFLINE);
        int num = userService.updateUserinfo(userInfo);
        return MyResult.success(num);
    }

    /**
     * 重置用户密码
     * @param id 用户id
     * @return
     */
    @RequestMapping("/reset_password")
    public MyResult resetPassword(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 重置密码
        UserInfo userInfo = new UserInfo();
        userInfo.setPassword("000000");
        userInfo.setId(id);
        int num = userService.updateUserinfo(userInfo);
        return MyResult.success(num);
    }

    /**
     * 对被举报用户发出警告
     * @param id 用户id
     * @param content 举报内容
     * @param adviceId 通知信息id
     * @return
     */
    @RequestMapping("/warn_user")
    public MyResult warnUser(Integer id,String content,Integer adviceId) throws IOException {
        // 非空校验
        if(id == null || content ==null || adviceId == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 发出警告信息
        Advice advice = new Advice();
        advice.setCategory(Advice.WARNING_INFO);
        advice.setSendId(0);
        advice.setReceiveId(id);
        advice.setContent(content);
        int num = adviceService.setFriendAdvice(advice);
        // 更新举报处理结果
        Advice report = new Advice();
        report.setId(adviceId);
        report.setResult(Advice.WARN);
        adviceService.updateAdvice(report);
        // 如果被警告用户在线通知该用户
        WebSocketServer webSocketServer = WebSocketServer.webSocketMap.get(id.toString());
        // 判断是否在线
        if(webSocketServer != null){
            webSocketServer.sendMessage(WebSocketConstant.UPDATE_WARN);
        }
        // 举报用户在线通通知该用户
        advice = adviceService.getAdviceById(adviceId);
        // 如果举报者在线，通知举报者更新举报列表
        webSocketServer = WebSocketServer.webSocketMap.get(advice.getSendId().toString());
        // 判断是否在线
        if(webSocketServer != null){
            webSocketServer.sendMessage(WebSocketConstant.UPDATE_REPORT);
        }
        return MyResult.success(num);
    }

    /**
     * 验证管理员操作密码是否正确
     * @param password 验证密码
     * @param session 当前会话
     * @return
     */
    @RequestMapping("/identify")
    public MyResult identify(String password,HttpSession session){
        // 非空校验
        if(password == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取当前登录的管理员
        Manager manager = (Manager) session.getAttribute("manager");
        // 判断密码是否正确
        if(manager.getPassword().equals(password)){
            return MyResult.success(null);
        }else{
            return MyResult.fail(1);
        }
    }
}
