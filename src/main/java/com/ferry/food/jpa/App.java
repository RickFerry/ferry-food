package com.ferry.food.jpa;

import com.ferry.food.Application;
import com.ferry.food.model.Cozinha;
import com.ferry.food.model.Restaurante;
import com.ferry.food.repository.CozinhaRepository;
import com.ferry.food.repository.RestauranteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.math.BigDecimal;

@Slf4j
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE).run(args);

        CozinhaRepository cadastroCozinha = run.getBean(CozinhaRepository.class);
        log.warn(cadastroCozinha.listarCozinhas().toString());

        Cozinha iraniana = Cozinha.builder().nome("Iraniana").build();
        Cozinha israelense = Cozinha.builder().nome("Israelense").build();

        log.warn(cadastroCozinha.adicionarCozinha(iraniana).toString());
        log.warn(cadastroCozinha.adicionarCozinha(israelense).toString());

        log.warn(cadastroCozinha.buscarCozinha(1L).toString());

        log.warn(cadastroCozinha.atualizarCozinha(Cozinha.builder().nome("Cubana").build(), 1L).toString());


        RestauranteRepository cadastroRestaurante = run.getBean(RestauranteRepository.class);
        log.warn(cadastroRestaurante.listarRestaurantes().toString());

        Restaurante italiano = Restaurante.builder().nome("Italiano").taxaFrete(new BigDecimal("10.00")).build();
        Restaurante japones = Restaurante.builder().nome("Japonês").taxaFrete(new BigDecimal("15.00")).build();

        log.warn(cadastroRestaurante.adicionar(italiano).toString());
        log.warn(cadastroRestaurante.adicionar(japones).toString());

        log.warn(cadastroRestaurante.buscarRestaurante(1L).toString());

        log.warn(cadastroRestaurante.atualizar(
                Restaurante.builder().nome("Mexicano").taxaFrete(new BigDecimal("12.00")).build(), 1L).toString());

        cadastroRestaurante.remover(2L);
        log.warn(cadastroRestaurante.listarRestaurantes().toString());

        cadastroCozinha.removerCozinha(2L);
        log.warn(cadastroCozinha.listarCozinhas().toString());
    }
}
