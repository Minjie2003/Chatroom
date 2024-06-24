package com.example.demo.service;

import com.example.demo.VO.MessageVO;
import com.example.demo.enity.Message;
import com.example.demo.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-03
 * Time: 20:45
 */
@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;

    /**
     * 返回前200条会话信息列表
     * @param sessionId 会话id
     * @return 信息列表
     */
    public List<MessageVO> getAllBySessionId(int sessionId) {
        return messageMapper.selectAllBySessionId(sessionId);
    }

    /**
     * 返回200条信息之后的所有信息
     * @param sessionId
     * @return
     */
    public List<MessageVO> getRestBySessionId(int sessionId){ return messageMapper.selectRestBySessionId(sessionId); }


    /**
     * 插入新信息信息
     * @param message 构造的信息对象
     * @return 数据库操作影响条数
     */
    public int setMessage(Message message){
        return messageMapper.insertMessage(message);
    }
}
