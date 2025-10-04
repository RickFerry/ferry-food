package com.ferry.food.jpa;

import com.ferry.food.Application;
import com.ferry.food.model.Cozinha;
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
        log.warn(cadastroCozinha.listarCozinhas().toString());

        Cozinha iraniana = Cozinha.builder().nome("Iraniana").build();
        Cozinha israelense = Cozinha.builder().nome("Israelense").build();

        log.warn(cadastroCozinha.adicionarCozinha(iraniana).toString());
        log.warn(cadastroCozinha.adicionarCozinha(israelense).toString());

        log.warn(cadastroCozinha.buscarCozinha(1L).toString());

        log.warn(cadastroCozinha.atualizarCozinha(Cozinha.builder().nome("Cubana").build(), 1L).toString());

        cadastroCozinha.removerCozinha(2L);
        log.warn(cadastroCozinha.listarCozinhas().toString());
    }
}
