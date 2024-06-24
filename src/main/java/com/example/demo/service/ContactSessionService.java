package com.example.demo.service;

import com.example.demo.VO.ContactSessionVO;

import com.example.demo.VO.UserSelectVO;
import com.example.demo.enity.ContactSession;
import com.example.demo.mapper.ContactSessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 23:22
 */
@Service
public class ContactSessionService {
    @Autowired
    ContactSessionMapper contactSessionMapper;
    public ContactSession getById(int id){
        return contactSessionMapper.getById(id);
    }
    /**
     * 通过用户id和联系人id来获取联系人回话，可能存在查到两个会话的请框，一个是聊天室会话，一个事用户之间的私聊会话
     * @param userId 用户id
     * @param contactId 联系人id
     * @return 会话列表
     */
    public ContactSessionVO getByUserIdAndContactId(int userId, int contactId,int category){
        return contactSessionMapper.selectByUserIdAndContactId(userId,contactId,category);
    }
    /**
     * 插入一条新的联系人会话
     * @param contactSession 新的联系人会话信息
     * @return 数据库操作影响条数
     */
    public int setContactSession(ContactSession contactSession){
        return contactSessionMapper.insertContactSession(contactSession);
    }
    /**
     * 删除联系人会话，通过用户id和会话id查找
     * @param userId 用户id
     * @param sessionId 会话id
     * @return 数据库操作影响的数据条数
     */
    public int deleteContactSessionByUserIdAndSessionId(int userId,int sessionId){
        return contactSessionMapper.deleteByUserIdAndSessionId(userId,sessionId);
    }
    /**
     * 根据被传来的contactSession对象修改contactSession
     * @param session 要修改的实体参数
     * @return 数据库操作的影响行数
     */
    public int updateContactSession(ContactSession session){
        return contactSessionMapper.updateById(session);
    }
    /**
     * 查询用户会话列表,用来返回给前端
     * @param userId 用户id
     * @return 查询到的用户会话列表
     */
    public List<ContactSessionVO>  getUserSessionList(int userId){
        return contactSessionMapper.selectAllByUserId(userId);
    }

    /**
     *  获取同一会话的用户会话列表
     * @param sessionId
     * @return
     */
    public List<ContactSession> getAllBySessionId(int sessionId){
        return contactSessionMapper.selectAllBySessionId(sessionId);
    }

    /**
     * 更新用户绘画的未读信息数目
     * @param contactSession 传来的变化得了饿unredanum的会话实体
     * @return 数据库操作影响行数
     */
    public int updateContactSessionUnReadNum(ContactSession contactSession){
        return contactSessionMapper.updateUnreadNumById(contactSession.getUnreadNum(),contactSession.getId());
    }

    public List<UserSelectVO> getAllMemberBySessionId(int sessionId){
        return contactSessionMapper.selectAllChatMember(sessionId);
    }
}
