package com.example.demo.service;

import com.example.demo.enity.Manager;
import com.example.demo.mapper.ManagerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-15
 * Time: 14:11
 */
@Service
public class ManagerService {
    @Autowired
    ManagerMapper managerMapper;
    public Manager getManagerByAccountNum(String accountNum){
        return managerMapper.selectManager(accountNum);
    }
}
