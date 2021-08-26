package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.repository.CompraVooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;


@Component
@RequiredArgsConstructor
public class CompraVooSavePort implements com.zup.aviacao.port.interfaces.CompraVooPort {

    private final CompraVooConverter converter;

    private final CompraVooRepository repository;

    private final EntityManager manager;

    @Override
    public CompraVooResponseDTO saveCompraVoo(CompraVooRequestDTO request) {
        return converter.compraVooEntityToDto(repository.save(converter.compraVooEntity(request, manager)));
    }
}
