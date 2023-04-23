package com.zgy.learn.zgycodegenerator.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zgy
 * @date 2022/6/28
 */
@Component
public class Generator {
    @Value("${generator.author}")
    private String author;
    @Value("${generator.outputPath}")
    private String projectPath;
    @Value("${generator.package.parent}")
    private String packageParent;
    @Value("${generator.package.module}")
    private String packageModule;
    @Value("${generator.database.user}")
    private String databaseUser;
    @Value("${generator.database.password}")
    private String databasePassword;
    @Value("${generator.database.url}")
    private String databaseUrl;
    @Value("${generator.database.tables}")
    private String databaseTables;

    public void generate() {
        // 1.构建一个代码自动生成器对象
        AutoGenerator ag = new AutoGenerator();

        // 2.配置策略
        GlobalConfig gc = new GlobalConfig();
        String outputPath = projectPath;
        gc.setOutputDir(outputPath + "/src/main/java/");
        gc.setAuthor(author);
        gc.setOpen(false);
        // 是否覆盖
        gc.setFileOverride(false);
        // 去掉service的I前缀
        gc.setServiceName("%sSevice");
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        ag.setGlobalConfig(gc);

        // 3.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        String dbURL = "jdbc:mysql://" + databaseUrl + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
        dsc.setUrl(dbURL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(databaseUser);
        dsc.setPassword(databasePassword);
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        // 4.包的设置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(packageModule);
        pc.setParent(packageParent);
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        ag.setPackageInfo(pc);

        // 5.代码模版
        FreemarkerTemplateEngine engine = new FreemarkerTemplateEngine();
        ag.setTemplateEngine(engine);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置自定义输出模板
        // 指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("/codeTemplates/entity.java");
        templateConfig.setMapper("/codeTemplates/mapper.java");
        templateConfig.setXml("/codeTemplates/mapper.xml");
        templateConfig.setService("/codeTemplates/service.java");
        templateConfig.setServiceImpl("/codeTemplates/serviceImpl.java");
        templateConfig.setController("/codeTemplates/controller.java");
        ag.setTemplate(templateConfig);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                map.put("obj", packageParent + ".util");
                map.put("camelTableName", databaseTables);
                this.setMap(map);
            }
        };
        // 特殊处理
        String mapperTemplate = "/codeTemplates/mapper.xml.ftl";
        String resultTemplate = "/codeTemplates/Result.java.ftl";
        String messageTemplate = "/codeTemplates/MessageCode.java.ftl";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(mapperTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名, 如果Entity设置了前后缀, 此处注意xml的名称会跟着发生变化
                return projectPath + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        focList.add(new FileOutConfig(resultTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/"+ packageParent +"/util/Result.java";
            }
        });
        focList.add(new FileOutConfig(messageTemplate) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + "/src/main/java/"+ packageParent +"/util/MessageCode.java";
            }
        });
        cfg.setFileOutConfigList(focList);
        ag.setCfg(cfg);

        // 6.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 要映射的表名
        strategyConfig.setInclude(databaseTables.split(","));
        // 驼峰命名
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 自动lombok
        strategyConfig.setEntityLombokModel(true);
        //strategyConfig.setLogicDeleteFieldName("deleted");
        // 自动填充
        TableFill createTime = new TableFill("createTime", FieldFill.INSERT);
        TableFill updateTime = new TableFill("updateTime", FieldFill.INSERT_UPDATE);
        ArrayList tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategyConfig.setTableFillList(tableFills);
        // 乐观锁
        strategyConfig.setVersionFieldName("version");
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        ag.setStrategy(strategyConfig);
        // 执行
        ag.execute();
    }

}
