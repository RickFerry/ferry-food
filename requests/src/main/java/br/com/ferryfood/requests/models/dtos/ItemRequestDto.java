package br.com.ferryfood.requests.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequestDto {
        private Long id;
        private Integer amount;
        private String description;
}
