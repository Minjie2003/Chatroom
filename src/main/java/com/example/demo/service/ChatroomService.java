package com.example.demo.service;

import com.example.demo.VO.ChatroomVO;
import com.example.demo.enity.Chatroom;
import com.example.demo.mapper.ChatroomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-26
 * Time: 23:29
 */
@Service
public class ChatroomService {
    @Autowired
    ChatroomMapper chatroomMapper;

    /**
     * 查询聊天室总行数
     * @return 聊天室总行数
     */
    public int getTotalCount(){
        return chatroomMapper.selectCount();
    }
    /**
     * 根据页号查询聊天室列表
     * @param index 页号
     * @return 聊天室列表
     */
    public List<Chatroom> getChatroomListByIndex(int index){
        // 计算聊天室表下标
        index = (index-1)*8;
        return chatroomMapper.selectChatroomByIndex(index);
    }
    /**
     * 插入一条聊天室数据
     * @param chatroom 新聊天室信息
     * @return 数据库操作返回值
     */
    public int setChatroom(Chatroom chatroom){
       return chatroomMapper.insertChatroom(chatroom);
    }
    /**
     * 根据传来的聊天室实体修改聊天室信息
     * @param chatroom 携带新消息的聊天室实体
     * @return 数据操作影响行数
     */
    public int updateChatroom(Chatroom chatroom){
        return chatroomMapper.updateById(chatroom);
    }
    /**
     * 通过关键字搜索聊天室信息
     * @param key 关键字
     * @return 搜索到的聊天室结构集合
     */
    public List<Chatroom> getAllByNameAndSessionId(String key){
        return chatroomMapper.selectByNameAndSessionId(key);
    }

    /**
     * 获取聊天室对象根据会话id
     * @param sessionId
     * @return
     */
    public ChatroomVO getBySessionId(int sessionId){
        return chatroomMapper.selectBySessionId(sessionId);
    }
}
