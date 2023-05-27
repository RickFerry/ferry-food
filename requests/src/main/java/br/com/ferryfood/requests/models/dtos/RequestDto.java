package br.com.ferryfood.requests.models.dtos;

import java.time.LocalDateTime;
import java.util.List;

import br.com.ferryfood.requests.models.Status;

public record RequestDto(
        Long id,
        LocalDateTime dataHora,
        Status status,
        List<ItemRequestDto> itens) {
}
