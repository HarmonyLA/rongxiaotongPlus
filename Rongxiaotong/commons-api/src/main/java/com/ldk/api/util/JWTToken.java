package com.ldk.api.util;


import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTToken {
    static String  siganture ="Jack";
    public static String createToken(Integer id,String username){
        // 创建JWT对象
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken  = jwtBuilder
                // 设置header
                .setHeaderParam("HS256","HS256")
                //设置数据或者参数 payload
                .claim("id",id)
                .claim("username",username)
                // 可以设置主题
                // 设置有效时长
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*100))
                .setId(UUID.randomUUID().toString())
                // 设置signature 签名
                .signWith(SignatureAlgorithm.HS256,siganture)
                .compact();
        return  jwtToken;
    }


    // 校验token  这个校验token方法暂时不用
    public static boolean checkToken(String token) {
        if(token == null){
            return  false;
        }
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(siganture).parseClaimsJws(token);
            // 数据可以从claimsJws 对象获取
        }catch (Exception e){
            return  false;
        }
        return  true;
    }

    // 根据 token解析出id 和 username 值
    public  static  String getUserNameFromToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(siganture).parseClaimsJws(token);
        //获取id
        Claims body = claimsJws.getBody();
        String username = (String)body.get("username");
        return  username;
    }

    public  static  Integer getIdFromToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(siganture).parseClaimsJws(token);
        //获取id
        Claims body = claimsJws.getBody();
        Integer id = (Integer)body.get("id");
        return  id;
    }


}
