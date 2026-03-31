package com.ferry.food.adapter.input.http.controller;

import com.ferry.food.domain.ports.input.estado.CriarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.ListarEstadosUseCase;
import com.ferry.food.domain.ports.input.estado.ObterEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.AtualizarEstadoUseCase;
import com.ferry.food.domain.ports.input.estado.DeletarEstadoUseCase;
import com.ferry.food.application.dtos.input.estado.CriarEstadoDTO;
import com.ferry.food.application.dtos.input.estado.AtualizarEstadoDTO;
import com.ferry.food.application.dtos.output.estado.EstadoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {
    
    private final CriarEstadoUseCase criarUseCase;
    private final ListarEstadosUseCase listarUseCase;
    private final ObterEstadoUseCase obterUseCase;
    private final AtualizarEstadoUseCase atualizarUseCase;
    private final DeletarEstadoUseCase deletarUseCase;
    
    @GetMapping
    public ResponseEntity<List<EstadoDTO>> listar() {
        List<EstadoDTO> estados = listarUseCase.executar();
        return ResponseEntity.ok(estados);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<EstadoDTO> obter(@PathVariable Long id) {
        EstadoDTO estado = obterUseCase.executar(id);
        return ResponseEntity.ok(estado);
    }
    
    @PostMapping
    public ResponseEntity<EstadoDTO> criar(@Valid @RequestBody CriarEstadoDTO dto) {
        EstadoDTO estado = criarUseCase.executar(new CriarEstadoUseCase.CriarEstadoInput(dto.getNome()));
        return ResponseEntity
            .created(URI.create("/estados/" + estado.id()))
            .body(estado);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody AtualizarEstadoDTO dto
    ) {
        EstadoDTO estado = atualizarUseCase.executar(id, new AtualizarEstadoUseCase.AtualizarEstadoInput(dto.getNome()));
        return ResponseEntity.ok(estado);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
