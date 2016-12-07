package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class LibraryServiceApplication {

	public static void main(String[] args) {


		//		ConfigurableApplicationContext ctx = SpringApplication.run(LibraryApplication.class, args);
//
//		BookService bookService = ctx.getBean("bookService", BookService.class);
//		bookService.printAllBooks();
//		ctx.close();

		SpringApplication.run(LibraryServiceApplication.class, args);


	}
}
