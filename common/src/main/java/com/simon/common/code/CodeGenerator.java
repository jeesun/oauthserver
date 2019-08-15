package com.simon.common.code;

import com.google.common.base.CaseFormat;
import com.simon.common.utils.DbUtil;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
 * @author simon
 * @date 2018/09/22
 */
@Slf4j
public class CodeGenerator {

    /**
     * 项目基础包名称，根据自己的项目修改
     */
    public static String BASE_PACKAGE;

    /*生成文件地址配置*/
    /**
     * 生成的Model类所在包
     */
    private static String MODEL_PACKAGE;

    /**
     * 生成的Mapper所在包
     */
    private static String MAPPER_PACKAGE;

    /**
     * 生成Repository所在包
     */
    private static String REPOSITORY_PACKAGE;

    /**
     * 生成的Service所在包
     */
    private static String SERVICE_PACKAGE;

    /**
     * 生成的ServiceImpl所在包
     */
    private static String SERVICE_IMPL_PACKAGE;

    /**
     * 生成的Controller所在包
     */
    private static String CONTROLLER_PACKAGE;

    /**
     * Mapper插件基础接口的完全限定名(第二步提到的核心继承接口Mapper)
     */
    private static String MAPPER_INTERFACE_REFERENCE;

    /*数据库配置*/
    /**
     * 数据库url
     */
    public static String JDBC_URL;
    public static String JDBC_USERNAME;
    public static String JDBC_PASSWORD;
    public static String JDBC_DIVER_CLASS_NAME;

    /**
     * 项目在硬盘上的基础路径
     */
    static String PROJECT_PATH;

    /**
     * 模板位置
     */
    static String TEMPLATE_FILE_PATH;

    /**
     * java文件路径
     */
    static String JAVA_PATH;

    /**
     * 资源文件路径
     */
    static String RESOURCES_PATH;

    /**
     * 生成的mapper存放路径
     */
    private static String PACKAGE_PATH_MAPPER;

    /**
     * 生成的JPA Repository存放路径
     */
    private static String PACKAGE_PATH_REPOSITORY;

    /**
     * 生成的Service存放路径
     */
    private static String PACKAGE_PATH_SERVICE;

    /**
     * 生成的Service实现存放路径
     */
    private static String PACKAGE_PATH_SERVICE_IMPL;

    /**
     * 生成的Controller存放路径
     */
    private static String PACKAGE_PATH_CONTROLLER;

    /**
     * 要生成的模块
     */
    private static String GEN_MODULES;

    /**
     * @author
     */
    static String AUTHOR;

    /**
     * @date
     */
    static final String CREATE = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

    /**
     * 可选的要生成的代码部分
     */
    private static String MODULE_MODEL_AND_MAPPER = "modelandmapper";
    private static String MODULE_REPOSITORY = "repository";
    private static String MODULE_SERVICE = "service";
    private static String MODULE_CONTROLLER = "controller";
    private static String MODULE_CONTROLLER_AND_PAGE = "controllerandpage";

    private static Properties prop;
    static {
        prop = new Properties();
        try {
            prop.load(CodeGenerator.class.getResourceAsStream("/code-gen.properties"));
            JDBC_URL = prop.getProperty("jdbc_url");
            JDBC_USERNAME = prop.getProperty("jdbc_username");
            JDBC_PASSWORD = prop.getProperty("jdbc_password");
            JDBC_DIVER_CLASS_NAME = prop.getProperty("jdbc_driver_class_name");
            TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates/code";
            MAPPER_INTERFACE_REFERENCE = prop.getProperty("mapper_interface_reference");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CodeGenerator(){

    }


    public static void genCodeByCustomModelName(String mainOrTest, String basePackage, String moduleDir, String tableName, String tableComment, String modelName, String idType, String genModules, String author, EntityDataModel entityDataModel) {
        if (StringUtils.isEmpty(basePackage)) {
            throw new RuntimeException("缺少基础包名");
        }
        BASE_PACKAGE = basePackage;
        MODEL_PACKAGE = BASE_PACKAGE + ".model";
        MAPPER_PACKAGE = BASE_PACKAGE + ".mapper";
        REPOSITORY_PACKAGE = BASE_PACKAGE + ".repository";
        SERVICE_PACKAGE = BASE_PACKAGE + ".service";
        SERVICE_IMPL_PACKAGE = BASE_PACKAGE + ".service.impl";
        CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";

        PACKAGE_PATH_MAPPER = packageConvertPath(MAPPER_PACKAGE);
        PACKAGE_PATH_REPOSITORY = packageConvertPath(REPOSITORY_PACKAGE);
        PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);
        PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);
        PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);

        if (StringUtils.isEmpty(moduleDir)) {
            throw new RuntimeException("缺少要生成的模块目录");
        }
        PROJECT_PATH = System.getProperty("user.dir") + moduleDir;
        TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/templates/code";

        if(StringUtils.isEmpty(author)){
            throw new RuntimeException("缺少作者");
        }
        AUTHOR = author;

        if (StringUtils.isEmpty(genModules)){
            throw new RuntimeException("缺少要生成的模块说明");
        }

        if (StringUtils.isEmpty(mainOrTest)) {
            throw new RuntimeException("缺少要生成的位置配置是main还是test目录");
        }
        String javaPathPattern = "/src/{0}/java";
        String resourcesPathPattern = "/src/{0}/resources";
        JAVA_PATH = MessageFormat.format(javaPathPattern, mainOrTest);
        RESOURCES_PATH = MessageFormat.format(resourcesPathPattern, mainOrTest);

        File javaDir = Paths.get(PROJECT_PATH + JAVA_PATH + "/").toFile();
        File resourceDir = Paths.get(PROJECT_PATH + RESOURCES_PATH + "/").toFile();
        File mappingDir = Paths.get(PROJECT_PATH + RESOURCES_PATH + "/mapping/").toFile();
        if (!javaDir.exists()) {
            log.error("目录" + javaDir.getPath() + "不存在");
            if (javaDir.mkdirs()) {
                log.info("创建目录" + javaDir.getPath() + "成功！");
            } else {
                log.error("创建目录" + javaDir.getPath() + "失败！");
            }
        }
        if (!resourceDir.exists()) {
            log.error("目录" + resourceDir.getPath() + "不存在");
            if (resourceDir.mkdirs()) {
                log.info("创建目录" + resourceDir.getPath() + "成功！");
            } else {
                log.error("创建目录" + resourceDir.getPath() + "失败！");
            }
        }
        if (!mappingDir.exists()) {
            log.error("目录" + mappingDir.getPath() + "不存在");
            if (mappingDir.mkdirs()) {
                log.info("创建目录" + mappingDir.getPath() + "成功！");
            } else {
                log.error("创建目录" + mappingDir.getPath() + "失败！");
            }
        }

        if(StringUtils.isEmpty(genModules)){
            genModelAndMapper(tableName, tableComment, modelName, idType);
            genRepository(tableName, tableComment, modelName, idType);
            genService(tableName, tableComment, modelName, idType);
        }else{
            String[] modules = genModules.toLowerCase().split(",");

            if(Arrays.asList(modules).contains(MODULE_MODEL_AND_MAPPER)){
                genModelAndMapper(tableName, tableComment, modelName, idType);
            }
            if(Arrays.asList(modules).contains(MODULE_REPOSITORY)){
                genRepository(tableName, tableComment, modelName, idType);
            }
            if(Arrays.asList(modules).contains(MODULE_SERVICE)){
                genService(tableName, tableComment, modelName, idType);
            }
            if(Arrays.asList(modules).contains(MODULE_CONTROLLER)){
                genController(tableName, tableComment, modelName, idType);
            }
            if(Arrays.asList(modules).contains(MODULE_CONTROLLER_AND_PAGE)){
                if (null == entityDataModel){
                    genControllerAndPage(tableName, tableComment, modelName, idType);
                }else{
                    genControllerAndPage(tableName, tableComment, modelName, idType, entityDataModel);
                }
            }
        }
    }

    public static void genCodeByCustomModelName(String mainOrTest, String basePackage, String moduleDir, String tableName, String tableComment, String modelName, String idType, String genModules, String author) {
        genCodeByCustomModelName(mainOrTest, basePackage, moduleDir, tableName, tableComment, modelName, idType, genModules, author, null);
    }

    public static void genCodeByCustomModelName(String mainOrTest, String basePackage, String moduleDir, String tableName, String tableComment, String modelName, String idType, String genModules) {
        genCodeByCustomModelName(mainOrTest, basePackage, moduleDir, tableName, tableComment, modelName, idType, genModules, null);
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String mainOrTest, String basePackage, String moduleDir, String tableName, String tableComment, String modelName) {
        genCodeByCustomModelName(mainOrTest, basePackage, moduleDir, tableName, tableComment, modelName, "Long", null);
    }


    private static void genModelAndMapper(String tableName, String tableComment, String modelName, String idType) {
        Context context = new Context(ModelType.FLAT);
        context.setId("Potato");
        context.setTargetRuntime("MyBatis3Simple");
        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");

        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
        jdbcConnectionConfiguration.setConnectionURL(JDBC_URL);
        jdbcConnectionConfiguration.setUserId(JDBC_USERNAME);
        jdbcConnectionConfiguration.setPassword(JDBC_PASSWORD);
        jdbcConnectionConfiguration.setDriverClass(JDBC_DIVER_CLASS_NAME);
        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

        PluginConfiguration pluginConfiguration = new PluginConfiguration();
        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
        pluginConfiguration.addProperty("mappers", MAPPER_INTERFACE_REFERENCE);
        context.addPluginConfiguration(pluginConfiguration);

        //该代码不能满足要求
        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
        javaModelGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaModelGeneratorConfiguration.setTargetPackage(MODEL_PACKAGE);
        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
        sqlMapGeneratorConfiguration.setTargetProject(PROJECT_PATH + RESOURCES_PATH);
        sqlMapGeneratorConfiguration.setTargetPackage("mapping");
        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
        javaClientGeneratorConfiguration.setTargetProject(PROJECT_PATH + JAVA_PATH);
        javaClientGeneratorConfiguration.setTargetPackage(MAPPER_PACKAGE);
        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

        TableConfiguration tableConfiguration = new TableConfiguration(context);
        tableConfiguration.setTableName(tableName);
        if (StringUtils.isNotEmpty(modelName)){
            tableConfiguration.setDomainObjectName(modelName);
        }
        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Mysql", true, null));

        context.addTableConfiguration(tableConfiguration);

        List<String> warnings;
        MyBatisGenerator generator;
        try {
            Configuration config = new Configuration();
            config.addContext(context);
            config.validate();

            boolean overwrite = true;
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            warnings = new ArrayList<>();
            generator = new MyBatisGenerator(config, callback, warnings);
            generator.generate(null);
        } catch (Exception e) {
            throw new RuntimeException("生成Model和Mapper失败", e);
        }

        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
        }
        if (StringUtils.isEmpty(modelName)){
            modelName = tableNameConvertUpperCamel(tableName);
        }
        log.info(modelName + ".java 生成成功");
        log.info(modelName + "Mapper.java 生成成功");
        log.info(modelName + "Mapper.xml 生成成功");

        FreeMarkerGeneratorUtil.generatorMvcCode(
                DbUtil.getConnection(JDBC_DIVER_CLASS_NAME, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD),
                tableName,
                modelName,
                BASE_PACKAGE);
        //重新生成Mapper.java，覆盖自动生成的。
        reGenMapper(tableName, tableComment, modelName, idType);
    }

    /**
     * 使用模板重新生成mapper.java
     * @param tableName
     * @param modelName
     * @param idType
     */
    private static void reGenMapper(String tableName, String tableComment, String modelName, String idType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("tableName", tableName);
            data.put("tableComment", tableComment);
            data.put("AUTHOR", AUTHOR);
            data.put("CREATE", CREATE);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", modelNameConvertLowerCamel(modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("idType", idType);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_MAPPER + modelNameUpperCamel + "Mapper.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("mapper.ftl").process(data,
                    new FileWriter(file));
            log.info(modelNameUpperCamel + "Mapper.java 重新生成成功，文件位置{}", file.getPath());


        } catch (Exception e) {
            throw new RuntimeException("生成Mapper失败", e);
        }
    }

    private static void genRepository(String tableName, String tableComment, String modelName, String idType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("AUTHOR", AUTHOR);
            data.put("CREATE", CREATE);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", modelNameConvertLowerCamel(modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("idType", idType);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_REPOSITORY + modelNameUpperCamel + "Repository.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("repository.ftl").process(data,
                    new FileWriter(file));
            log.info(modelNameUpperCamel + "Repository.java 生成成功，文件位置：{}", file.getPath());

        } catch (Exception e) {
            throw new RuntimeException("生成repository失败", e);
        }
    }

    private static void genService(String tableName, String tableComment, String modelName, String idType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("AUTHOR", AUTHOR);
            data.put("CREATE", CREATE);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", modelNameConvertLowerCamel(modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("idType", idType);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("service.ftl").process(data,
                    new FileWriter(file));
            log.info(modelNameUpperCamel + "Service.java 生成成功，文件位置：{}", file.getPath());

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            cfg.getTemplate("service-impl.ftl").process(data,
                    new FileWriter(file1));
            log.info(modelNameUpperCamel + "ServiceImpl.java 生成成功，文件位置：{}", file1.getPath());
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    private static void genController(String tableName, String tableComment, String modelName, String idType) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("AUTHOR", AUTHOR);
            data.put("CREATE", CREATE);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("baseRequestMapping", modelNameConvertMappingPath(modelNameUpperCamel));
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", modelNameConvertLowerCamel(modelNameUpperCamel));
            data.put("basePackage", BASE_PACKAGE);
            data.put("idType", idType);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));

            log.info(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static void genControllerAndPage(String tableName, String tableComment, String modelName, String idType){
        PageGeneratorUtil.generatorPage(
                DbUtil.getConnection(JDBC_DIVER_CLASS_NAME, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD),
                tableName,
                modelName,
                CONTROLLER_PACKAGE);
    }

    private static void genControllerAndPage(String tableName, String tableComment, String modelName, String idType, EntityDataModel entityDataModel){
        PageGeneratorUtil.generatorPage(
                DbUtil.getConnection(JDBC_DIVER_CLASS_NAME, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD),
                tableName,
                modelName,
                CONTROLLER_PACKAGE, entityDataModel);
    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    public static String tableNameConvertLowerCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, tableName.toLowerCase());
    }

    private static String tableNameConvertUpperCamel(String tableName) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    }

    private static String tableNameConvertMappingPath(String tableName) {
        //兼容使用大写的表名
        tableName = tableName.toLowerCase();
        return "/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
    }

    private static String modelNameConvertMappingPath(String modelName) {
        String tableName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, modelName);
        return tableNameConvertMappingPath(tableName);
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

    private static String modelNameConvertLowerCamel(String modelName){
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelName);
    }

}