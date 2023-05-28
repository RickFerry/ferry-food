package br.com.ferryfood.requests.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.ferryfood.requests.models.dtos.RequestDto;
import br.com.ferryfood.requests.models.dtos.StatusDto;
import br.com.ferryfood.requests.services.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping()
    public List<RequestDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestDto> getOnebyId(@PathVariable @NotNull Long id) {
        RequestDto dto = null;
        try {
            dto = service.getOnebyId(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/port")
    public String returnPort(@Value("${local.server.port}") String port) {
        return String.format("Request answered by the instance running on the port %s", port);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<RequestDto> createRequest(@RequestBody @Valid RequestDto dto,
            UriComponentsBuilder uriBuilder) {
        RequestDto request = service.createRequest(dto);

        URI url = uriBuilder.path("/requests/{id}").buildAndExpand(request.getId()).toUri();

        return ResponseEntity.created(url).body(request);
    }

    @Transactional
    @PutMapping("/{id}/status")
    public ResponseEntity<RequestDto> updateStatus(@PathVariable Long id, @RequestBody StatusDto status) {
        RequestDto dto = service.updateStatus(id, status);

        return ResponseEntity.ok(dto);
    }

    @Transactional
    @PutMapping("/{id}/paidout")
    public ResponseEntity<Void> approvePaymentRequest(@PathVariable @NotNull Long id) {
        service.approvePaymentRequest(id);

        return ResponseEntity.ok().build();
    }
}
