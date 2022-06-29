package com.zgy.learn.zgycodegenerator.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author zgy
 * @date 2022/6/28
 */
@Component
public class Generator {
    @Value("${author}")
    private String author;
    @Value("${project.path}")
    private String projectPath;
    @Value("${package.parent}")
    private String packageParent;
    @Value("${package.moudle}")
    private String packageMoudle;
    @Value("${datasource.dbUrl}")
    private String datasourceDbUrl;
    @Value("${datasource.tables}")
    private String datasourceTables;

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
        String dbURL = "jdbc:mysql://" + datasourceDbUrl + "?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8";
        dsc.setUrl(dbURL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setDbType(DbType.MYSQL);
        ag.setDataSource(dsc);

        // 4.包的设置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(packageMoudle);
        pc.setParent(packageParent);
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setController("controller");
        ag.setPackageInfo(pc);

        // 5.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        // 要映射的表名
        strategyConfig.setInclude(datasourceTables.split(","));
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
