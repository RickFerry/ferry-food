package br.com.ferryfood.payments.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient("requests-ms")
public interface PaymentClient {

    /**
     * @param id
     */
    @PutMapping("requests/{id}/paidout")
    void updatepayment(@PathVariable Long id);
}
