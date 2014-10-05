package com.aristideniyungeko;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring boot app for demo
 */
@RestController
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Controller {

   @RequestMapping("/")
   public String index() {
      return "Voila!";
   }

   public static void main(String[] args) {
      ApplicationContext ctx = SpringApplication.run(Controller.class, args);
   }
}
