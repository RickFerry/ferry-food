package br.com.ferryfood.payments.controllers;

import java.net.URI;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ferryfood.payments.models.dtos.PaymentDetailDTO;
import br.com.ferryfood.payments.models.dtos.PaymentPersistDTO;
import br.com.ferryfood.payments.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    /**
     *
     */
    private static final int N10 = 10;
    /**
     *
     */
    private PaymentService paymentService;

    /**
     * @param page
     * @return Payment
     */
    @GetMapping
    public final ResponseEntity<Page<PaymentDetailDTO>> getAllPayments(
            @PageableDefault(size = N10) final Pageable page) {
        return ResponseEntity.ok(paymentService.getAllPayments(page));
    }

    /**
     * @param id
     * @return Payment
     */
    @GetMapping("/{id}")
    public final ResponseEntity<PaymentDetailDTO> getOnePayment(
            @PathVariable @NotNull final Long id) {
        return ResponseEntity.ok(paymentService.getOnePayment(id));
    }

    /**
     * @param dto
     * @param uri
     * @return Dto
     */
    @PostMapping
    @Transactional
    public final ResponseEntity<PaymentPersistDTO> savePayment(
            @RequestBody final PaymentPersistDTO dto,
            final UriComponentsBuilder uri) {
        PaymentPersistDTO p = paymentService.savePayment(dto);
        URI url = uri.path("/payments/{id}").buildAndExpand(p.id()).toUri();
        return ResponseEntity.created(url).body(p);
    }

    /**
     * @param id
     * @param dto
     * @return Payment
     */
    @Transactional
    @PutMapping("/{id}")
    public final ResponseEntity<PaymentDetailDTO> updatePayment(
            @PathVariable @NotNull final Long id,
            @RequestBody final PaymentDetailDTO dto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, dto));
    }

    /**
     * @param id
     * @return Null
     */
    @Transactional
    @DeleteMapping("/{id}")
    public final ResponseEntity<Void> deletePayment(
            @PathVariable @NotNull final Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * @param id
     */
    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name = "updateRequest",
        fallbackMethod = "confirmedNoIntegration")
    public final void confirmPayment(@PathVariable @NotNull final Long id) {
        paymentService.confirmPayment(id);
    }

    /**
     * @param id
     * @param e
     */
    public final void confirmedNoIntegration(final Long id, final Exception e) {
        paymentService.changeStatus(id, e);
    }
}
