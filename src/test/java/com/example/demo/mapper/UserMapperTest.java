//package com.example.demo.mapper;
//
//import com.example.demo.enity.UserInfo;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User: qumingnan
// * Date: 2023-11-16
// * Time: 17:19
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class UserMapperTest {
//    @Autowired
//    UserMapper userMapper;
//    @Test
//    void getByAccountName() {
//        userMapper.selectByAccountName("hhh");
//    }
//
//    @Test
//    void updateUserinfo() {
//        UserInfo userInfo  = new UserInfo();
//        userInfo.setId(1);
//        userInfo.setBirthday(LocalDate.parse("2021-10-09",DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        userMapper.updateUserinfo(userInfo);
//    }
//
//    @Test
//    void selectUserByIndex() {
//        userMapper.selectUserByIndex(1);
//    }
//
//    @Test
//    void selectCount() {
//        userMapper.selectCount();
//    }
//}