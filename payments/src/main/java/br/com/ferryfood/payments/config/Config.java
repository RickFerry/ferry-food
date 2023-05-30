package br.com.ferryfood.payments.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Queue createQueue() {
        return new Queue("finish.payment", false);
    }

    @Bean
    RabbitAdmin createRabbitAdmin(ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> starterAdmin(RabbitAdmin admin) {
        return event -> admin.initialize();
    }
}
