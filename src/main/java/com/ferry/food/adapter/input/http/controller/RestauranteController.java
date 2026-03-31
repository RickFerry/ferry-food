package com.ferry.food.adapter.input.http.controller;

import com.ferry.food.domain.ports.input.restaurante.CriarRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.ListarRestaurantesUseCase;
import com.ferry.food.domain.ports.input.restaurante.ObterRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.AtualizarRestauranteUseCase;
import com.ferry.food.domain.ports.input.restaurante.DeletarRestauranteUseCase;
import com.ferry.food.application.dtos.input.restaurante.CriarRestauranteDTO;
import com.ferry.food.application.dtos.input.restaurante.AtualizarRestauranteDTO;
import com.ferry.food.application.dtos.output.restaurante.RestauranteDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {
    
    private final CriarRestauranteUseCase criarUseCase;
    private final ListarRestaurantesUseCase listarUseCase;
    private final ObterRestauranteUseCase obterUseCase;
    private final AtualizarRestauranteUseCase atualizarUseCase;
    private final DeletarRestauranteUseCase deletarUseCase;
    
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restaurantes = listarUseCase.executar();
        return ResponseEntity.ok(restaurantes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<RestauranteDTO> obter(@PathVariable Long id) {
        RestauranteDTO restaurante = obterUseCase.executar(id);
        return ResponseEntity.ok(restaurante);
    }
    
    @PostMapping
    public ResponseEntity<RestauranteDTO> criar(@Valid @RequestBody CriarRestauranteDTO dto) {
        RestauranteDTO restaurante = criarUseCase.executar(
            new CriarRestauranteUseCase.CriarRestauranteInput(
                dto.getNome(),
                dto.getTaxaFrete(),
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getCidadeId(),
                dto.getCozinhaId()
            )
        );
        return ResponseEntity
            .created(URI.create("/restaurantes/" + restaurante.id()))
            .body(restaurante);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<RestauranteDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody AtualizarRestauranteDTO dto
    ) {
        RestauranteDTO restaurante = atualizarUseCase.executar(
            id,
            new AtualizarRestauranteUseCase.AtualizarRestauranteInput(
                dto.getNome(),
                dto.getTaxaFrete(),
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getCep(),
                dto.getCidadeId(),
                dto.getCozinhaId()
            )
        );
        return ResponseEntity.ok(restaurante);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        deletarUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }
}
