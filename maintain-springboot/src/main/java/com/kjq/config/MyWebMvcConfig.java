package com.kjq.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.MultipartConfigElement;

//配置图片静态资源访问路径
@Configuration
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //获取去jar包所在位置
//        第1种方式
//        ApplicationHome ah = new ApplicationHome(getClass());
//        File jarF = ah.getSource();
//        String path = null;
//        try {
//            path = jarF.getParentFile().toString()+"/images/";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        第2中方式
//        String path = null;
//        try {
//            path = new File(ResourceUtils.getURL("classpath:").getPath()).getParentFile().getParentFile().getParent()+"/images/";
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

//        第3种方式
//        项目没有部署，使用这个
        String path = System.getProperty("user.dir") + "/target/images/";
//        项目部署以后使用这个
//        String path = System.getProperty("user.dir") + "/images/";

        registry.addResourceHandler("/images/**").addResourceLocations("file:" + path);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        /**
         * 单个数据大小，
         * DataSize.ofMegabytes(maxFileSize)默认是配置字节，将字节转化为MB
         */
        factory.setMaxFileSize(DataSize.ofMegabytes(20));
        // 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(20));
        return factory.createMultipartConfig();
    }
}
