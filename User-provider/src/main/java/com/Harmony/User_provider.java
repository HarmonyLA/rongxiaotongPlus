package com.Harmony;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient

public class User_provider
{
    public static void main(String[] args) {
        SpringApplication.run(User_provider.class, args);
    }
}
