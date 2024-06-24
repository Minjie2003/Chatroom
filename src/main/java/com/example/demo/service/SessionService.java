package com.example.demo.service;

import com.example.demo.enity.Session;
import com.example.demo.mapper.SessionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-01
 * Time: 0:07
 */
@Service
public class SessionService {
    @Autowired
    SessionMapper sessionMapper;
    public int  banSessionById(int id){
        return sessionMapper.banSessionById(id);
    }
    /**
     * 根据session实体类插入一条session数据
     * @param session session实体
     * @return 数据操作影响行数
     */
    public int setSession(Session session){
        return sessionMapper.insertSession(session);
    }
    /**
     * 根据id查询一个会话
     * @param sessionId 会话id
     * @return 会话实体
     */
    public Session getById(int sessionId){
        return sessionMapper.selectById(sessionId);
    }
}
