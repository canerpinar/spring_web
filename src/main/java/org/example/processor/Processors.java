package org.example.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.thymeleaf.spring5.view.ThymeleafView;

//@Component
public class Processors //implements BeanFactoryAware, BeanNameAware, BeanPostProcessor
{
    /*
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName + " --------------- before initialization --------------------------");
        if(beanName.equals("index"))
            System.out.println("Class : " + bean.getClass() + " name " + beanName );
        else if(beanName.equals("defaultController")) System.out.println("Default Controller Class : " + bean.getClass() + " name " + beanName );

        if(bean instanceof ThymeleafView){
            ((ThymeleafView) bean).getStaticVariables().entrySet().forEach(stringObjectEntry -> {
                System.out.println(stringObjectEntry.getKey() +" : " + stringObjectEntry.getValue().toString());
            });
        }
        //ViewControllerRegistry viewControllerRegistry = new ViewControllerRegistry()
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String s) {

        System.out.println(s);


    }

     */
}
