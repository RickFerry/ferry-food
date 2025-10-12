package com.ferry.food.controller;

import com.ferry.food.model.Cozinha;
import com.ferry.food.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cozinhas")
public class CozinhaController {
    private final CozinhaRepository cozinhaRepository;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cozinhaRepository.listarCozinhas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
        Cozinha cozinha = cozinhaRepository.buscarCozinha(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha, UriComponentsBuilder uriComponentsBuilder) {
        Cozinha cozinhaSalva = cozinhaRepository.adicionarCozinha(cozinha);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(cozinhaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(cozinhaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cozinhaRepository.buscarCozinha(id);
        if (cozinhaAtual != null) {
            copyProperties(cozinha, cozinhaAtual, "id");
            Cozinha cozinhaAtualizada = cozinhaRepository.adicionarCozinha(cozinhaAtual);
            return ResponseEntity.ok(cozinhaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            Cozinha cozinha = cozinhaRepository.buscarCozinha(id);
            if (cozinha != null) {
                cozinhaRepository.removerCozinha(id);
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.notFound().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
