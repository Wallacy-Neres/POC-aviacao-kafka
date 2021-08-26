package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.VooResponseDTO;

public interface VooProducerPort {
    void producerSucesso(VooResponseDTO requestDTO);
}
