package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages =  {"com.example.demo"})
public class AdminAccessApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminAccessApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//	    return args -> {
//
//	      System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//	      String[] beanNames =  ctx.getBeanDefinitionNames();
//	      Arrays.sort(beanNames);
//	      for (String beanName : beanNames) {
//	        System.out.println(beanName);
//	      }
//	    };
//	  }    

}
