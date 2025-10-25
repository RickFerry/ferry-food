package com.ferry.food.domain.repository;

import com.ferry.food.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<FormaPagamento, Long> {
}
