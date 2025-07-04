package com.Harmony;

import com.Harmony.api.openfeifn.UserBackendAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@CrossOrigin
@EnableFeignClients(basePackages = "com.Harmony.api.openfeifn" ,clients = {UserBackendAPI.class})
public class UserFrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserFrontApplication.class, args);
    }
}
