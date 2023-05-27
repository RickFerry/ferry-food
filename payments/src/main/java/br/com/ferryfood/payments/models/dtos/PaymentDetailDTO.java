package br.com.ferryfood.payments.models.dtos;

import java.math.BigDecimal;

import br.com.ferryfood.payments.models.Payment;

public record PaymentDetailDTO(
        Long id,
        BigDecimal value,
        String name,
        String number,
        String expiration,
        String status,
        Long orderId,
        Long formOfPaymentId) {

    public PaymentDetailDTO(Payment p) {
        this(p.getId(), p.getValue(), p.getName(), p.getNumber(), p.getExpiration(), p.getStatus().toString(), p.getOrderId(),
                p.getFormOfPaymentId());
    }
}
