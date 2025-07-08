package com.ldk.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.pojo.User;
import com.ldk.api.util.JWTToken;
import com.ldk.mapper.UserMapper;
import com.ldk.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>  implements UserService {
    @Override
    public ResponseResult register(User user) {
        // 判断数据  非空判断
        if(user == null){
           return new ResponseResult(201,"注册信息必填",null,null);
        }
        if("".equals(user.getUserName()) || user.getUserName() == null){
        return new ResponseResult(202,"注册名必填",null,null);
        }

        if("".equals(user.getNickName()) || user.getNickName() == null){
            return new ResponseResult(203,"注册昵称必填",null,null);
        }

        if("".equals(user.getPassword()) || user.getPassword() == null){
            return new ResponseResult(204,"注册密码必填",null,null);
        }
        // 注册业务
        // 根据注册 名和 密码查询当前用户是否注册过
        //QueryWrapper 用来封装查询条件的
        QueryWrapper<User>  wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName())
                .eq("password",user.getPassword());
        User data = this.getOne(wrapper);
        // 对查询结果进行判断
        if(data != null ){
            return new ResponseResult(205,"该注册名已被使用",null,null);
        }
        // 进行注册
        boolean flag = this.save(user);
        // 对 flag 判断
        if(!flag){
            return  new ResponseResult(206,"注册失败,稍后重试!",null,null);
        }

        return  new ResponseResult(200,"注册成功",null,null);
    }

    @Override
    public ResponseResult doLoginService(User user) {
        // 判断数据  非空判断
        if(user == null){
            return new ResponseResult(201,"登录信息必填",null,null);
        }
        if("".equals(user.getUserName()) || user.getUserName() == null){
            return new ResponseResult(202,"登录名必填",null,null);
        }

        if("".equals(user.getPassword()) || user.getPassword() == null){
            return new ResponseResult(204,"登录密码必填",null,null);
        }
        // 判断该账号有没有注册
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",user.getUserName());
        User user1 = this.getOne(wrapper);
        if(user1 == null){
            return  new ResponseResult(205,"该账号没有注册",null,null);
        }
        // 根据用户名和密码查找用户  然后去比对注册时的账号和密码是否一致
        QueryWrapper<User> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("user_name",user.getUserName())
                .eq("password",user.getPassword());
        User data = this.getOne(wrapper1);
        if(data  == null){
            return new ResponseResult(206,"登录信息填写有误",null,null);
        }
        // 登录成功  需要返回数据给前端 token 和 其他信息
        // 下发token
        String token = JWTToken.createToken(data.getId(), data.getUserName());
        //将token 放哪里？？？？
        data.setToken(token);
        return new ResponseResult(200,"登录成功",data,null);
    }
}
