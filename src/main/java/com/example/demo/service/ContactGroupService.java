package com.example.demo.service;


import com.example.demo.enity.ContactGroup;
import com.example.demo.mapper.ContactGroupMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-27
 * Time: 20:08
 */
@Service
public class ContactGroupService {
    @Autowired
    ContactGroupMapper contactGroupMapper;

    public ContactGroup getContactGroupById(int id){
        return  contactGroupMapper.selectById(id);
    }
    /**
     * 查询默认的用户分组
     * @param userId 用户id
     * @return 查询到的分组
     */
    public ContactGroup getDefaultFriendGroup(int userId){
        return contactGroupMapper.selectDefaultFriendByUserId(userId);
    }
    /**
     * 查询默认的聊天室分组
     * @param userId 用户id
     * @return 查询到的分组
     */
    public ContactGroup getDefaultChatroomGroup(int userId){
        return contactGroupMapper.selectDefaultChatroomByUserId(userId);
    }
    /**
     * 加入一个新的分组
     * @param name 分组名
     * @param userId 分组id
     * @param isDefault 是否是默认分组
     * @param category 分组种类
     * @return 数据库操作行数
     */
    public int setContactGroup(String name,int userId,int isDefault,int category){
        ContactGroup contactGroup = new ContactGroup();
        contactGroup.setUserId(userId);
        contactGroup.setIsDefault(isDefault);
        contactGroup.setName(name);
        contactGroup.setCategory(category);
        return contactGroupMapper.insertGroup(contactGroup);
    }

    /**
     * 获取某个用户的所有分组
     * @param userId 用户id
     * @return 分组列表
     */
    public List<ContactGroup> getAllContactGroupById(int userId){
        return contactGroupMapper.selectAllByUserId(userId);
    }
}
