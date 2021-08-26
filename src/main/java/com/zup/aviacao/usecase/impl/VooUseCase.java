package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.port.impl.VooPortImpl;
import com.zup.aviacao.port.impl.VooProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class VooUseCase implements com.zup.aviacao.usecase.interfac.VooUseCase {

    private final VooPortImpl vooPort;

    private final VooProducerPort producerPort;

    private final EntityManager manager;

    @Override
    public void execute(VooRequestDTO request) {
        VooResponseDTO vooRequestDTO = vooPort.saveVoo(request);
        producerPort.producerSucesso(vooRequestDTO);
    }
}
