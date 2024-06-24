package com.example.demo.mapper;


import com.example.demo.VO.UserSelectVO;
import com.example.demo.enity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-16
 * Time: 16:58
 */
@Mapper
public interface UserMapper {
    // 根据用户accountName查询用户
    public UserInfo selectByAccountName(String accountNum);
    // 根据用户mail查询用户
    public UserInfo selectByMail(String mail);
    // 插入用户信息
    public Integer InsertUserinfo(UserInfo userInfo);
    // 修改用户密码
    public Integer updatePassword(String password,String accountNum);
    // 搜索用户通过account_num 或者 username
    public List<UserSelectVO> selectByAccountNumOrUsername(String key);
    // 根据用户id查询用户
    public UserInfo selectById(int userId);
    // 更新用户信息
    public int updateUserinfo(UserInfo userinfo);
    // 分页查询用户列表
    public List<UserInfo> selectUserByIndex(int index);
    // 查询用户表的总行数
    public int selectCount();
}
