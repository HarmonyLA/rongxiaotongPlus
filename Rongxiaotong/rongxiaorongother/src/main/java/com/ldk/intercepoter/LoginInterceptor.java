package com.ldk.intercepoter;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ldk.api.json.ResponseResult;
import com.ldk.api.util.JWTToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


// 登录拦截器  前端的每一个请求必须经过这个拦截器 校验token 有token就放行请求 ，否则就不放行
@Component
public class LoginInterceptor implements HandlerInterceptor {
    // 在执行controller方法之前执行这个函数
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        // 判断前端的路径 有些路径必须放行
       /* if(request.getRequestURI().equals("/user/login") ||
                request.getRequestURI().equals("/user/add")){
                return  true;// 放行请求
        }*/
        // 获取前端传过来的token值
        String token = request.getHeader("token");
        System.out.println("token="+token);
        if( token == null || StrUtil.isEmpty(token)){
            ResponseResult result = new ResponseResult();
            result.setCode(201).setMessage("token无效");
            String jsonString = JSONObject.toJSONString(result);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonString);
            return  false;//拦截请求
        }
        // 校验token
        boolean flag = JWTToken.checkToken(token);
        if(!flag){
            ResponseResult result = new ResponseResult();
            result.setCode(202).setMessage("token无效");
            String jsonString = JSONObject.toJSONString(result);
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(jsonString);
            return  false;
        }


        return  true;// 放行
    }
}
