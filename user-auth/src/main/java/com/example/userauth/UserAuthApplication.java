package com.example.userauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class UserAuthApplication {


    public static void main(String[] args) {
		SpringApplication.run(UserAuthApplication.class, args);
	}

}
