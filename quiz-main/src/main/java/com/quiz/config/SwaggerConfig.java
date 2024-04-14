package com.quiz.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * <p>
 * SwaggerConfig
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/5 8:53
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("main")//分组名称
                .apiInfo(new ApiInfoBuilder()
                        .title("接口文档")
                        .contact(new Contact("XGeorge", "https://gitee.com/georgedbddbbd", "18890176107@163.com"))
                        .description("接口文档")
                        .version("1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build();
    }

}
