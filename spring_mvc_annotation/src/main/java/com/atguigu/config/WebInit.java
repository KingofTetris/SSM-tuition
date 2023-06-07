package com.atguigu.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

/**
 * @author by KingOfTetris
 * @date 2023/6/1
 */
//用于代替web.xml
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    //设置一个配置类代替Spring的配置文件
    @Override
    protected Class<?>[] getRootConfigClasses() {
        //如果是分布式的大项目
        //Spring和SpringMVC的配置文件可以有很多个
        //所有才是数组类型
        return new Class[]{SpringConfig.class};
    }

    //设置一个配置类代理SpringMVC的配置文件
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //配置前端控制器DispatcherServlet
    //的url-pattern
    @Override
    protected String[] getServletMappings() {
        //相当于url-pattern配置为/
        return new String[]{"/"};
    }


    //这就是设置过滤器
    @Override
    protected Filter[] getServletFilters() {
        //和xml一样，还是首先创建编码过滤器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("utf-8");
        characterEncodingFilter.setForceEncoding(true);
        //创建请求方式的过滤器
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        return new Filter[]{characterEncodingFilter,
                hiddenHttpMethodFilter};
    }
}
