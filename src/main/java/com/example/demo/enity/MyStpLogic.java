package com.example.demo.enity;

import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpLogic;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: qumingnan
 * Date: 2024-05-06
 * Time: 0:34
 */
public class MyStpLogic extends StpLogic {

    public MyStpLogic(String loginType){
        super(loginType);
    }
    @Override
    public void login(Object id, SaLoginModel loginModel) {
        String token = this.createLoginSession(id, loginModel);

    }
}
