package com.sus.maxus.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * 项目名：yl
 * 包名：com.sus.maxus
 * 文件名 : AutoCodeUtils
 *
 * @author zjq
 * @date 2019/8/9 9:03
 * 描述：
 */
public class AutoCodeUtils {

    private static String filePath;
    private static String packagePath;
    private static String[] tableNames;
    private static String xmlPath;
    private static String controller;
    private static String service;
    private static String serviceImpl;
    private static String entity;
    private static String mapper;
    private static String driverClassName;
    private static String url;
    private static String username;
    private static String password;
    private static Boolean fileOverride;
    private static Boolean baseResultMap;
    private static Boolean enableCache;
    private static Boolean entityLombokModel;
    private static Boolean restControllerStyle;
    private static String author;
    private static String controllerName;
    private static String serviceName;
    private static String serviceImplName;
    private static String mapperName;
    private static String xmlName;


    static {
        ResourceBundle bundle = ResourceBundle.getBundle("CodeAutoGeneration");
        //全局文件输出路径
        filePath = bundle.getString("filePath");
        // 包路径配置
        packagePath = bundle.getString("packagePath");
        //需要生成的表
        tableNames = bundle.getString("tableNames").split("\\.");
        //xml的生成路径
        xmlPath = bundle.getString("xmlPath");
        //项目结构包名
        controller = bundle.getString("controller");
        service = bundle.getString("service");
        serviceImpl = bundle.getString("serviceImpl");
        mapper = bundle.getString("mapper");
        entity = bundle.getString("entity");
        // 数据源配置
        driverClassName = bundle.getString("driverClassName");
        url = bundle.getString("url");
        username = bundle.getString("username");
        password = bundle.getString("password");
        //生成策略
        
        fileOverride = Boolean.valueOf(bundle.getString("fileOverride"));
        baseResultMap = Boolean.valueOf(bundle.getString("baseResultMap"));
        enableCache = Boolean.valueOf(bundle.getString("enableCache"));
        entityLombokModel = Boolean.valueOf(bundle.getString("entityLombokModel"));
        restControllerStyle = Boolean.valueOf(bundle.getString("restControllerStyle"));
        //作者信息
        author = bundle.getString("author");
        //生成的类名信息
        controllerName = bundle.getString("controllerName");
        serviceName = bundle.getString("serviceName");
        serviceImplName = bundle.getString("serviceImplName");
        mapperName = bundle.getString("mapperName");
        xmlName = bundle.getString("xmlName");

    }


    public static void main(String[] args) {
        autoCode();
    }

    public static void autoCode() {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //不需要更改
        gc.setOutputDir(filePath + "/java");
        gc.setFileOverride(fileOverride);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(enableCache);
        // XML ResultMap
        gc.setBaseResultMap(baseResultMap);
        // XML columList
        gc.setBaseColumnList(false);
        // 作者
        gc.setAuthor(author);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName(controllerName);
        gc.setServiceName(serviceName);
        gc.setServiceImplName(serviceImplName);
        gc.setMapperName(mapperName);
        gc.setXmlName(xmlName);
        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(driverClassName);
        dsc.setUrl(url);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();

        // strategy.setTablePrefix(new String[] { "sys_" });// 此处可以修改为您的表前缀

        // 表名生成策略
        //表字段驼峰命名式
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setInclude(tableNames);
        strategy.setRestControllerStyle(restControllerStyle);
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        strategy.setEntityLombokModel(entityLombokModel);

        mpg.setStrategy(strategy);

        PackageConfig pc = new PackageConfig();

        pc.setParent(packagePath);
        pc.setController(controller);
        pc.setService(service);
        pc.setServiceImpl(serviceImpl);
        pc.setMapper(mapper);
        pc.setEntity(entity);
        mpg.setPackageInfo(pc);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                //不需要做任何事
            }
        };
        List<FileOutConfig> focList = new ArrayList<FileOutConfig>();
        // 调整 xml 生成目录演示
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名
                return filePath + "/resources/" + xmlPath + "/" + tableInfo.getEntityName() + "Mapper" + ".xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setTemplate(tc);
        // 执行生成
        mpg.execute();
    }

}
