package br.com.ferryfood.payments.models.dtos;

import java.math.BigDecimal;

import br.com.ferryfood.payments.enums.Status;
import br.com.ferryfood.payments.models.Payment;

public record PaymentPersistDTO(
        Long id,
        BigDecimal value,
        String name,
        String number,
        String expiration,
        String code,
        Status status,
        Long orderId,
        Long formOfPaymentId) {

    public PaymentPersistDTO(Payment p) {
        this(p.getId(), p.getValue(), p.getName(), p.getNumber(), p.getExpiration(), p.getCode(), p.getStatus(),
                p.getOrderId(), p.getFormOfPaymentId());
    }
}
