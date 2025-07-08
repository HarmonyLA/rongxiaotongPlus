package com.ldk.controller;


import com.ldk.api.dto.UserDTO;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.openfeign.BackUserAPI;
import com.ldk.api.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin
public class UserController {

    // 注入接口
    @Autowired
    BackUserAPI backUserAPI;

    // 请求路径
    @PostMapping("/user/add")
    public ResponseResult registerUser(@RequestBody UserDTO userDTO,User user){
        BeanUtils.copyProperties(userDTO,user);
        ResponseResult result = backUserAPI.doRegister(user);
        return  result;
    }

    @PostMapping("/user/login")
    public ResponseResult doLogin(@RequestBody UserDTO userDTO,User user){
        BeanUtils.copyProperties(userDTO,user);
        ResponseResult result = backUserAPI.doLogin(user);
        return result;
    }






}
