package br.com.ferryfood.payments.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ferryfood.payments.models.Payment;
import br.com.ferryfood.payments.models.dtos.PaymentDetailDTO;
import br.com.ferryfood.payments.models.dtos.PaymentPersistDTO;
import br.com.ferryfood.payments.repositorys.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    private PaymentRepository paymentRepository;

    public Page<PaymentDetailDTO> getAllPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable).map(PaymentDetailDTO::new);
    }

    public PaymentDetailDTO getOnePayment(Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        return new PaymentDetailDTO(p);
    }

    public PaymentPersistDTO savePayment(PaymentPersistDTO dto) {
        return new PaymentPersistDTO(paymentRepository.save(new Payment(dto)));
    }

    public PaymentDetailDTO updatePayment(Long id, PaymentDetailDTO dto) {
        Payment payment = paymentRepository.getReferenceById(id);
        BeanUtils.copyProperties(dto, payment, "id");
        return new PaymentDetailDTO(paymentRepository.save(payment));
    }

    public void deletePayment(Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
        paymentRepository.delete(p);
    }
}
