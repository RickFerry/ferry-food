package com.ferry.food.api.controller;

import com.ferry.food.api.service.CozinhaService;
import com.ferry.food.domain.exception.EntityInUseException;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cozinha;
import com.ferry.food.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
            cozinhaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
