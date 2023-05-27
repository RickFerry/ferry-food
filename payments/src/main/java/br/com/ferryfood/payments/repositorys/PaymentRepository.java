package br.com.ferryfood.payments.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ferryfood.payments.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long>{ }
