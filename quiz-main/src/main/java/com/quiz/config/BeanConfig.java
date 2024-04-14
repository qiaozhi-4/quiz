package com.quiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

/**
 * <p>
 * BeanConfig
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/6 17:51
 */
@Configuration
public class BeanConfig {

    /**
     * <p>
     * 用來处理JSON数据的单例bean
     * </p>
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * <p>
     * 用来匹配路径
     * </p>
     *
     * @return AntPathMatcher
     */
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}
