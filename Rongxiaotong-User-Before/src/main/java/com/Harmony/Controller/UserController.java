package com.Harmony.Controller;

import com.Harmony.api.json.ResponseResult;
import com.Harmony.api.openfeifn.UserBackendAPI;
import com.Harmony.api.dto.UserDTO;
import com.Harmony.api.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserBackendAPI userBackendAPI;
    @PostMapping("/register")
    public ResponseResult addUser(@RequestBody User userDTO, UserDTO user) {
        //由于这里是不涉及数据库，所以要使用dto去复制对象
        BeanUtils.copyProperties(userDTO,user);
        return userBackendAPI.doRegister(user);
    }
    @PostMapping("/login")
    public ResponseResult login(@RequestBody User userDTO, UserDTO user) {
        BeanUtils.copyProperties(userDTO,user);
        return userBackendAPI.doLogin(user);
    }
}
