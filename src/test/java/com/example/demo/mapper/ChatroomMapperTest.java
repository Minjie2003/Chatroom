//package com.example.demo.mapper;
//
//import com.example.demo.enity.Chatroom;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static java.time.LocalDateTime.now;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
//// * Created with IntelliJ IDEA.
//// * Description:
// * User: qumingnan
// * Date: 2023-12-01
// * Time: 0:16
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ChatroomMapperTest {
//    @Autowired
//    ChatroomMapper chatroomMapper;
//    @Test
//    void selectBySessionId() {
//        chatroomMapper.selectBySessionId(1);
//    }
//
//
//    @Test
//    void updateById() {
//        Chatroom chatroom =new Chatroom();
//        chatroom.setSessionId(1);
//        chatroom.setUpdateTime(now());
//        chatroomMapper.updateById(chatroom);
//    }
//
//    @Test
//    void selectCount() {
//        chatroomMapper.selectCount();
//    }
//
//    @Test
//    void selectChatroomByIndex() {
//        chatroomMapper.selectChatroomByIndex(0);
//    }
//}