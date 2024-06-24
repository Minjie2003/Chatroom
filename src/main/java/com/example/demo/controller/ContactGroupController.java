package com.example.demo.controller;

import com.example.demo.common.CommonConstant;
import com.example.demo.common.MyResult;
import com.example.demo.enity.ContactGroup;
import com.example.demo.enity.UserInfo;
import com.example.demo.service.ContactGroupService;
import com.example.demo.service.ContactSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-02
 * Time: 17:08
 */
@RestController
@RequestMapping("/contact_group")
public class ContactGroupController {
    @Autowired
    ContactGroupService contactGroupService;

    /**
     * 创建分组
     * @param name 分组名称
     * @param category 分组种类
     * @param session 会话
     * @return
     */
    @RequestMapping("/create_group")
    public MyResult createGroup(String name,Integer category,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(name) || category==null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取用户id
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        int num = contactGroupService.setContactGroup(name,userInfo.getId(),0,category);
        return MyResult.success(num);
    }

    /**
     * 获取好友分组列表
     * @param session 当前会话
     * @return
     */
    @RequestMapping("/get_group")
    public MyResult getGroupList(HttpSession session){
        // 获取用户信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 查询分组信息
        List<ContactGroup> list= contactGroupService.getAllContactGroupById(userInfo.getId());
        return MyResult.success(list);
    }
}
