package com.ferry.food.api.service;

import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cidade;
import com.ferry.food.domain.model.Estado;
import com.ferry.food.domain.repository.CidadeRepository;
import com.ferry.food.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@RequiredArgsConstructor
public class CidadeService {
    public static final String CIDADE_DE_CODIGO_D_NAO_ENCONTRADO = "Cidade de código %d não encontrado";

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    @Transactional(readOnly = true)
    public List<Cidade> listar() {
        return cidadeRepository.listarCidades();
    }

    @Transactional(readOnly = true)
    public Cidade buscarPorId(Long id) {
        Cidade cidade = cidadeRepository.buscarCidade(id);
        if (cidade != null) {
            return cidade;
        }
        throw new MyEntityNotFoundException(
                format(CIDADE_DE_CODIGO_D_NAO_ENCONTRADO, id));
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = estadoRepository.buscarEstado(estadoId);
        if (estado == null) {
            throw new MyEntityNotFoundException(
                    format("Estado de código %d não encontrado", estadoId));
        }
        cidade.setEstado(estado);
        return cidadeRepository.adicionarCidade(cidade);
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeAtual = cidadeRepository.buscarCidade(id);
        if (cidadeAtual != null) {
            Long estadoId = cidade.getEstado().getId();
            Estado estado = estadoRepository.buscarEstado(estadoId);
            if (estado == null) {
                throw new MyEntityNotFoundException(
                        format("Estado de código %d não encontrado", estadoId));
            }
            copyProperties(cidade, cidadeAtual, "id", "estado");
            cidadeAtual.setEstado(estado);
            return cidadeRepository.adicionarCidade(cidadeAtual);
        }
        throw new MyEntityNotFoundException(
                format(CIDADE_DE_CODIGO_D_NAO_ENCONTRADO, id));
    }

    @Transactional
    public void deletar(Long id) {
        Cidade cidade = cidadeRepository.buscarCidade(id);
        if (cidade != null) {
            cidadeRepository.removerCidade(cidade.getId());
        }
        throw new MyEntityNotFoundException(
                format(CIDADE_DE_CODIGO_D_NAO_ENCONTRADO, id));
    }
}
