package com.example.demo.mapper;

import com.example.demo.enity.ContactGroup;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-27
 * Time: 19:59
 */
@Mapper
public interface ContactGroupMapper {
    // 根据用户id,分组名，分组种类，是否默认插入分组信息
    int insertGroup(ContactGroup contactGroup);
    // 根据用户id查询分组信息
    List<ContactGroup> selectAllByUserId(int userId);
    // 根据用户id查询默认的好友分组
    ContactGroup selectDefaultFriendByUserId(int userId);
    // 根据用户id查询默认聊天室分组
    ContactGroup selectDefaultChatroomByUserId(int userId);
    // 更具分组id查询分组
    ContactGroup selectById(int id);
}
