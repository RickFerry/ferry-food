package br.com.ferryfood.requests.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import br.com.ferryfood.requests.models.Request;
import br.com.ferryfood.requests.models.Status;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Request p set p.status = :status where p = :request")
    void updateStatus(Status status, Request request);

    @Query(value = "SELECT p from Request p LEFT JOIN FETCH p.items where p.id = :id")
    Request porIdComItens(Long id);
}
