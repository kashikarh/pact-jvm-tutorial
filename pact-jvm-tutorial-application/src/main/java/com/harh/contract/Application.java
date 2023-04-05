package com.harh.contract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hello world!
 *
 */

@SpringBootApplication
public class Application extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class, args);
    }
}
