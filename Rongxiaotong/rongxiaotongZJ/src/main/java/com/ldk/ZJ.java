package com.ldk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients
@MapperScan("com.ldk.mapper")
public class ZJ
{
    public static void main( String[] args )
    {

        SpringApplication.run(ZJ.class, args);
    }
}
