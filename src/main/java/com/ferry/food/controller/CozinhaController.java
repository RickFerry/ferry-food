package com.ferry.food.controller;

import com.ferry.food.model.Cozinha;
import com.ferry.food.repository.CozinhaRepository;
import com.ferry.food.service.CozinhaService;
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
    private final CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        return ResponseEntity.ok(cozinhaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> buscarPorId(@PathVariable Long id) {
        Cozinha cozinha = cozinhaService.buscarPorId(id);
        if (cozinha != null) {
            return ResponseEntity.ok(cozinha);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha, UriComponentsBuilder uriComponentsBuilder) {
        Cozinha cozinhaSalva = cozinhaService.adicionar(cozinha);
        URI uri = uriComponentsBuilder.path("/{id}").buildAndExpand(cozinhaSalva.getId()).toUri();
        return ResponseEntity.created(uri).body(cozinhaSalva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtualizada = cozinhaService.atualizar(id, cozinha);
        if (cozinhaAtualizada != null) {
            return ResponseEntity.ok(cozinhaAtualizada);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            Object retorno = cozinhaService.deletar(id);
            if (retorno == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
