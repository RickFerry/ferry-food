package com.ferry.food.api.controller;

import com.ferry.food.domain.model.Estado;
import com.ferry.food.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estados")
public class EstadoController {
    private final EstadoRepository estadoRepository;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        return ResponseEntity.ok(estadoRepository.listarEstados());
    }
}
