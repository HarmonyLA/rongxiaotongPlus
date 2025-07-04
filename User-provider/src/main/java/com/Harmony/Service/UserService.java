package com.Harmony.Service;

import com.Harmony.api.json.ResponseResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.Harmony.api.dto.UserDTO;

public interface UserService extends IService<UserDTO> {
    ResponseResult register(UserDTO userDTO);

    ResponseResult Login(UserDTO userDTO);
}
