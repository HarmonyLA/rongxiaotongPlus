package com.ldk;


import com.ldk.api.openfeign.BackUserAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.ldk.api.openfeign",
                    clients = {BackUserAPI.class})
public class UserBeforeApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(UserBeforeApplication.class,args);
    }
}
