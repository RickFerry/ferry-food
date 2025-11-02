package com.ferry.food.api.controller;

import com.ferry.food.api.service.EstadoService;
import com.ferry.food.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(estadoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(estadoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Estado> cadastrar(@RequestBody Estado estado, UriComponentsBuilder builder) {
        Estado estadoSalvo = estadoService.salvar(estado);
        return ResponseEntity
                .created(builder.path("/{id}")
                        .buildAndExpand(estadoSalvo.getId()).toUri())
                .body(estadoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        return ResponseEntity.ok(estadoService.atualizar(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}