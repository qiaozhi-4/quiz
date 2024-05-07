package com.code.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * FreemarkerCode
 * </p>
 *
 * @author XGeorge
 * @since 2024/5/6 14:27
 */
public class FreemarkerCode {

    private static Connection conn;

    /**
     * 获取数据库连接
     */
    public static Statement getConn() throws SQLException, ClassNotFoundException {
        // 数据库连接信息
        final String url = "jdbc:mysql://127.0.0.1:3306/quiz";
        final String username = "root";
        final String password = "root";

        // 加载 MySQL 驱动程序
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (conn == null || conn.isClosed())
            // 建立数据库连接
            conn = DriverManager.getConnection(url, username, password);
        return conn.createStatement();
    }

    /**
     * 获取所有表信息
     */
    public static List<HashMap<String, Object>> getTable() throws SQLException, ClassNotFoundException {
        // 创建 Statement 对象
        Statement stmt = getConn();
        // 执行 SQL 查询
        String sql = "SELECT\n" +
                "\tTABLE_NAME AS 表名,\n" +
                "\tTABLE_COMMENT AS 表注释\n" +
                "FROM\n" +
                "\tINFORMATION_SCHEMA.TABLES\n" +
                "WHERE\n" +
                "\tTABLE_SCHEMA = 'dingdong';";
        ResultSet rs = stmt.executeQuery(sql);
        final ArrayList<HashMap<String, Object>> tables = new ArrayList<>();
        // 处理查询结果
        while (rs.next()) {
            final HashMap<String, Object> table = new HashMap<>();
            // 从结果集中获取数据
            final String name = rs.getString(1);
            table.put("name", name);
            table.put("comment", rs.getString(2));
            table.put("fields", getField(name));
            // 处理查询结果
            tables.add(table);
        }
        return tables;
    }

    /**
     * 获取表字段信息
     *
     * @param tableName 表名
     */
    public static List<HashMap<String, Object>> getField(String tableName) throws SQLException, ClassNotFoundException {
        // 创建 Statement 对象
        Statement stmt = getConn();
        // 执行 SQL 查询
        String sql = "SELECT\n" +
                "\tCOLUMN_NAME AS 字段名,\n" +
                "\tDATA_TYPE AS 数据类型,\n" +
                "\tCOLUMN_COMMENT AS 字段注释\n" +
                "FROM\n" +
                "\tINFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE\n" +
                "\tTABLE_SCHEMA = 'dingdong'\n" +
                "\tAND TABLE_NAME = '" + tableName + "';";
        ResultSet rs = stmt.executeQuery(sql);
        // 处理查询结果

        final ArrayList<HashMap<String, Object>> fields = new ArrayList<>();
        while (rs.next()) {
            final HashMap<String, Object> field = new HashMap<>();
            // 从结果集中获取数据
            final String propertyName = rs.getString(1);
            final String propertyType = rs.getString(2);
            final String comment = rs.getString(3);
            // 处理查询结果
            field.put("propertyName", propertyName);
            field.put("propertyType", getFieldType(propertyType));
            field.put("comment", comment);
            fields.add(field);
        }
        return fields;
    }

    public static String getFieldType(String dataType) {
        // 忽略大小写比较
        if (dataType.equalsIgnoreCase("int"))
            return "int";
        if (dataType.equalsIgnoreCase("real"))
            return "double";
        return "QString";
    }

    public static void main(String[] args) throws IOException, TemplateException, SQLException, ClassNotFoundException {
        // 创建一个Configuration对象，用于加载模板文件
        Configuration configuration = new Configuration(Configuration.getVersion());
        // 创建一个Template对象，用于处理模板文件
        Template template;

        // 设置模板文件的加载路径
        configuration.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir") + "/code-generator/src/main/resources/templates/ftl"));
        // 获取模板文件
        template = configuration.getTemplate("entity.h.ftl");

        // 获取表信息
        final List<HashMap<String, Object>> tables = getTable();
        tables.forEach(t -> {
            try {
                // 获取表名
                final String tableName = (String) t.get("name");

                // 准备模板数据
                Map<String, Object> data = new HashMap<>();
                data.put("table", t);
                data.put("macroName", tableName.toUpperCase() + "_H");

                // 准备输出文件
                final String fileName = String.format("D:\\code\\test\\%s.h", tableName);
                File output = new File(fileName);

                // 创建输出文件
                FileWriter writer = new FileWriter(output);

                // 将模板文件中的数据填充到Map对象中
                template.process(data, writer);

                // 关闭输出流
                writer.close();
            } catch (TemplateException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

