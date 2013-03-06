package org.camelcookbook.structuringroutes;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringJavaDslApplication {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("/SpringJavaDslApplication-context.xml");
        applicationContext.start();

        // let the Camel runtime do its job for 5 seconds
        Thread.sleep(5000);

        // shutdown
        applicationContext.stop();
    }
}