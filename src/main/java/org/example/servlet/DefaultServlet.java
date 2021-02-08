package org.example.servlet;


import org.example.startup.ApplicationStartup;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.DefaultEventListenerFactory;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.ServletContextResourceLoader;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.function.support.RouterFunctionMapping;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import org.springframework.web.servlet.mvc.HttpRequestHandlerAdapter;
import org.springframework.web.servlet.mvc.annotation.ModelAndViewResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.resource.ResourceUrlProvider;
import org.springframework.web.servlet.view.ViewResolverComposite;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class DefaultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CONTEXT PATH : " + req.getContextPath());
        ServletContext servletContext = req.getServletContext();
        System.out.println("--------------------------------");
        servletContext.getServletRegistrations().values().forEach(o -> {
            System.out.println("|||||||||||||||||||||||||||" + o.getName());
        });
/*
        servletContext.getServletRegistrations().entrySet().forEach(stringEntry -> {
            System.out.println(stringEntry.getValue().getName());
        });
        System.out.println("--------------------------------");


 */



        ConfigurableWebApplicationContext webApplicationContext= (ConfigurableWebApplicationContext) ContextLoaderListener.getCurrentWebApplicationContext();
        Arrays.stream(webApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);


/*
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(IndexController.class);

*/





        webApplicationContext.close();
        webApplicationContext.setConfigLocations("org/example/configiki");
        webApplicationContext.refresh();



        String[] beanNames = webApplicationContext.getBeanDefinitionNames();
        resp.getWriter().println("BEAN COUNT : " + beanNames.length);

        resp.getWriter().println("bean count after close : " + webApplicationContext.getBeanDefinitionCount() + "Config location : " + webApplicationContext.getConfigLocations().toString());
        resp.getWriter().println("Config locations:  " + Arrays.toString(webApplicationContext.getConfigLocations()));

        RequestMappingHandlerAdapter requestMappingHandlerAdapter = webApplicationContext.getBean(RequestMappingHandlerAdapter.class);
        HandlerMappingIntrospector handlerMappingIntrospector = webApplicationContext.getBean(HandlerMappingIntrospector.class);


/*
        HttpRequestHandlerAdapter httpRequestHandlerAdapter = webApplicationContext.getBean(HttpRequestHandlerAdapter.class);
        //httpRequestHandlerAdapter.
        ViewResolverComposite resolverComposite = webApplicationContext.getBean(ViewResolverComposite.class);
        DelegatingWebMvcConfiguration delegatingWebMvcConfiguration = webApplicationContext.getBean(DelegatingWebMvcConfiguration.class);
        //resp.getWriter().println("GET SERVLET CCONTEXT : " + delegatingWebMvcConfiguration.getServletContext().getServletContextName());
        DefaultFormattingConversionService conversionService = webApplicationContext.getBean(DefaultFormattingConversionService.class);
        ResourceUrlProvider urlProvider = webApplicationContext.getBean(ResourceUrlProvider.class);
        BeanNameUrlHandlerMapping handlerMapping = webApplicationContext.getBean(BeanNameUrlHandlerMapping.class);
        RouterFunctionMapping routerFunctionMapping = webApplicationContext.getBean(RouterFunctionMapping.class);
        String gelen = routerFunctionMapping.getPathMatcher().extractPathWithinPattern("/","/index.html");

        System.out.println("-------------" + gelen+ "--fddsfdsf---------------------------------");


        for(String name:beanNames){
            //if(name.equals("defaultController")){
            resp.getWriter().println(name + "-- : --" + webApplicationContext.getBean(name));

        }



        DefaultEventListenerFactory defaultEventListenerFactory = webApplicationContext.getBean(DefaultEventListenerFactory.class);
        //EventListenerMethodProcessor eventListenerMethodProcessor = webApplicationContext.getBean(EventListenerMethodProcessor.class);
        ContextRefreshedEvent contextRefreshedEvent = new ContextRefreshedEvent(webApplicationContext);
        ConfigurationClassPostProcessor configurationClassPostProcessor = webApplicationContext.getBean(ConfigurationClassPostProcessor.class);
        //configurationClassPostProcessor.
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor = new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setBeanFactory(webApplicationContext.getBeanFactory());
        //defaultEventListenerFactory.createApplicationListener("assd", EventListenerFactory.class, ReflectionUtils.)



        resp.getWriter().println("---------------------------------------------------");
        servletContext.getServletRegistrations().entrySet().forEach(stringEntry -> {
            try {
                resp.getWriter().println(stringEntry.getKey() +" : " + stringEntry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resp.getWriter().println("------------------------URL MAPPING---------------------------");
        servletContext.getServletRegistration("dispatcher").getMappings().forEach(s -> {
            try {
                resp.getWriter().println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        //new ContextLoaderListener().initWebApplicationContext(servletContext).


        BeanDefinitionRegistry beanDefinitionRegistry = (BeanDefinitionRegistry) webApplicationContext.getAutowireCapableBeanFactory();
        resp.getWriter().println("Kaldırtıktan önce : " + webApplicationContext.getBeanDefinitionCount());
        //webApplicationContext.stop();
        beanDefinitionRegistry.removeBeanDefinition("defaultController");
        webApplicationContext.refresh();
        resp.getWriter().println("Kaldırtıktan sonra : " + webApplicationContext.getBeanDefinitionCount());
        GenericBeanDefinition gbd = new GenericBeanDefinition();
        gbd.setBeanClass(IndexController.class);
        beanDefinitionRegistry.registerBeanDefinition("secondController",gbd);

        webApplicationContext.refresh();

        String[] beanNames = webApplicationContext.getBeanDefinitionNames();

        for(String name:beanNames){
            if(name.equals("defaultController")){
                resp.getWriter().println("defaultController : " + webApplicationContext.getBean("defaultController"));
            }
        }

        //webApplicationContext.clearResourceCaches();
        //webApplicationContext.refresh();

        //webApplicationContext.close();

        webApplicationContext.setConfigLocation("org/example/config2");
        //webApplicationContext.scan("org/example/config2");
        for(String name:beanNames){
            if(name.equals("secondController") || name.equals("defaultController")){
                resp.getWriter().println("defaultController : " + webApplicationContext.getBean(name));
            }
        }
        //webApplicationContext.register(IndexController.class);
        //webApplicationContext.refresh();
        resp.getWriter().println("Ekleme yaptıktan sonra: " + webApplicationContext.getBeanDefinitionCount());
        //webApplicationContext.refresh();

        HttpRequestHandlerAdapter httpRequestHandlerAdapter = webApplicationContext.getBean(HttpRequestHandlerAdapter.class);
        DelegatingWebMvcConfiguration delegatingWebMvcConfiguration = webApplicationContext.getBean(DelegatingWebMvcConfiguration.class);
        resp.getWriter().println("GET SERVLET CCONTEXT : " + delegatingWebMvcConfiguration.getServletContext().getServletContextName());

         */




        //HandlerMapping
        //delegatingWebMvcConfiguration.
/*
        servletContext.getServletRegistrations().entrySet().forEach(stringEntry -> {
            try {
                resp.getWriter().println(stringEntry.getKey() + " : " + stringEntry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


        //servletContext.set

        */


/*
        resp.getWriter().println("CONTEXT PATH : " + req.getContextPath());
        servletContext.getServletRegistrations().keySet().forEach(s -> {
            try {
                resp.getWriter().println("SERVLET NAME : " + s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resp.getWriter().println("DİSPATCHER SPRİNG SERVLET ATTRIBUTE NAMES");
        servletContext.getServletRegistration("dispatcher").getInitParameters().entrySet().forEach(stringStringEntry -> {
            try {
                resp.getWriter().println(stringStringEntry.getKey() + " : " + stringStringEntry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        ConfigurableWebApplicationContext applicationContext = (ConfigurableWebApplicationContext) WebApplicationContextUtils.findWebApplicationContext(servletContext);
        applicationContext.setConfigLocation("org/example/config2/WebConfig2.java");
        applicationContext.refresh();
        resp.getWriter().println(applicationContext.getBeanDefinitionNames());
/*

 */

        //servletContext.addListener(new ContextLoaderListener(annotationConfigApplicationContext));


        //servletContext.addServlet("defaultServlet",new DefaultServlet()).addMapping("/default");

        /*
        servletContext.addListener(new ContextLoaderListener(applicationContext));
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(applicationContext));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");



        resp.getWriter().println("REAL PATH DEFAULT : " + servletContext.getRealPath("/default"));
        resp.getWriter().println("REAL PATH DISPATCHER : " + servletContext.getRealPath("/dispatcher"));
        resp.getWriter().println("REAL PATH / : " + servletContext.getRealPath("/"));

        resp.getWriter().println("REAL PATH DEFAULT RESOURCE : " + servletContext.getResource("/default"));
        resp.getWriter().println("REAL PATH DEFAULT DISPATCHER RESOURCE  : " + servletContext.getResource("/dispatcher"));
        resp.getWriter().println("REAL PATH / RESOURCE : " + servletContext.getResource("/"));



        resp.getWriter().println(Arrays.toString(applicationContext.getBeanDefinitionNames()));
        ServletRegistration registration = servletContext.getServletRegistration("dispatcher");
        resp.getWriter().println("DİSPATCHER SPRİNG SERVLET INFORMATİON");
        registration.getInitParameters().entrySet().forEach(stringStringEntry -> {
            try {
                resp.getWriter().println(stringStringEntry.getKey() + " : " + stringStringEntry.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        registration.getMappings().forEach(s -> {
            try {
                resp.getWriter().println(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

*/

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
