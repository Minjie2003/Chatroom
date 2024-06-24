//package com.example.demo.mapper;
//
//import com.example.demo.enity.ContactGroup;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * Created with IntelliJ IDEA.
// * Description:
// * User: qumingnan
// * Date: 2023-11-27
// * Time: 21:26
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ContactGroupMapperTest {
//    @Autowired
//    ContactGroupMapper contactGroupMapper;
//    @Test
//    void insertGroup() {
//        ContactGroup contactGroup =new ContactGroup();
//        contactGroup.setUserId(1);
//        contactGroup.setName("王者");
//        contactGroup.setCategory(1);
//        contactGroupMapper.insertGroup(contactGroup);
//    }
//
//    @Test
//    void selectByUserId() {
//        contactGroupMapper.selectAllByUserId(1);
//    }
//
//    @Test
//    void selectDefaultFriendByUserId() {
//        contactGroupMapper.selectDefaultFriendByUserId(1);
//
//    }
//}