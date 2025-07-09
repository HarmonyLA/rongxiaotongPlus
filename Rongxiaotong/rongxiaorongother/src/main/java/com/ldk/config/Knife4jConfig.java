//package com.ldk.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springdoc.core.models.GroupedOpenApi;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * Knife4j整合Swagger3 Api接口文档
// */
//@Configuration
//@EnableConfigurationProperties(SwaggerProperties.class)
//public class Knife4jConfig {
//    @Autowired
//    private SwaggerProperties swaggerProperties;
//
//
//
//    @Bean
//    public GroupedOpenApi orderApi() { // 创建了一个api接口的分组
//        return GroupedOpenApi.builder()
//                .group("订单模块") // 分组名称
//                .pathsToMatch("/order/*") // 接口请求路径规则
//                .build();
//    }
//
//    @Bean
//    public OpenAPI openAPI(){
//        return new OpenAPI()
//                .info(new Info() // 基本信息配置
//                        .title(swaggerProperties.getTitle()) // 标题
//                        .description(swaggerProperties.getDescription()) // 描述Api接口文档的基本信息
//                        .version(swaggerProperties.getVersion()) // 版本
//                        .termsOfService(swaggerProperties.getTermsOfServiceUrl())
//                        // 设置OpenAPI文档的联系信息，包括联系人姓名为"patrick"，邮箱为"patrick@gmail.com"。
//                        .contact(new Contact().name(swaggerProperties.getName()).email(swaggerProperties.getEmail()))
//                        // 设置OpenAPI文档的许可证信息，包括许可证名称为"Apache 2.0"，许可证URL为"http://springdoc.org"。
//                        .license(new License().name("Apache 2.0").url("https://springdoc.org"))
//                );
//    }
//
//}