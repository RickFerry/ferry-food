package com.ferry.food.jpa;

import com.ferry.food.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE).run(args);

        CadastroCozinha cadastroCozinha = run.getBean(CadastroCozinha.class);
        log.info(cadastroCozinha.listarCozinhas().toString());
    }
}
