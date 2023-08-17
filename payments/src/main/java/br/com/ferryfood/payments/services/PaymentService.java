package br.com.ferryfood.payments.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.ferryfood.payments.enums.Status;
import br.com.ferryfood.payments.http.PaymentClient;
import br.com.ferryfood.payments.models.Payment;
import br.com.ferryfood.payments.models.dtos.PaymentDetailDTO;
import br.com.ferryfood.payments.models.dtos.PaymentPersistDTO;
import br.com.ferryfood.payments.repositorys.PaymentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    /**
     *
     */
    private static final String ENTITY_NOT_FOUND = "Entity not found!";
    /**
     *
     */
    private PaymentRepository paymentRepository;
    /**
     *
     */
    private PaymentClient paymentClient;

    /**
     * @param pageable
     * @return All
     */
    public final Page<PaymentDetailDTO> getAllPayments(
            final Pageable pageable) {
        return paymentRepository.findAll(pageable).map(PaymentDetailDTO::new);
    }

    /**
     * @param id
     * @return Payment
     */
    public final PaymentDetailDTO getOnePayment(final Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        return new PaymentDetailDTO(p);
    }

    /**
     * @param dto
     * @return Dto
     */
    public final PaymentPersistDTO savePayment(final PaymentPersistDTO dto) {
        return new PaymentPersistDTO(paymentRepository.save(new Payment(dto)));
    }

    /**
     * @param id
     * @param dto
     * @return Dto
     */
    public final PaymentDetailDTO updatePayment(
            final Long id, final PaymentDetailDTO dto) {
        Payment payment = paymentRepository.getById(id);
        BeanUtils.copyProperties(dto, payment, "id");
        return new PaymentDetailDTO(paymentRepository.save(payment));
    }

    /**
     * @param id
     */
    public final void deletePayment(final Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        paymentRepository.delete(p);
    }

    /**
     * @param id
     */
    public final void confirmPayment(final Long id) {
        Payment p = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        p.setStatus(Status.CONFIRMED);
        paymentRepository.save(p);
        paymentClient.updatepayment(id);
    }

    /**
     * @param id
     * @param e
     */
    public final void changeStatus(final Long id, final Exception e) {
        Payment p = paymentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(ENTITY_NOT_FOUND));
        p.setStatus(Status.CONFIRMED_NO_INTEGRATION);
        paymentRepository.save(p);
    }
}
