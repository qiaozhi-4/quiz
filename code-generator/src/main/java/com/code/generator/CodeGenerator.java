package com.code.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.*;

/**
 * <p>
 * CodeGenerator
 * </p>
 *
 * @author XGeorge
 * @since 2024/4/2 14:41
 */
public class CodeGenerator {
    /**
     * 当前项目路径
     */
    private final static String PROJECT_PATH = System.getProperty("user.dir");
    /**
     * 基本路径
     */
    private static final String SRC_MAIN_JAVA = "/src/main/java/";
    /**
     * 子模块路径
     */
    private static final String SUB_MODULE = "/quiz-main";
    /**
     * entity路径
     */
    private static final String ENTITY_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/java/com/quiz/entity/";
    /**
     * service路径
     */
    private static final String SERVICE_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/java/com/quiz/service/";
    /**
     * serviceImpl路径
     */
    private static final String SERVICE_IMPL_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/java/com/quiz/service/impl/";
    /**
     * mapper路径
     */
    private static final String MAPPER_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/java/com/quiz/mapper/";
    /**
     * xml路径
     */
    private static final String XML_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/resources/mappers/";
    /**
     * controller路径
     */
    private static final String CONTROLLER_PATH = PROJECT_PATH + SUB_MODULE + "/src/main/java/com/quiz/controller/";
    /**
     * 数据连接路径
     */
    private final static String URL = "jdbc:mysql://101.34.56.241:3306/quiz";
    /**
     * 数据连接账号
     */
    private final static String USERNAME = "root";
    /**
     * 数据连接密码
     */
    private final static String PASSWORD = "U2FsdGVkX1/w";
    /**
     * 需要替换的表前缀
     */
    public static final String REGEX = "^\\w+?(?=[A-Z])";

    /**
     * <p>
     * 处理 all 情况
     * </p>
     *
     * @param tables 所有表名组合的字符串
     * @return List<String> 表名集合
     */
    private static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }


    private static Map<OutputFile, String> getPathInfo() {
        Map<OutputFile, String> pathInfo = new HashMap<>(5);
        pathInfo.put(OutputFile.entity, ENTITY_PATH);
        pathInfo.put(OutputFile.service, SERVICE_PATH);
        pathInfo.put(OutputFile.serviceImpl, SERVICE_IMPL_PATH);
        pathInfo.put(OutputFile.mapper, MAPPER_PATH);
        pathInfo.put(OutputFile.xml, XML_PATH);
        pathInfo.put(OutputFile.controller, CONTROLLER_PATH);
        return pathInfo;
    }

    /**
     * <p>
     * 单模块代码生成器
     * </p>
     */
    public static void generator() {
        /*基础配置*/
        FastAutoGenerator.create(URL, USERNAME, PASSWORD)
                /*全局配置*/
                .globalConfig(builder ->
                        builder

                                .disableOpenDir()/*禁止打开输出目录*/
                                /*.outputDir(PROJECT_PATH + "/src/main/java")*//*指定输出目录*/
                                .author("XGeorge")/*作者名*/
                                //.enableKotlin()/*开启 kotlin 模式*/
                                .enableSwagger()/*开启 swagger 模式*/
                                .dateType(DateType.TIME_PACK)/*时间策略*/
                                .commentDate("yyyy-MM-dd")/*注释时间格式*/
                )
                /*包配置*/
                .packageConfig(builder ->
                        builder.parent("com.quiz")/*包名*/
                                /*.moduleName(moduleName)*//*模块名*/
                                .entity("entity")/*Entity 包名*/
                                .service("service")/*Service 包名*/
                                .serviceImpl("service.impl")/*Service Impl 包名*/
                                .mapper("mapper")/*Mapper 包名*/
                                .xml("mapper.xml")/*Mapper XML 包名*/
                                .controller("controller")/*Controller 包名*/
                                /*.other("other")*//*自定义文件包名--输出自定义文件时所用到的包名*/
                                .pathInfo(getPathInfo())/*路径配置信息*/
                )
                /*策略配置*/
                .strategyConfig(builder ->
                        builder
//                                .addInclude("t_user")
//                                .addInclude("t_user_auth")
//                                .addInclude("t_role")
//                                .addInclude("t_permission")
//                                .addInclude("t_path")
//                                .addInclude("t_user_roles")
//                                .addInclude("t_role_permissions")
                                .addInclude("q_prop")
                                .addInclude("q_user_prop")
//                                .addInclude("q_paper")
//                                .addInclude("q_tag")
//                                .addInclude("q_question")
//                                .addInclude("q_answers")
//                                .addInclude("q_paper_questions")
//                                .addInclude("q_paper_tags")
//                                .addInclude("q_question_tags")
//                                .addInclude("q_classes")
//                                .addInclude("q_paper_classes")
//                                .addInclude("yz_device")
//                                .addInclude("yz_use_info")
                                //.likeTable(new LikeTable("", SqlLike.RIGHT))

                                /*配置 Entity*/
                                .entityBuilder()
                                /*.disable()*/
                                .javaTemplate("\\templates\\ftl\\entity.java")//设置模版路径
                                .enableFileOverride()//开启文件覆盖
                                .enableChainModel()//开启链式模型
                                .enableLombok()//开启Lombok
                                .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                                //开启 ActiveRecord 模型(封装了实体一些简单持久化操作,必须有对应的BaseMapper实现)
                                .enableActiveRecord()
                                //配置生成文件的名字
                                .convertFileName(entityName -> entityName.replaceAll(REGEX, ""))

                                /*配置 Service*/
                                .serviceBuilder()
                                /*.disable()*/
                                .serviceTemplate("\\templates\\ftl\\service.java")//设置模版路径
                                .serviceImplTemplate("\\templates\\ftl\\serviceImpl.java")//设置模版路径
                                .enableFileOverride()//开启文件覆盖
                                .convertServiceFileName(entityName ->
                                        "I" + entityName.replaceAll(REGEX, "") + "Service")
                                .convertServiceImplFileName(entityName ->
                                        entityName.replaceAll(REGEX, "") + "ServiceImpl")

                                /*配置 Mapper*/
                                .mapperBuilder()
                                .disable()
                                .mapperTemplate("\\templates\\ftl\\mapper.java")
                                .mapperXmlTemplate("\\templates\\ftl\\mapper.xml")
                                .enableFileOverride()//开启文件覆盖
                                .enableBaseResultMap()
                                //.enableBaseColumnList()
                                .convertMapperFileName(entityName ->
                                        entityName.replaceAll(REGEX, "") + "Mapper")
                                .convertXmlFileName(entityName ->
                                        entityName.replaceAll(REGEX, "") + "Mapper")

                                /*配置 Controller*/
                                .controllerBuilder()
                                .disable()//禁用生成
                                .template("\\templates\\ftl\\controller.java")//设置模版路径
                                .enableFileOverride()//开启文件覆盖
                                .enableRestStyle()//开启生成@RestController 控制器(等同于@Controller + @ResponseBody。)
                                .convertFileName(entityName ->
                                        entityName.replaceAll(REGEX, "") + "Controller"))
                /*模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker (需要导入相应包)*/
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }


    public static void main(String[] args) {
        generator();
    }
}
