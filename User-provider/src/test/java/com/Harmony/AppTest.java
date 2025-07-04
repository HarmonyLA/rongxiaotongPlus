package com.Harmony;

import cn.hutool.core.lang.UUID;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest{
    @Test
    public void createJWT() {
        // 创建JWT对象
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // 设置header
                .setHeaderParam("type", "JWT")
                .setHeaderParam("suanFa", "HS256")
                //设置数据或者参数 payload
                .claim("username", "ldk")
                .claim("loginRole", "admin")
                // 可以设置主题
                .setSubject("JWTTOKEN")
                // 设置有效时长
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 100))
                .setId(UUID.randomUUID().toString())
                // 设置signature 签名
                .signWith(SignatureAlgorithm.HS256, "Harmony's signature")
                .compact();
        System.out.println(jwtToken);

    }
}
//public class AppTest extends TestCase {
//
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//
//    public AppTest(String testName) {
//        super(testName);
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite() {
//        return new TestSuite(AppTest.class);
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp() {
//        assertTrue(true);
//    }
//
//
//}
