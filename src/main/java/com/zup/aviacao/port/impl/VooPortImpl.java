package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.port.interfaces.VooPort;
import com.zup.aviacao.repository.VooRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class VooPortImpl implements VooPort {

    private final VooRepository vooRepository;

    private final VooConverter converter;

    private final EntityManager manager;

    @Override
    public VooResponseDTO saveVoo(VooRequestDTO request) {
        return converter.vooEntityToDto(vooRepository.save(converter.vooEntity(request, manager)));
    }
}
