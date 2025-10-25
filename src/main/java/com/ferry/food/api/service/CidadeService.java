package com.ferry.food.api.service;

import com.ferry.food.domain.exception.MyEntityNotFoundException;
import com.ferry.food.domain.model.Cidade;
import com.ferry.food.domain.model.Estado;
import com.ferry.food.domain.repository.CidadeRepository;
import com.ferry.food.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
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
        return cidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Cidade buscarPorId(Long id) {
        return getOrElseThrow(id);
    }

    @Transactional
    public Cidade salvar(Cidade cidade) {
        Estado estado = estadoRepository.findById(cidade.getEstado().getId())
                .orElseThrow(() -> new MyEntityNotFoundException(format(
                        "Estado de código %d não encontrado", cidade.getEstado().getId())));
        cidade.setEstado(estado);
        return cidadeRepository.save(cidade);
    }

    @Transactional
    public Cidade atualizar(Long id, Cidade cidade) {
        Cidade cidadeAtual = getOrElseThrow(id);
        Estado estado = estadoRepository.findById(cidade.getEstado().getId()).orElseThrow(
                () -> new MyEntityNotFoundException(format(
                        "Estado de código %d não encontrado", cidade.getEstado().getId())));
        copyProperties(cidade, cidadeAtual, "id", "estado");
        cidadeAtual.setEstado(estado);
        return cidadeRepository.save(cidadeAtual);
    }

    @Transactional
    public void deletar(Long id) {
        cidadeRepository.findById(id).ifPresentOrElse(
                cidadeRepository::delete,
                () -> {
                    throw new MyEntityNotFoundException(
                            format(CIDADE_DE_CODIGO_D_NAO_ENCONTRADO, id));
                }
        );
    }

    private Cidade getOrElseThrow(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new MyEntityNotFoundException(
                format(CIDADE_DE_CODIGO_D_NAO_ENCONTRADO, id)));
    }
}
