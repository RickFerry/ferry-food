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
    final Queue createQueue() {
        return new Queue("finish.payment", false);
    }

    @Bean
    final RabbitAdmin createRabbitAdmin(final ConnectionFactory conn) {
        return new RabbitAdmin(conn);
    }

    @Bean
    final ApplicationListener<ApplicationReadyEvent> starterAdmin(
            final RabbitAdmin admin) {
        return event -> admin.initialize();
    }
}
