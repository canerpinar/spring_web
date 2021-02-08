package org.example.startup;

import org.example.servlet.DefaultServlet;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

@Component
public class ApplicationStartup implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext){
        System.out.println("----------------------LOADING----------------------------");
        ConfigurableWebApplicationContext webApplicationContext = new AnnotationConfigWebApplicationContext();
        webApplicationContext.setConfigLocation("org/example/config");
        servletContext.addListener(new ContextLoaderListener(webApplicationContext));



        //servletContext.addServlet("defaultServlet",new DefaultServlet()).addMapping("/default");
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",new DispatcherServlet(webApplicationContext));
        ServletRegistration.Dynamic servletDefault = servletContext.addServlet("kayseri",new DefaultServlet());
        servletDefault.setLoadOnStartup(1);
        servletDefault.addMapping("/dispatcher/kayseri/");
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/dispatcher/");

    }



}
