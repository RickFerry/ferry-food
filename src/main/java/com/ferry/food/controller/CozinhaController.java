package com.ferry.food.controller;

import com.ferry.food.model.Cozinha;
import com.ferry.food.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;

    @GetMapping
    public List<Cozinha> listar() {
        return cozinhaRepository.listarCozinhas();
    }

    @GetMapping("/{id}")
    public Cozinha buscarPorId(@PathVariable Long id) {
        return cozinhaRepository.buscarCozinha(id);
    }
}
