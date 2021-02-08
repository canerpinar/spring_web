package org.example.controller;

import org.example.entity.Person;
import org.example.repository.PersonRepository;
import org.example.utils.Deneme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Controller(value = "defaultController")
@RequestMapping("/dispatcher")
public class IndexController{

    @Value("${spring.deneme}")
    String denemeler;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MessageSource messageSource;

    @Autowired
    private Deneme deneme;

  //private PersonRepository personRepository;




    @RequestMapping("/")
    public String index(Locale locale, Model model)
    {


        Map<String,String> beanList = new HashMap<>();
        for(String s: webApplicationContext.getBeanDefinitionNames()){
            //System.out.println("BEAN : " + s);
            beanList.put(s,webApplicationContext.getBean(s).getClass().getName());
        }
/*

        for(int k=0;k<10;k++){
            Person person = new Person();
            person.setName("can");
            person.setSurname("asd");
            personRepository.save(person);
        }
        System.out.println("COUNT with TRANSACTION : " + applicationContext.getBeanDefinitionCount());
        for(String s:applicationContext.getBeanDefinitionNames()){
            System.out.println(s +" : " + applicationContext.getBean(s));
        }
        System.out.println("index sayfasına girildi.......");

 */


        // add parametrized message from controller
        String welcome = messageSource.getMessage("welcome.message", new Object[]{"John Doe"}, locale);
        model.addAttribute("message", welcome);

        model.addAttribute("beanList",beanList);
        // obtain locale from LocaleContextHolder
        Locale currentLocale = LocaleContextHolder.getLocale();
        model.addAttribute("locale", currentLocale);
        model.addAttribute("deneme", denemeler);
        model.addAttribute("startMeeting", "10:30");
        model.addAttribute("beanCount",webApplicationContext.getBeanDefinitionCount());
        return "index";
    }
}
