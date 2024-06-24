package com.example.demo.service;


import com.example.demo.VO.UserSelectVO;
import com.example.demo.enity.UserInfo;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2023-11-16
 * Time: 16:57
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询index页的所有用户信息
     * @param index 页号
     * @return 用户信息列表
     */
    public List<UserInfo> getUserListByPageIndex(int index){
        // 计算用户表下标
        index = (index-1)*8;
        return userMapper.selectUserByIndex(index);
    }
    /**
     * 获取用户表的总行数
     * @return 总行数
     */
    public int getTotalLine(){
        return userMapper.selectCount();
    }
    /**
     * 根据用户id查询用户
     * @param userId 用户id
     * @return 用户信息
     */
    public UserInfo getById(int userId){
        return userMapper.selectById(userId);
    }
    /**
     * 更新用户信息
     * @param userInfo 用户修改的信息对象
     * @return 数据库操作行数
     */
    public int updateUserinfo(UserInfo userInfo){
       return userMapper.updateUserinfo(userInfo);
    }
    /**
     * 根据用户名和账号搜索系统用户
     * @param key 关键字
     * @return
     */
    public List<UserSelectVO> getByAccountNumOrUsername(String key){
        return userMapper.selectByAccountNumOrUsername(key);
    }
    /**
     * 根据账号查用户信息
     * @param accountNum 账号
     * @return 用户信息
     */
    public UserInfo getByAccountNum(String accountNum){
        return userMapper.selectByAccountName(accountNum);
    }

    /**
     * 根据邮箱获取用户信息
     * @param mail 邮箱
     * @return 查询到的用户信息
     */
    public UserInfo getByMail(String mail){
        return userMapper.selectByMail(mail);
    }

    /**
     * 根据用户名，密码，邮箱插入用户信息
     * @param accountNum 账号
     * @param password 密码
     * @param mail 邮箱
     * @return 插入数据库的信息的id
     */
    public int insertUserinfo(String accountNum,String password,String mail){
        LocalDateTime createTime = LocalDateTime.now();
        UserInfo userInfo = new UserInfo();
        userInfo.setAccountNum(accountNum);
        userInfo.setPassword(password);
        userInfo.setMail(mail);
        userInfo.setCreateTime(createTime);
        userMapper.InsertUserinfo(userInfo);
        return userInfo.getId();
    }

    /**
     * 修改用户密码
     * @param password 密码
     * @param accountNum 账号
     * @return 数据库操作影响行数
     */
    public int updatePassword(String password,String accountNum){
        return userMapper.updatePassword(password,accountNum);
    }

}
