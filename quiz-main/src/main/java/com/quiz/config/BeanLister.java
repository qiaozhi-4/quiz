package com.quiz.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * <p>
 * BeanLister 在应用启动时,输出所有bean
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/8 12:51
 */
@Component
@Log4j2
public class BeanLister implements CommandLineRunner {
    private final ApplicationContext ctx;

    public BeanLister(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public void run(String... args) {
        String[] beanNames = ctx.getBeanDefinitionNames();
        log.debug("应用程序上下文中的所有 Bean:");
        for (String beanName : beanNames) {
            log.debug(beanName);
        }
    }
}
