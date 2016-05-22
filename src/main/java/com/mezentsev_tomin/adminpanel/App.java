package com.mezentsev_tomin.adminpanel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Mezentsev.Y on 5/18/2016.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("application-context.xml");
        applicationContext.close();
    }
}
