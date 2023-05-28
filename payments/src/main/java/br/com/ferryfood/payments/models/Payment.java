package br.com.ferryfood.payments.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.ferryfood.payments.enums.Status;
import br.com.ferryfood.payments.models.dtos.PaymentPersistDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "payments")
@NoArgsConstructor
public class Payment {

    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Positive
    @Column(name = "`value`")
    @NotNull(message = "Value not be null")
    private BigDecimal value;

    @Size(max = 100)
    @Column(name = "`name`")
    @NotBlank(message = "Name not be null or blank")
    private String name;

    @Size(max = 19)
    @Column(name = "`number`")
    @NotBlank(message = "Number not be null or blank")
    private String number;

    @Size(max = 7)
    @Column(name = "`expiration`")
    @NotBlank(message = "Expiration not be null or blank")
    private String expiration;

    @Size(min = 3, max = 3)
    @Column(name = "`code`")
    @NotBlank(message = "Code not be null or blank")
    private String code;

    @Column(name = "`status`")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status not be null")
    private Status status;

    @Column(name = "`order_id`")
    @NotNull(message = "Order id not be null")
    private Long orderId;

    @Column(name = "`form_of_payment_Id`")
    @NotNull(message = "Form of payment not be null")
    private Long formOfPaymentId;

    public Payment(PaymentPersistDTO dto) {
        this.id = dto.id();
        this.value = dto.value();
        this.name = dto.name();
        this.number = dto.number();
        this.expiration = dto.expiration();
        this.code = dto.code();
        this.status = Status.CREATED;
        this.orderId = dto.orderId();
        this.formOfPaymentId = dto.formOfPaymentId();
    }
}
