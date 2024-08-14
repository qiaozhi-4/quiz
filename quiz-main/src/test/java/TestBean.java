import com.quiz.Main;
import com.quiz.constant.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * TestBean
 * </p>
 *
 * @author XGeorge
 * @since 2024/8/13 下午5:46
 */
@Log4j2
@SpringBootTest(classes = Main.class)
public class TestBean {

    // 自动注入Spring的应用上下文对象
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void allBeanName() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        log.info("应用程序上下文中的所有 Bean:");
        for (String beanName : beanNames) {
            log.info(beanName);
        }
    }

    @Test
    public void controllerBean() {
        // 获取所有标注了@Controller的bean
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(Controller.class);
        controllers.values().stream().filter(bean -> bean.getClass().getPackage().getName().startsWith("com.quiz.controller"))
                .map(bean -> {
                    HashMap<String, Object> map = new HashMap<>();
                    RequestMapping requestMapping = bean.getClass().getAnnotation(RequestMapping.class);
                    Api api = bean.getClass().getAnnotation(Api.class);
                    log.info("type: {} 路径:{} 描述:{}",
                            bean.getClass().getName(),
                            requestMapping != null ? requestMapping.value()[0] : "",
                            api != null ? api.tags()[0] : ""
                    );
                    Arrays.stream(bean.getClass().getMethods()).map(method -> {
                        HashMap<String, Object> map1 = new HashMap<>();
                        RequestMapping requestMapping1 = method.getAnnotation(RequestMapping.class);
                        ApiOperation annotation = method.getAnnotation(ApiOperation.class);
                        log.info("\t方法: {} 路径:{} 描述:{}",
                                method.getName(),
                                requestMapping1 != null ? requestMapping1.value()[0] : "",
                                annotation != null ? annotation.value() : ""
                        );
                        map1.put("方法", method.getName());
                        map1.put("路径", requestMapping1 != null ? requestMapping1.value()[0] : "");
                        map1.put("描述", annotation != null ? annotation.value() : "");
                        return map1;
                    });
                    map.put("type", bean.getClass().getName());
                    map.put("路径", requestMapping != null ? requestMapping.value()[0] : "");
                    map.put("描述", api != null ? api.tags()[0] : "");
                    return map;
                })
                .forEach(map -> {
//                    map.forEach((k, v) -> {
//                        log.info("{}: {}", k, v);
//                    });
//                    log.info("");
                });
    }

    @Test
    public void controllerPath() {
        // 获取所有请求映射信息
        RequestMappingHandlerMapping mapping = applicationContext.getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
        // 拿到Handler适配器中的全部方法
        Map<RequestMappingInfo, HandlerMethod> methodMap = mapping.getHandlerMethods();
        methodMap.entrySet().stream()
                // 过滤出controller层的方法
//                .filter(handlerMethod -> handlerMethod.getValue().getBeanType().getName().matches("com\\.quiz\\.controller.*"))
                .forEach(handlerMethod -> {
                    // 拿到请求方法
                    final String httpMethods = handlerMethod.getKey().getMethodsCondition().getMethods().stream()
                            .map(Enum::name).collect(Collectors.joining(Constants.SPACE_MARK));
                    assert handlerMethod.getKey().getPatternsCondition() != null;
                    // 拿到请求路径
                    final String patterns = String.join(Constants.SPACE_MARK, handlerMethod.getKey().getPatternsCondition().getPatterns());
                    // 拿到请求路径对应的ApiOperation注解
                    final ApiOperation apiOperation = handlerMethod.getValue().getMethodAnnotation(ApiOperation.class);
                    // 获取请求路径对应的描述
                    final String describe = apiOperation != null ? apiOperation.value() : null;

                    log.info("\t请求方法: {} 请求路径:{} 描述:{}",
                            httpMethods,
                            patterns,
                            describe
                    );
                });
    }
}
