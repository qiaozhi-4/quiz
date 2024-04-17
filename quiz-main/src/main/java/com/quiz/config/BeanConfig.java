package com.quiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
     * 生成自定义序列化 LocalDateTime 类
     * </p>
     *
     * @return ObjectMapper
     */
    @NotNull
    private static ObjectMapper getObjectMapper() {
        final JavaTimeModule module = new JavaTimeModule();
        final ObjectMapper mapper = new ObjectMapper();

        //定义日期类型(LocalDateTime)的格式
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 注册LocalDateTime序列化和反序列化器
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
        // 如果需要，也可以添加反序列化器
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

        // 注册自定义的序列化程序和反序列化程序
        mapper.registerModule(module);

        // 关闭WRITE_DATES_AS_TIMESTAMPS功能，确保日期时间以字符串形式序列化
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper;
    }

    /**
     * <p>
     * 配置用來处理 JSON 数据的全局 bean
     * </p>
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper objectMapper() {
        return getObjectMapper();
    }

    /**
     * <p>
     * 配置用来处理 redis 序列化数据时添加类信息
     * </p>
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper redisObjectMapper() {
        final ObjectMapper mapper = getObjectMapper();

        // 保留类型信息，以便正确反序列化复杂对象
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL);
        return mapper;
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
