package com.example.demo.springboot2023.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>
 * mysql 代码生成器演示例子
 * </p>
 *
 * @author jobob
 * @since 2018-09-12
 */
public class MysqlGenerator {


    /**
     * 是否启动swagger
     */
    private static final boolean blSwagger = true;
    /**
     * 包所在路径
     */
    private static final String  packagePath="com.example.demo";
    /**
     * baseEntity所在路径
     */
    private static final String baseEntity = packagePath+".springboot2023.utils.BaseEntity";
    /**
     * 实体所在的module
     */
    private static final String moduleName="serve";
    /**
     * 删除字符: tb_student =>Student
     * 不忽略，则tb_student =>TbStudent
     */
    private static final String ignorePrefix="tb_";
    /**
     * 数据库表名，支持多个，用,隔开
     */
    private static final String bean="tb_serve";
    /**,tb_book
     * 数据库
     */
    private static final String database="cwsd_db";
    /**
     * 用户名
     */
    private static final String username="root";
    /**
     * 密码
     */
    private static final String password="admin";

    /**
     * 作者
     */
    private static final String authorname="Csk";

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor(authorname);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setSwagger2(blSwagger);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/"+database+"?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername(username);
        dsc.setPassword(password);
        //取消将tinyint(1)转成Boolean
        dsc.setTypeConvert(new MySqlTypeConvertCustom());

        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(packagePath);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath;
        // 自定义输出配置
//        List<FileOutConfig> focList = new ArrayList<>();
//        templatePath = "/templates/dto/pageListRequestDTO.java.ftl";
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/src/main/java/" +packagePath.replace('.','/')+"/"+ packageName +
//                        "/dto/requestDTO/" + tableInfo.getEntityName() + "PageListRequestDTO" + StringPool.DOT_JAVA;
//            }
//        });
//        templatePath = "/templates/dto/deleteByIdsRequestDTO.java.ftl";
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/src/main/java/"+packagePath.replace('.','/')+"/"+  packageName +
//                        "/dto/requestDTO/" + tableInfo.getEntityName() + "DeleteByIdsRequestDTO" + StringPool.DOT_JAVA;
//            }
//        });
//        templatePath = "/templates/dto/updateUsefulByIdsRequestDTO.java.ftl";
//        focList.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return projectPath + "/src/main/java/" +packagePath.replace('.','/')+"/"+ packageName +
//                        "/dto/requestDTO/" + tableInfo.getEntityName() + "UpdateUsefulByIdsRequestDTO" + StringPool.DOT_JAVA;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
         templateConfig.setController("templates/controller.java");
         templateConfig.setMapper("templates/mapper.java");
         templateConfig.setService("templates/service.java");
         templateConfig.setServiceImpl("templates/serviceImpl.java");
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        mpg.setCfg(cfg);
        //mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperEntityClass(baseEntity);
        strategy.setEntityLombokModel(true);
        //strategy.setSuperControllerClass(packagePath+".common.base.BaseController");
        strategy.setInclude(bean.split(","));
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(ignorePrefix );
        strategy.setRestControllerStyle(true);


        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();

        System.out.println("end....");
    }

}


/**
 * 自定义类型转换
 */
class MySqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {
    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("tinyint(1)")) {
            return DbColumnType.INTEGER;
        }
        return super.processTypeConvert(globalConfig, fieldType);
    }
}

