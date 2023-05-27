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
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<Page<PaymentDetailDTO>> getAllPayments(@PageableDefault(size = 10) Pageable page) {
        return ResponseEntity.ok(paymentService.getAllPayments(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDetailDTO> getOnePayment(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(paymentService.getOnePayment(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PaymentPersistDTO> savePayment(@RequestBody PaymentPersistDTO dto, UriComponentsBuilder uri) {
        PaymentPersistDTO p = paymentService.savePayment(dto);
        URI url = uri.path("/payments/{id}").buildAndExpand(p.id()).toUri();
        return ResponseEntity.created(url).body(p);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<PaymentDetailDTO> updatePayment(@PathVariable @NotNull Long id, @RequestBody PaymentDetailDTO dto) {
        return ResponseEntity.ok(paymentService.updatePayment(id, dto));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable @NotNull Long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
