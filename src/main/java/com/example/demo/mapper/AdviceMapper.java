package com.example.demo.mapper;


import com.example.demo.VO.AdviceVO;
import com.example.demo.VO.ChatroomAdviceVO;
import com.example.demo.VO.ReportVO;
import com.example.demo.enity.Advice;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-08
 * Time: 0:10
 */
@Mapper
public interface AdviceMapper  {
    // 查询一个用户的好友请求
    public List<AdviceVO> selectAllFriendByReceiveId(int receiveId);
    // 查询一个用户的所有的聊天室请求，包括好友请求
    public List<ChatroomAdviceVO> selectAllChatroomReqByReceiveId(int receiveId);
    // 查询一个用户的所有的举报信息
    public List<AdviceVO> selectAllReportByReceiveId(int sendId);
    // 查询一个用户的所有警告信息
    public List<Advice> selectAllWarnByReceiveId(int receivedId);
    // 向一个用户发送好友请求信息
    public int insertFriendAdvice(Advice advice);
    // 向一个聊天室群主发送请求入群信息
    public int insertAddChatroomAdvice(Advice advice);
    // 根据id获取advice
    public Advice selectById(int id);
    // 更新一个通知信息更具通知实体
    public int updateAll(Advice advice);
    // 查询举报信息的总行数
    public int selectReportCount();
    // 根据下标查询举报信息列表
    public List<ReportVO> selectReportByIndex(int index);
    // 根据举报人用户名、id 获取 被举报的用户名或id查询举报信息
    public List<ReportVO> selectReportByKey(String key);


}
