package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.CompraVooResponseDTO;

public interface CompraVooProducerPort {
    void producerSucesso(CompraVooResponseDTO responseDTO);
}
