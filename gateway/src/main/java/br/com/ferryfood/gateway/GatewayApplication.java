package br.com.ferryfood.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GatewayApplication {

	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Override
	public final String toString() {
		return "GatewayApplication []";
	}
}
