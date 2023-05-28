package br.com.ferryfood.requests.models.dtos;

import br.com.ferryfood.requests.models.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusDto {
    private Status status;
}
