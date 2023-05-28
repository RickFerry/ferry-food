package br.com.ferryfood.requests.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import br.com.ferryfood.requests.models.Request;
import br.com.ferryfood.requests.models.Status;
import br.com.ferryfood.requests.models.dtos.RequestDto;
import br.com.ferryfood.requests.models.dtos.StatusDto;
import br.com.ferryfood.requests.repositorys.RequestRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RequestService {

    @Autowired
    private RequestRepository repository;

    @Autowired
    private final ModelMapper modelMapper;

    public List<RequestDto> getAll() {
        return repository.findAll().stream()
                .map(p -> modelMapper.map(p, RequestDto.class))
                .collect(Collectors.toList());
    }

    public RequestDto getOnebyId(Long id) throws NotFoundException {
        Request request = repository.findById(id)
                .orElseThrow(NotFoundException::new);

        return modelMapper.map(request, RequestDto.class);
    }

    public RequestDto createRequest(RequestDto dto) {
        Request request = modelMapper.map(dto, Request.class);

        request.setDateTime(LocalDate.now());
        request.setStatus(Status.ACCOMPLISHED);
        request.getItems().forEach(item -> item.setRequest(request));
        repository.save(request);

        return modelMapper.map(request, RequestDto.class);
    }

    public RequestDto updateStatus(Long id, StatusDto dto) {

        Request request = repository.porIdComItens(id);

        if (request == null) {
            throw new EntityNotFoundException();
        }

        request.setStatus(dto.getStatus());
        repository.updateStatus(dto.getStatus(), request);
        return modelMapper.map(request, RequestDto.class);
    }

    public void approvePaymentRequest(Long id) {

        Request request = repository.porIdComItens(id);

        if (request == null) {
            throw new EntityNotFoundException();
        }

        request.setStatus(Status.PAID_OUT);
        repository.updateStatus(Status.PAID_OUT, request);
    }
}
