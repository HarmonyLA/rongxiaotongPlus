package com.Harmony.Controller;


import com.Harmony.Service.UserService;
import com.Harmony.api.json.ResponseResult;
import com.Harmony.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/doRegister")
    public ResponseResult doRegister(@RequestBody UserDTO userDTO) {
        return userService.register(userDTO);

    }
    @PostMapping("/doLogin")
        public ResponseResult doLogin(@RequestBody UserDTO userDTO) {
        return userService.Login(userDTO);

    }
}
