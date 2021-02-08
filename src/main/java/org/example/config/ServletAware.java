package org.example.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Arrays;


public class ServletAware implements ServletConfigAware, ServletContextAware {
    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        System.out.println("---------------ServletConfig servletConfig------------------");

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("---------------ServletContext servletContext------------------");



        System.out.println(servletContext);
        System.out.println(servletContext.getContextPath());
        System.out.println(servletContext.getServletContextName());
        //Arrays.stream(webApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        //System.out.println("---------------ServletContext 2222222222 servletContext------------------");
    }
}
