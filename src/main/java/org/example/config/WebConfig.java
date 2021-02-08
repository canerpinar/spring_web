package org.example.config;


import org.example.processor.HandlerIntercopter;
import org.example.processor.Processors;
import org.example.utils.Deneme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleServletPostProcessor;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"org.example.config","org.example.controller"
,"org.example.entity","org.example.repository","org.example.utils","org.example.processor"})
@PropertySource(value = {"classpath:application.properties"},encoding = "UTF-8")
@Import(Processors.class)
public class WebConfig implements WebMvcConfigurer {


    @PreDestroy
    public void destroy(){
        System.out.println("------------WebConfig destroyed------------");
    }


    Map<String,String> mapUrl = new HashMap<String, String>(){
        {
            put("Deneme","Deneme");
        put("Ahmet","Mehmet");
        put("Ali","Veli");
        }
    };

    @Autowired
    ApplicationContext applicationContext;




    @Bean
    public SpringResourceTemplateResolver templateResolver(){
        System.out.println("TEMPLATE RESOLVER");
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setCharacterEncoding("UTF-8");
        resolver.setTemplateMode("HTML5");
        resolver.setApplicationContext(applicationContext);
        return resolver;
    }


    @Bean
    public ServletAware servletAware(){
        return new ServletAware();
    }

    @Bean
    public SpringTemplateEngine templateEngine(){
        System.out.println("TEMPLATE ENGİNE");
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
    // multi language

    @Bean
    public ReloadableResourceBundleMessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
/*
    @Bean
    public CookieLocaleResolver localeResolver(){
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("my-locale-cookie");
        localeResolver.setCookieMaxAge(3600);
        return localeResolver;
    }

 */

    @Bean
    public LocaleChangeInterceptor localeInterceptor(){
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }




    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(localeInterceptor());
        registry.addInterceptor(new HandlerIntercopter());
    }

}
