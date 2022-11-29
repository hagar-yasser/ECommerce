package org.example;

import org.example.config.ShoppingServletConfig;
import org.example.config.WebConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(ShoppingServletConfig.class);
        System.out.println("Hello world!");
    }
}