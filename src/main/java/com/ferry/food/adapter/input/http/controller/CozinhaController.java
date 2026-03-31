package com.ferry.food.adapter.input.http.controller;

import com.ferry.food.domain.ports.input.cozinha.CriarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.ListarCozinhasUseCase;
import com.ferry.food.domain.ports.input.cozinha.ObterCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.AtualizarCozinhaUseCase;
import com.ferry.food.domain.ports.input.cozinha.DeletarCozinhaUseCase;
import com.ferry.food.application.dtos.input.cozinha.CriarCozinhaDTO;
import com.ferry.food.application.dtos.input.cozinha.AtualizarCozinhaDTO;
import com.ferry.food.application.dtos.output.cozinha.CozinhaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {
    
    private final CriarCozinhaUseCase criarUseCase;
    private final ListarCozinhasUseCase listarUseCase;
    private final ObterCozinhaUseCase obterUseCase;
    private final AtualizarCozinhaUseCase atualizarUseCase;
    private final DeletarCozinhaUseCase deletarUseCase;
    
    @GetMapping
    public ResponseEntity<List<CozinhaDTO>> listar() {
        List<CozinhaDTO> cozinhas = listarUseCase.executar();
        return ResponseEntity.ok(cozinhas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CozinhaDTO> obter(@PathVariable Long id) {
        CozinhaDTO cozinha = obterUseCase.executar(id);
        return ResponseEntity.ok(cozinha);
    }
    
    @PostMapping
    public ResponseEntity<CozinhaDTO> criar(@Valid @RequestBody CriarCozinhaDTO dto) {
        CozinhaDTO cozinha = criarUseCase.executar(new CriarCozinhaUseCase.CriarCozinhaInput(dto.getNome()));
        return ResponseEntity
            .created(URI.create("/cozinhas/" + cozinha.id()))
            .body(cozinha);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CozinhaDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody AtualizarCozinhaDTO dto
    ) {
        CozinhaDTO cozinha = atualizarUseCase.executar(id, new AtualizarCozinhaUseCase.AtualizarCozinhaInput(dto.getNome()));
        return ResponseEntity.ok(cozinha);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
