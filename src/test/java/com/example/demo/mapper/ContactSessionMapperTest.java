//package com.example.demo.mapper;
//
//import com.example.demo.enity.ContactSession;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User: qumingnan
// * Date: 2023-11-30
// * Time: 18:47
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ContactSessionMapperTest {
//    @Autowired
//    private ContactSessionMapper contactSessionMapper;
//    @Test
//    void selectAllByUserId() {
//        contactSessionMapper.selectAllByUserId(1);
//    }
//
//
//    @Test
//    void selectAllBySessionId() {
//        contactSessionMapper.selectAllBySessionId(2);
//    }
//
//    @Test
//    void updateUnreadNumById() {
//        contactSessionMapper.updateUnreadNumById(2,1);
//    }
//
//    @Test
//    void selectAllChatMember() {
//        contactSessionMapper.selectAllChatMember(2);
//    }
//
//    @Test
//    void updateById() {
//        ContactSession contactSession = new ContactSession();
//        contactSession.setContactId(1);
//        contactSession.setIsHide(1);
//        contactSessionMapper.updateById(contactSession);
//    }
//
//    @Test
//    void deleteByUserIdAndSessionId() {
//        contactSessionMapper.deleteByUserIdAndSessionId(1,1);
//
//    }
//
//    @Test
//    void insertContactSession() {
//        ContactSession contactSession1 = new ContactSession();
//        contactSession1.setSessionId(1);
//        contactSession1.setGroupId(2);
//        contactSession1.setNickName("hahha");
//        contactSession1.setContactId(1);
//        contactSession1.setUserId(2);
//        contactSessionMapper.insertContactSession(contactSession1);
//    }
//
//    @Test
//    void selectByUserIdAndContactId() {
//        contactSessionMapper.selectByUserIdAndContactId(1,2,1);
//    }
//
//
//}