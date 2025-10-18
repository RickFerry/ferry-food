package com.ferry.food.api.controller;

import com.ferry.food.api.service.EstadoService;
import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Estado;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Estado estado = estadoService.buscarPorId(id);
            return ResponseEntity.ok(estado);
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
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
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado estado) {
        try {
            Estado estadoAtualizado = estadoService.atualizar(id, estado);
            return ResponseEntity.ok(estadoAtualizado);
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            estadoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (MyEntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
