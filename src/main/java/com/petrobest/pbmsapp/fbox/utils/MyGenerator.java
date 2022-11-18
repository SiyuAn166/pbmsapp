//package com.petrobest.pbmsapp.fbox.utils;
//
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
//import org.apache.commons.lang3.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class MyGenerator {
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
//            if (StringUtils.isNotEmpty(ipt)) {
//                return ipt;
//            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//    public static void main(String[] args) {
//        // 代码生成器
//        AutoGenerator mpg = new AutoGenerator();
//
//        // 全局配置
//        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir")+"/pbmsapp";
//        gc.setOutputDir(projectPath + "/src/main/java");//输出文件路径
//        gc.setOpen(false);
//
//        gc.setFileOverride(true);
//        gc.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
//        gc.setEnableCache(false);// XML 二级缓存
//        gc.setBaseResultMap(true);// XML ResultMap
//        gc.setBaseColumnList(false);// XML columList
//        gc.setAuthor("demo");// 作者
//        mpg.setGlobalConfig(gc);
//
//        // 数据源配置
//        DataSourceConfig dsc = new DataSourceConfig();
//        dsc.setUrl("jdbc:mysql://localhost:3306/bestms?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8");
//        // dsc.setSchemaName("public");
//        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
//        dsc.setPassword("root");
//        mpg.setDataSource(dsc);
//
//        // 包配置
//        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
//        //修改为自己的项目路径
//        pc.setParent("com.petrobest.pbmsapp");
//        mpg.setPackageInfo(pc);
//
//        // 自定义配置
//        InjectionConfig cfg = new InjectionConfig() {
//            @Override
//            public void initMap() {
//                // to do nothing
//            }
//        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 自定义输入文件名称
//                return projectPath + "/src/main/resources/mybatis/mapper/" + pc.getModuleName()
//                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//            }
//        });
//        cfg.setFileOutConfigList(focList);
//        mpg.setCfg(cfg);
//        mpg.setTemplate(new TemplateConfig().setXml(null));
//
//        // 策略配置
//        StrategyConfig strategy = new StrategyConfig();
//        strategy.setNaming(NamingStrategy.underline_to_camel);
//        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");
//        //strategy.setEntityLombokModel(true);//是否系列化类
//        strategy.setRestControllerStyle(true);
//        // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//        strategy.setInclude(scanner("表名"));
//        strategy.setSuperEntityColumns("id");
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(pc.getModuleName() + "_");
//        mpg.setStrategy(strategy);
//        mpg.setTemplateEngine(new VelocityTemplateEngine());
//        mpg.execute();
//    }
//}
