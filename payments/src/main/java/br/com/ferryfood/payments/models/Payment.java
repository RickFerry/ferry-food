package br.com.ferryfood.payments.models;

import java.math.BigDecimal;

import br.com.ferryfood.payments.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @NotNull(message = "Value not be null")
    private BigDecimal value;

    @Size(max = 100)
    @NotBlank(message = "Name not be null or blank")
    private String name;

    @Size(max = 19)
    @NotBlank(message = "Number not be null or blank")
    private String number;

    @Size(max = 7)
    @NotBlank(message = "Expiration not be null or blank")
    private String expiration;

    @Size(min = 3, max = 3)
    @NotBlank(message = "Code not be null or blank")
    private String code;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status not be null")
    private Status status;

    @NotNull(message = "Order id not be null")
    private Long orderId;

    @NotNull(message = "Form of payment not be null")
    private Long formOfPaymentId;
}
