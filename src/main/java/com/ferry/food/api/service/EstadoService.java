package com.ferry.food.api.service;

import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Estado;
import com.ferry.food.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Estado> listar() {
        return estadoRepository.listarEstados();
    }

    @Transactional(readOnly = true)
    public Estado buscarPorId(Long id) {
        Estado estado = getEstadoAtualOrNull(id);
        if (estado != null) {
            return estado;
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.adicionarEstado(estado);
    }

    @Transactional
    public Estado atualizar(Long id, Estado estado) {
        Estado estadoAtual = getEstadoAtualOrNull(id);
        if (estadoAtual != null) {
            copyProperties(estadoAtual, estado, "id");
            return estadoRepository.adicionarEstado(estadoAtual);
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    @Transactional
    public void deletar(Long id) {
        Estado estado = getEstadoAtualOrNull(id);
        if (estado != null) {
            estadoRepository.removerEstado(estado.getId());
        }
        throw new MyEntityNotFoundException(format("Estado de código %d não encontrado", id));
    }

    private Estado getEstadoAtualOrNull(Long id) {
        return estadoRepository.buscarEstado(id);
    }
}
