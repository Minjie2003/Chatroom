//package com.example.demo.mapper;
//
//import com.example.demo.enity.Message;
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
// * Date: 2023-11-30
// * Time: 17:04
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class MessageMapperTest {
//    @Autowired
////    MessageMapper mapper;
//    @Test
//    void insertMessage() {
//        Message message = new Message();
//        message.setCategory(1);
//        message.setContent("长夜将至");
//        message.setSendId(1);
//        message.setSessionId(1);
//        mapper.insertMessage(message);
//    }
//
//    @Test
//    void selectBySessionId() {
//        mapper.selectAllBySessionId(1);
//    }
//
//    @Test
//    void selectById() {
//        mapper.selectById(1);
//    }
//}