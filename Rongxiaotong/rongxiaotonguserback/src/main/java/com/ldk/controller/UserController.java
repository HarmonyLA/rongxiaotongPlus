package com.ldk.controller;


import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.User;
import com.ldk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // 注册
    @PostMapping("/user/add")
    public ResponseResult doRegister(@RequestBody User user){
        ResponseResult result = userService.register(user);
        return  result;
    }

    // 登录
    @PostMapping("/user/doLogin")
    public ResponseResult doLogin(@RequestBody User user){
        ResponseResult result = userService.doLoginService(user);
        return  result;
    }


}
