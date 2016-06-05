package com.mezentsev_tomin.adminpanel.listeners;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Yuriy on 05.06.2016.
 */
public class InitContextListener implements ServletContextListener {

    protected WebApplicationContext getWebApplicationContext(ServletContextEvent event){
        ServletContext servletContext = event.getServletContext();
        return (WebApplicationContext) servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-context.xml");
        applicationContext.close();
        System.out.println();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
