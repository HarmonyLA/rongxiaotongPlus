package com.ldk.api.openfeign;


import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "user-service")
public interface BackUserAPI {
    // 注册
    @PostMapping("/user/add")
    public ResponseResult doRegister(@RequestBody User user);
    // 登录
    @PostMapping("/user/doLogin")
    public ResponseResult doLogin(@RequestBody User user);

}
