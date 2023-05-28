package br.com.ferryfood.requests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class RequestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestsApplication.class, args);
	}
}
