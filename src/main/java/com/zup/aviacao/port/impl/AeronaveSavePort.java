package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.repository.AeronaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AeronaveSavePort implements com.zup.aviacao.port.interfaces.AeronavePort {

    private final AeronaveRepository repository;

    private final AeronaveConverter converter;

    @Override
    public AeronaveResponseDTO saveAeronave(AeronaveRequestDTO request) {
        return converter.aeronaveEntityToDto(repository.save(converter.aeronavetoEntity(request)));
    }
}
