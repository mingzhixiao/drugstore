package com.wzw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @program: drugstore
 * @description: swagger的基本配置
 * @author: wzw
 * @create: 2019-04-18 09:50
 */
@Configuration
@EnableSwagger2
public class MySwagger {
    private ApiInfo apiInfo(){
        Contact wzw = new Contact("明之晓", "", "");
        return new ApiInfoBuilder()
                .title("wzw的文档")
                .contact(wzw)
                .description("drugstore的api")
                .termsOfServiceUrl("http://127.0.0.1:8889/")
                .version("wzw.1.0")
                .build();
    }

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("第一版本")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzw.web"))
                .paths(PathSelectors.any())
                .build();
    }

/*    @Bean
    public Docket createRestApi2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("第二版本")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wzw.web"))
                .paths(PathSelectors.any())
                .build();
    }*/
}