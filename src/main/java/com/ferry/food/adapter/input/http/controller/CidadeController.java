package com.ferry.food.adapter.input.http.controller;

import com.ferry.food.domain.ports.input.cidade.CriarCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.ListarCidadesUseCase;
import com.ferry.food.domain.ports.input.cidade.ObterCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.AtualizarCidadeUseCase;
import com.ferry.food.domain.ports.input.cidade.DeletarCidadeUseCase;
import com.ferry.food.application.dtos.input.cidade.CriarCidadeDTO;
import com.ferry.food.application.dtos.input.cidade.AtualizarCidadeDTO;
import com.ferry.food.application.dtos.output.cidade.CidadeDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {
    
    private final CriarCidadeUseCase criarUseCase;
    private final ListarCidadesUseCase listarUseCase;
    private final ObterCidadeUseCase obterUseCase;
    private final AtualizarCidadeUseCase atualizarUseCase;
    private final DeletarCidadeUseCase deletarUseCase;
    
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        List<CidadeDTO> cidades = listarUseCase.executar();
        return ResponseEntity.ok(cidades);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CidadeDTO> obter(@PathVariable Long id) {
        CidadeDTO cidade = obterUseCase.executar(id);
        return ResponseEntity.ok(cidade);
    }
    
    @PostMapping
    public ResponseEntity<CidadeDTO> criar(@Valid @RequestBody CriarCidadeDTO dto) {
        CidadeDTO cidade = criarUseCase.executar(
            new CriarCidadeUseCase.CriarCidadeInput(dto.getNome(), dto.getEstadoId())
        );
        return ResponseEntity
            .created(URI.create("/cidades/" + cidade.id()))
            .body(cidade);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CidadeDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody AtualizarCidadeDTO dto
    ) {
        CidadeDTO cidade = atualizarUseCase.executar(
            id,
            new AtualizarCidadeUseCase.AtualizarCidadeInput(dto.getNome(), dto.getEstadoId())
        );
        return ResponseEntity.ok(cidade);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
