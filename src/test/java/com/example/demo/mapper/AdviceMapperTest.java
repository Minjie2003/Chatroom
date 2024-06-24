//package com.example.demo.mapper;
//
//import com.example.demo.enity.Advice;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User: qumingnan
// * Date: 2023-12-08
// * Time: 0:48
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class AdviceMapperTest {
//    @Autowired
//    AdviceMapper adviceMapper;
//    @Test
//    void selectAllByReceiveId() {
//        adviceMapper.selectAllFriendByReceiveId(1);
//    }
//
//    @Test
//    void selectAllChatroomReqByReceiveId() {
//        adviceMapper.selectAllChatroomReqByReceiveId(2);
//    }
//
//    @Test
//    void selectAllWarnByReceiveId() {
//        adviceMapper.selectAllWarnByReceiveId(1);
//    }
//
//    @Test
//    void insertFriendAdvice() {
//        Advice advice = new Advice();
//
//        advice.setCategory(1);
//        advice.setContent("hh");
//        advice.setReceiveId(21);
//        advice.setSendId(1);
//        adviceMapper.insertFriendAdvice(advice);
//    }
//
//    @Test
//    void selectReportCount() {
//        adviceMapper.selectReportCount();
//    }
//
//    @Test
//    void selectReportByIndex() {
//        adviceMapper.selectReportByIndex(0);
//    }
//
//
//    @Test
//    void selectReportByKey() {
//        adviceMapper.selectReportByKey("2");
//    }
//}