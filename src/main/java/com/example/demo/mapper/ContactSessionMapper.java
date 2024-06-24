package com.example.demo.mapper;

import com.example.demo.VO.ContactSessionVO;

import com.example.demo.VO.UserSelectVO;
import com.example.demo.enity.ContactSession;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 17:24
 */
@Mapper
public interface ContactSessionMapper {
    // 根据用户id查询用户会话列表
    public List<ContactSessionVO> selectAllByUserId(int userId);
    // 根据会话id查询所有的用户会话
    public List<ContactSession> selectAllBySessionId(int sessionId);
    // 根据id更新用户session的unreadNum
    public int updateUnreadNumById(int unreadNum,int id);
    // 会话id查询统一会话的所有用户信息根据
    public List<UserSelectVO> selectAllChatMember(int sessionId);
    // 根据会话实体类id更新会话
    public int updateById(ContactSession session);
    // 删除会话根据用户id和会话id
    public int deleteByUserIdAndSessionId(int userId,int sessionId);
    // 插入一条联系人回话
    public int insertContactSession(ContactSession contactSession);
    // 根据用户id和联系人id查询会话
    public ContactSessionVO selectByUserIdAndContactId(int userId,int contactId,int category);
    // 根据会话id查询会话
    public ContactSession getById(int id);


}
