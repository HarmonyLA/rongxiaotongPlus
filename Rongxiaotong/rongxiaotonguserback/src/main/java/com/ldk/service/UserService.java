package com.ldk.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.User;

public interface UserService extends IService<User> {

   // 注册
    ResponseResult register(User user);

    // 登录
    ResponseResult doLoginService(User user);
}
