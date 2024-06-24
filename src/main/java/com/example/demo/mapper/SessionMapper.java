package com.example.demo.mapper;

import com.example.demo.enity.Session;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-30
 * Time: 23:14
 */
@Mapper
public interface SessionMapper {
    // 根据sessionId获取session对象
    public Session selectById(int id);
    // 根据session实体对象插入一条session数据
    public int insertSession(Session session);
    // 根据id封禁会话
    public int banSessionById(int id);
}
