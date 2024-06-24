package com.example.demo.mapper;

import com.example.demo.enity.Manager;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-12-15
 * Time: 14:12
 */
@Mapper
public interface ManagerMapper {
    // 查询管理员信息
    public Manager selectManager(String accountNum);
}
