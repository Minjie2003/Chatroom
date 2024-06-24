//package com.example.demo.mapper;
//
//import com.example.demo.enity.Session;
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
// * Time: 23:20
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class SessionMapperTest {
//    @Autowired
//    SessionMapper sessionMapper;
//    @Test
//    void selectById() {
//        sessionMapper.selectById(1);
//    }
//
//    @Test
//    void insertSession() {
//        Session session = new Session();
//        session.setCategory(Session.USER_TO_USER);
//        sessionMapper.insertSession(session);
//        System.out.println(session.getId());
//    }
//
//    @Test
//    void banSessionById() {
//        sessionMapper.banSessionById(1);
//    }
//}