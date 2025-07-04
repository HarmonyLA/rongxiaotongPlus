package com.Harmony.Service.impl;

import com.Harmony.Service.UserService;
import com.Harmony.api.json.ResponseResult;
import com.Harmony.api.dto.UserDTO;
import com.Harmony.api.util.JWTToken;
import com.Harmony.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDTO> implements UserService {
    @Override
    public ResponseResult register(UserDTO userDTO) {
        if (userDTO ==null){
            return new ResponseResult().setCode(500).setMsg("register information is missing").setData(null);
        }
        if ("".equals(userDTO.getUserName())|| userDTO.getUserName()==null){
            return new ResponseResult().setCode(501).setMsg("register name is required").setData(null);
        }
        if ("".equals(userDTO.getPassword())|| userDTO.getPassword()==null){
            return new ResponseResult().setCode(502).setMsg(" password is null").setData(null);
        }
        QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDTO.getUserName());
        UserDTO data=this.getOne(queryWrapper);
        if (data!=null){
            return new ResponseResult().setCode(504).setMsg("register user already exist").setData(null);
        }else {

            boolean flag= this.save(userDTO);
            if (!flag){
                return new ResponseResult().setCode(505).setMsg("register unsuccess").setData(userDTO);
            }
            return new ResponseResult().setCode(200).setMsg("register success").setData(null);

        }
    }

    @Override
    public ResponseResult Login(UserDTO userDTO) {
        // 参数校验
        if (userDTO == null) {
            return new ResponseResult().setCode(500).setMsg("Login information is missing").setData(null);
        }

        if (userDTO.getUserName() == null || "".equals(userDTO.getUserName())) {
            return new ResponseResult().setCode(501).setMsg("Login name is required").setData(null);
        }

        if (userDTO.getPassword() == null || "".equals(userDTO.getPassword())) {
            return new ResponseResult().setCode(502).setMsg("Password is required").setData(null);
        }

        // 一次性查询用户名和密码
        QueryWrapper<UserDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userDTO.getUserName())
                .eq("password", userDTO.getPassword()); // 注意：实际项目中应使用加密后的密码

        UserDTO data = this.getOne(queryWrapper);

        if (data == null) {
            return new ResponseResult().setCode(505).setMsg("Username or password is incorrect").setData(null);
        }
        String token = JWTToken.createToken(data.getId(),data.getUserName());
        data.setToken(token);
        // 登录成功，返回用户信息或token
        return new ResponseResult().setCode(200).setMsg("Login success").setData(data);
    }
}
