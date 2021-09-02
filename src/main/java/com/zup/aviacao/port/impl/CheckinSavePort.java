package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.repository.CheckinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class CheckinSavePort implements com.zup.aviacao.port.interfaces.CheckinSavePort {

    private final CheckinRepository repository;

    private final CheckinConverter converter;

    private final EntityManager manager;

    @Override
    public CheckinResponseDTO saveCheckin(CheckinRequestDTO requestDTO) {
        return converter.checkinEntityToDto(repository.save(converter.checkinVooEntity(requestDTO, manager)));
    }
}
