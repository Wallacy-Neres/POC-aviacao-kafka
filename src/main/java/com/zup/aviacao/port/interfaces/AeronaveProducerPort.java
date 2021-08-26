package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.AeronaveResponseDTO;

public interface AeronaveProducerPort {
     void producerSucesso(AeronaveResponseDTO requestDTO);
}
