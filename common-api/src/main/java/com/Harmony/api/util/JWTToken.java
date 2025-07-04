package com.Harmony.api.util;
import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.*;

import java.util.Date;

public class JWTToken {
    static String siganture="Harmony";
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
//    public static String createJWT() {
//        // 创建JWT对象
//        JwtBuilder jwtBuilder = Jwts.builder();
//        String jwtToken = jwtBuilder
//                // 设置header
//                .setHeaderParam("type", "JWT")
//                .setHeaderParam("suanFa", "HS256")
//                //设置数据或者参数 payload
//                .claim("username", "ldk")
//                .claim("loginRole", "admin")
//                // 可以设置主题
//                .setSubject("JWTTOKEN")
//                // 设置有效时长
//                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))
//                .setId(UUID.randomUUID().toString())
//                // 设置signature 签名
//                .signWith(SignatureAlgorithm.HS256, "Harmony's signature")
//                .compact();
//        System.out.println(jwtToken);
//        return jwtToken;
//
//    }
    public static boolean checkToken(String token) {

        // 校验token
            if(token == null){
                return  false;
            }
            try {
                Jws<Claims> claimsJws = Jwts.parser().setSigningKey(siganture).parseClaimsJws(token);
            }catch (Exception e){
                return  false;
            }
            return  true;
        }

}
