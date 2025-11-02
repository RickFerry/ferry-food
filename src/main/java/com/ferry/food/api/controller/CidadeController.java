package com.ferry.food.api.controller;

import com.ferry.food.api.service.CidadeService;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cidade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cidades")
public class CidadeController {
    private final CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<Cidade>> listar() {
        return ResponseEntity.ok(cidadeService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Cidade cidade = cidadeService.buscarPorId(id);
            return ResponseEntity.ok(cidade);
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Cidade cidade, UriComponentsBuilder builder) {
        try {
            Cidade cidadeSalva = cidadeService.salvar(cidade);
            return ResponseEntity
                    .created(builder.path("/{id}")
                            .buildAndExpand(cidadeSalva.getId()).toUri())
                    .body(cidadeSalva);
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            Cidade cidadeAtualizada = cidadeService.atualizar(id, cidade);
            return ResponseEntity.ok(cidadeAtualizada);
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            cidadeService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
