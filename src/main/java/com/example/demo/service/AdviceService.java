package com.example.demo.service;

import com.example.demo.VO.AdviceVO;
import com.example.demo.VO.ChatroomAdviceVO;
import com.example.demo.VO.ReportVO;
import com.example.demo.enity.Advice;
import com.example.demo.mapper.AdviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-08
 * Time: 0:51
 */
@Service
public class AdviceService {
    @Autowired
    AdviceMapper adviceMapper;
    /**
     * 根据key值查询举报信息
     * @param key 关键字符串
     * @return 举报信息列表
     */
    public List<ReportVO> getReportByKey(String key){
        return adviceMapper.selectReportByKey(key);
    }
    /**
     * 根据页号查询举报信息
     * @param index 页号
     * @return 举报信息列表
     */
    public List<ReportVO> getReportListByIndex(int index){
        // 计算通知表下标
        index = (index-1)*8;
        return adviceMapper.selectReportByIndex(index);
    }
    /**
     * 查询举报信息的总行数
     * @return 举报信息总行数
     */
    public int getTotalReportCount(){
        return adviceMapper.selectReportCount();
    }
    /**
     * 根据传来的通知实体更新通知信息
     * @param advice 通知实体
     * @return 数据库操作影响的行数
     */
    public int updateAdvice(Advice advice){
        return adviceMapper.updateAll(advice);
    }
    /**
     * 根据id获取advice
     * @param id advice的id
     * @return 查询到的实体类
     */
    public Advice getAdviceById(int id){
        return  adviceMapper.selectById(id);
    }
    /**
     * 插入聊天室通知，通知实体内部需要有chatroomId字段
     * @param advice 要插入的advice实体
     * @return 数据库操作影响行数
     */
    public int setAddChatroomAdvice(Advice advice){
        return adviceMapper.insertAddChatroomAdvice(advice);
    }

    /**
     * 插入好友请求
     * @param advice 好友请求实体
     * @return
     */
    public int setFriendAdvice(Advice advice){
        return adviceMapper.insertFriendAdvice(advice);
    }
    /**
     * 获取所有的好友请求
     * @param receiveId 接受者用户id
     * @return 好友请求列表
     */
    public List<AdviceVO> getAllFriendAdviceByReceiveId(int receiveId){
        return adviceMapper.selectAllFriendByReceiveId(receiveId);
    }

    /**
     * 获取所有聊天室请求
     * @param receivedId 接收用户id
     * @return 聊天室邀请和请求进入聊天室列表
     */
    public List<ChatroomAdviceVO>getAllChatroomAdviceByReceivedId(int receivedId){
        return adviceMapper.selectAllChatroomReqByReceiveId(receivedId);
    }

    /**
     * 获取所有的警告
     * @param receiveId 警告接收用户id
     * @return 警告信息列表
     */
    public List<Advice> getAllWarnAdviceByReceiveId(int receiveId){
        return adviceMapper.selectAllWarnByReceiveId(receiveId);
    }

    /**
     * 获取用户所有的举报信息
     * @param sendId 发送举报信息的用户id
     * @return 举报信息列表
     */
    public List<AdviceVO> getAllReportByReceivedId(int sendId){
        return adviceMapper.selectAllReportByReceiveId(sendId);
    }
}
