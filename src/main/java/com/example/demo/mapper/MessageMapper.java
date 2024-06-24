package com.example.demo.mapper;

import com.example.demo.VO.MessageVO;
import com.example.demo.enity.Message;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 11:32
 */
@Mapper
public interface MessageMapper {
        // 根据信息id查询信息
        public Message selectById(int id);
        // 根据sessionId查询最多两百条信息列表
        public List<MessageVO> selectAllBySessionId(int sessionId);
        // 根据sessionId查询两百条往后的所有信息
        public List<MessageVO> selectRestBySessionId(int sessionId);
        // 根据信息对象插入一条信息
        public int insertMessage(Message message);

}
