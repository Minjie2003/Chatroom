package com.example.demo.mapper;

import com.example.demo.VO.ChatroomVO;
import com.example.demo.enity.Chatroom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-26
 * Time: 22:47
 */
@Mapper
public interface ChatroomMapper {
    // 搜索聊天室通过name
    public List<Chatroom> selectByNameAndSessionId(String key);
    // 根据sessionId搜索聊天室
    public ChatroomVO selectBySessionId(int sessionId);
    // 更新聊天室对象
    public int updateById(Chatroom chatroom);
    // 插入一条聊天室数据
    public int insertChatroom(Chatroom chatroom);
    // 搜索聊天室总行数
    public int selectCount();
    // 根据下标分页搜索聊天室表
    public List<Chatroom> selectChatroomByIndex(int index);
}
