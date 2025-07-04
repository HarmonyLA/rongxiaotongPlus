package com.Harmony.api.openfeifn;


import com.Harmony.api.json.ResponseResult;
import com.Harmony.api.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "cloud-provider-service")
public interface UserBackendAPI {

    @PostMapping("/user/doRegister")
    public ResponseResult doRegister(@RequestBody UserDTO userDTO);
     @PostMapping("/user/doLogin")
     public ResponseResult doLogin(@RequestBody UserDTO userDTO);
}
