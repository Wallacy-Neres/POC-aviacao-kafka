package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.port.interfaces.AeronavePort;
import com.zup.aviacao.port.interfaces.AeronaveProducerPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AeronaveUseCase implements com.zup.aviacao.usecase.interfac.AeronaveUseCase {

    private final AeronavePort aeronavePort;

    private final AeronaveProducerPort aeronaveProducerPort;


    @Override
    public void execute(AeronaveRequestDTO request) {
        AeronaveResponseDTO aeronaveResponseDTO = aeronavePort.saveAeronave(request);
        aeronaveProducerPort.producerSucesso(aeronaveResponseDTO);
    }
}
