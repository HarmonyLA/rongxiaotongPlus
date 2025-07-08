package com.ldk;


import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class AppTest {


    @Test
    public void createJWT(){
        // 创建JWT对象
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken  = jwtBuilder
                // 设置header
                .setHeaderParam("type","JWT")
                .setHeaderParam("suanFa","HS256")
                //设置数据或者参数 payload
                .claim("username","ldk")
                .claim("loginRole","admin")
                // 设置有效时长
                .setExpiration(new Date(System.currentTimeMillis()+24*60*60*1000))
                .setId(UUID.randomUUID().toString())
                // 设置signature 签名
                .signWith(SignatureAlgorithm.HS256,"helloWorld")
                .compact();
        System.out.println(jwtToken);
    }
  //  eyJ0eXBlIjoiSldUIiwic3VhbkZhIjoiSFMyNTYiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImxkayIsImxvZ2luUm9sZSI6ImFkbWluIiwiZXhwIjoxNzUxNDQ0MzMwLCJqdGkiOiI2YzdiMThiMy0wMGM0LTQ1ODgtOTA1ZC0wZjRiNzk2MWQ5ZmEifQ.FhRvlJV2RdFbbYW43w_8vtKmmE03PspLZujsdZvOz7w


    @Test
    public void pareJWT(){
        String token ="eyJ0eXBlIjoiSldUIiwic3VhbkZhIjoiSFMyNTYiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImxkayIsImxvZ2luUm9sZSI6ImFkbWluIiwiZXhwIjoxNzUxNDQ0MzMwLCJqdGkiOiI2YzdiMThiMy0wMGM0LTQ1ODgtOTA1ZC0wZjRiNzk2MWQ5ZmEifQ.FhRvlJV2RdFbbYW43w_8vtKmmE03PspLZujsdZvOz7w";
        JwtParser parser = Jwts.parser();
        Jws<Claims> claimsJws = parser.setSigningKey("helloWorld").parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        System.out.println(body.get("username"));
        System.out.println(body.get("loginRole"));
        System.out.println(body.getId());
        System.out.println(body.getExpiration());

    }

}
