package com.example.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.extra.mail.MailUtil;
import com.example.demo.VO.UserSelectVO;
import com.example.demo.common.*;
import com.example.demo.enity.ContactGroup;
import com.example.demo.enity.UserInfo;

import com.example.demo.service.ContactGroupService;
import com.example.demo.service.UserService;
import com.sun.mail.smtp.SMTPAddressFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * Description: 任何对于用户信息的修改，修改完数据库后，都必须修改session中的userinfo对象
 *              因为查询用户信息时，返回的是session中的userinfo值
 * User: qumingnan
 * Date: 2023-11-13
 * Time: 22:26
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ContactGroupService contactGroupService;

    /**
     * 用户注销下线
     * @param session 当前会话
     * @return 统一返回值
     */
    @RequestMapping("/logout")
    public MyResult logout(HttpSession session){
        session.removeAttribute("userinfo");


        return MyResult.success(null);
    }
    /**
     * 获取历史权限
     * @param password 密码
     * @param session 当前会话
     * @return 统一返回值
     */
    @RequestMapping("/get_permit")
    public MyResult getPermit(String password,HttpSession session){
        // 非空校验
        if(password == null || !StringUtils.hasLength(password)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"密码未被传递到后端");
        }
        // 获取用户密码
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 判断密码是否正确
        if(!password.equals(userInfo.getPassword())){
            // 不相等
            System.out.println("密码为"+userInfo.getPassword());
            return MyResult.fail(LoginConstant.PASSWORD_ERROR,"密码错误");
        }
        // 密码正确,设置权限
        session.setAttribute("permit",1);
        return MyResult.success("密码正确",null);

    }

    /**
     * 更新用户信息
     * @param userInfo 用户信息对象
     * @param session  当前会话
     * @return 统一返回值
     */
    @RequestMapping("/update_userinfo")
    public MyResult update_userinfo(UserInfo userInfo,HttpSession session){
        // 非空校验
        if(userInfo == null){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"参数未传递成功！");
        }
        // 修改数据库用户信息
        userService.updateUserinfo(userInfo);
        // 获取session中的用户信息
        UserInfo userInfo1= (UserInfo) session.getAttribute("userinfo");
        userInfo1 = userService.getByAccountNum(userInfo1.getAccountNum());
        // 更新session中的用户信息
        session.setAttribute("userinfo",userInfo1);
        return MyResult.success("修改成功");
    }

    /**
     * 头像更新函数
     * @param photo 头像文件
     * @param session 会话
     * @return 统一返回值
     * @throws IOException
     */
    @RequestMapping("/update_head")
    public MyResult update_head(MultipartFile photo,HttpSession session) throws IOException {
        // 非空判断
        if(photo.isEmpty() == true){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"没有获取到文件");
        }
        // 获取文件名
        String fileName = photo.getOriginalFilename();
        // 获取后缀
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        // 生成唯一文件名
        String photoFile = UUID.randomUUID().toString()+suffix;
        // 创建文件
        File file =new File(PathConstant.HEAD_PATH+File.separator+photoFile);
        file.createNewFile();
        // 创建文件输入流
        OutputStream outputStream = new FileOutputStream(file);
        // 输入文件数据
        outputStream.write(photo.getBytes());
        outputStream.flush();
        // 关闭输出流
        outputStream.close();

        // 更新头像路径
        UserInfo userInfo = new UserInfo();
        userInfo.setPhoto("head"+File.separator+photoFile);
        // 获取当前用户
        UserInfo userInfo1 = (UserInfo)session.getAttribute("userinfo");
        userInfo.setId(userInfo1.getId());
        userService.updateUserinfo(userInfo);
        // 修改session中的用户信息
        userInfo1.setPhoto(userInfo.getPhoto());
        session.setAttribute("userinfo",userInfo1);
        return MyResult.success("修改成功!");
    }
    /**
     * 搜索用户
     * @param key
     * @return 统一返回对象
     */
    @RequestMapping("select_user")
    public MyResult getContact(String key){
        // 非空判断
        if(!StringUtils.hasLength(key)){
            MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 获取用户列表
        List<UserSelectVO> list = userService.getByAccountNumOrUsername(key);
        // 返回结果
        return MyResult.success(list);

    }
    @RequestMapping("/get_contact_info")
    public MyResult getContactInfo(Integer id){
        // 非空校验
        if(id == null){
            return MyResult.fail(0);
        }
        // 获取联系人用户信息
        UserInfo contact = userService.getById(id);
        // 将密码置空
        contact.setPassword("");
        // 返回结果
        return MyResult.success(contact);
    }
    /**
     * 获取当前用户信息
     * @param session
     * @return
     */
    @RequestMapping("/get_userinfo")
    public MyResult getUserinfo(HttpSession session){
        // 获取Userinfo信息
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        // 存password
        UserInfo userInfo1 = userService.getById(userInfo.getId());
        // 抹去密码
        userInfo1.setPassword("");
        // 返回userinfo信息
        return MyResult.success("用户信息",userInfo1);

    }
    /**
     * 修改用户密码
     * @param password 新密码
     * @param session 会话
     * @return
     */
    @RequestMapping("/update_password")
    public MyResult updatePassword(String password,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(password)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL);
        }
        // 查看用户是否有修改权限
        Boolean isPermission = (Boolean)session.getAttribute(IdentifyConstant.PERMISSION);
        System.out.println(isPermission);
        if(isPermission == null || !isPermission){
            // 用户没有修改权限
           return MyResult.fail(UpdateConstant.NO_PERMISSION,"没有权限");
        }
        // 获取被修改的用户
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo == null){
            return MyResult.fail(UpdateConstant.NO_VERIFY_GET,"没有获取验证码！");
        }
        // 判断新密码和原密码是否一致
        if(password.equals(userInfo.getPassword())){
            return MyResult.fail(UpdateConstant.PASSWORD_SAME,"原密码和新密码一致！");
        }
        // 修改用户密码
        Integer num = userService.updatePassword(password,userInfo.getAccountNum());
        // 清除修改权限
        session.removeAttribute(IdentifyConstant.PERMISSION);

        return MyResult.success("用户密码修改成功",num);
    }
    /**
     * 验证修改账号的用户身份
     * @param accountNum 账号
     * @param verify 邮箱验证码
     * @param session 会话
     * @return 统一返回对象
     */
    @RequestMapping("/identify")
    public MyResult identify(String accountNum,String verify,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(accountNum) || !StringUtils.hasLength(verify)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"账号或验证码为空");
        }
        //判断账号是否正确
        UserInfo userInfo = (UserInfo) session.getAttribute("userinfo");
        if(userInfo == null){
            // 该用户没有获取验证码
            return MyResult.fail(IdentifyConstant.VERIFY_NOT_GET,"该用户未获取验证码!");
        }
        if(!userInfo.getAccountNum().equals(accountNum)){
            // 该账号不是获取验证码的邮箱
            return MyResult.fail(IdentifyConstant.ACCOUNT_NOT_SAME,"获取验证码账号和确认账号不一致 !");
        }
        // 检验验证码是否正确
        String code = (String)session.getAttribute(VerifyConstant.MAIL_VERIFY);
        if(code == null){
            // 用户未获取验证码
            return MyResult.fail(IdentifyConstant.VERIFY_NOT_GET,"该用户未获取验证码!");
        }
        boolean isCorrect = verify.equals(code);
        if(!isCorrect){
            // 验证码错误,包括验证码输错了或者该用户没有获取验证码两种情况
            return MyResult.fail(IdentifyConstant.VERIFY_ERROR,"验证码错误");
        }
        Boolean permission = true;
        // 身份确认成功
        session.setAttribute(IdentifyConstant.PERMISSION,permission);
        return MyResult.success("确认成功！",null);
    }
    /**
     * 获取修改密码的邮箱验证码
     * @param accountNum 账号
     */
    @RequestMapping("/get_update_mail_verify")
    public MyResult getUpdateMailVerify(String accountNum,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(accountNum)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"账号未输入！");
        }
        // 查询账户信息
        UserInfo userInfo = userService.getByAccountNum(accountNum);
        // 判断是否有账户
        if(userInfo == null){
            // 没有此账户
            return MyResult.fail(LoginConstant.USER_NOT_EXIST,"账号不存在！");
        }

        // 获取随机验证码
        Random random = new Random();
        Integer verify = random.nextInt(900000)+10000;
        String verifyCode = verify.toString();
        // 发送验证码
        MailUtil.send(userInfo.getMail(), "修改密码验证码", verifyCode, false);
        // 保存验证码
        session.setAttribute(VerifyConstant.MAIL_VERIFY,verifyCode);
        // 保存发送验证码的用户
        session.setAttribute("userinfo",userInfo);
        return MyResult.success("验证码发送成功!",null);
    }
    /**
     * 获取图像验证码
     * @param session 会话session
     * @param response 响应对象
     */
    @RequestMapping("/get_verify")
    public void getVerify(HttpSession session,HttpServletResponse response){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);

        //图形验证码写出，可以写出到文件，也可以写出到流
        try {
            lineCaptcha.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("image/png");
        // 将验证码存贮在session中
        session.setAttribute(VerifyConstant.IMAGE_VERIFY,lineCaptcha.getCode());


    }

    /**
     * 登录接口
     * @param accountNum 账号
     * @param password 密码
     * @param verify 验证码
     * @param session 会话session
     * @return 统一返回对象
     */
    @RequestMapping("/login")
    public MyResult login(String accountNum,String password,String verify,HttpSession session){
        // 非空校验
        if(!StringUtils.hasLength(accountNum) || !StringUtils.hasLength(password) || !StringUtils.hasLength(verify)) {
            return MyResult.fail(CommonConstant.PARAMETER_NULL, "参数有空字符串");
        }
        // 判断验证码是否正确
        Boolean isCorrect = verify.equals((String)session.getAttribute(VerifyConstant.IMAGE_VERIFY));
        if(!isCorrect){
            // 验证码错误
            return MyResult.fail(LoginConstant.VERIFY_ERROR,"验证码错误");
        }
        // 查询账号是否存在
        UserInfo userInfo = userService.getByAccountNum(accountNum);
        if(userInfo == null){
            // 账号不存在
            return MyResult.fail(LoginConstant.USER_NOT_EXIST,"账号或密码错误");
        }
        // 检查账号是否被封禁
        if(userInfo.getState() == UserInfo.BAN){
            // 账号被封禁
            return MyResult.fail(LoginConstant.USER_BANED,"账号已被封禁" );
        }
        // 判断密码是否正确
        if(!userInfo.getPassword().equals(password)){
            // 密码错误
            return MyResult.fail(LoginConstant.PASSWORD_ERROR,"账号或密码错误");
        }

        // 登录成功
        // 设置登录session
        session.setAttribute("userinfo",userInfo);
        // todo: websocket连接成功之后将登陆状态切换为在线
        return MyResult.success("登录成功",userInfo);
    }

    /**
     * 发送注册邮箱验证码
     * @param mail 邮箱地址
     * @param session 会话对象
     * @return 统一返回对象
     */

    @RequestMapping("/get_reg_mail_verify")
    public MyResult getRegMailVerify(String mail, HttpSession session) throws SMTPAddressFailedException {
        // 非空判断
        if(!StringUtils.hasLength(mail)){
            return MyResult.fail(CommonConstant.PARAMETER_NULL,"邮箱未输入！");
        }
        // 判断邮箱是否被注册
        UserInfo userInfo = userService.getByMail(mail);
        if(userInfo != null){
            return MyResult.fail(VerifyConstant.MAIL_USED,"该邮箱已经被注册！");
        }
        // 获取随机验证码
        Random random = new Random();
        Integer verify = random.nextInt(900000)+10000;
        String verifyCode = verify.toString();
        // 发送验证码
        MailUtil.send(mail, "注册验证码", verifyCode, false);

        // 保存验证码
        session.setAttribute(mail,verifyCode);
        return MyResult.success("验证码已经发送!",null);
    }
    /**
     * 注册接口 需要注册成一个事务
     * @param accountNum 注册账号
     * @param password 注册密码
     * @param mail 注册邮箱
     * @param mailVerify 邮箱验证码
     * @param request http请求
     * @return 统一返回对象
     */
    @Transactional
    @RequestMapping("/reg")
    public MyResult reg(String accountNum, String password, String mail, String mailVerify, HttpServletRequest request){
        // 非空校验
        if(!StringUtils.hasLength(accountNum) || !StringUtils.hasLength(mail) || !StringUtils.hasLength(mailVerify)
        || !StringUtils.hasLength(password)){
            return MyResult.fail(RegConstant.PARAMETER_NULL,"参数错误:参数含有空字符串！");
        }
        // 获取账户信息
        UserInfo userInfo = userService.getByAccountNum(accountNum);
        if(userInfo != null){
            return MyResult.fail(RegConstant.ACCOUNT_NUM_REPEAT,"参数错误：该账号已经存在！");
        }
        // 获取session
        HttpSession session = request.getSession(false);
        // 获取正确的邮箱验证码
        String mailVerifyCode = (String)session.getAttribute(mail);
        if(mailVerifyCode == null){
            return MyResult.fail(RegConstant.VERIFY_NOT_GET,"参数错误：请先获取邮箱验证码！");
        }
        // 判断用户输入的邮箱验证码是否正确
        if(!mailVerifyCode.equals(mailVerify)){
            return MyResult.fail(RegConstant.VERIFY_ERROR,"参数错误：验证码错误！");
        }

        // 插入账户数据
        Integer userid = userService.insertUserinfo(accountNum,password,mail);

        // 注册成功
        // 创建默认用户分组
        contactGroupService.setContactGroup("好友",userid,1, ContactGroup.USER_TO_USER);
        // 创建默认群聊分组
        contactGroupService.setContactGroup("聊天室",userid,1, ContactGroup.CHATROOM);
        return MyResult.success(userid);
    }

}

