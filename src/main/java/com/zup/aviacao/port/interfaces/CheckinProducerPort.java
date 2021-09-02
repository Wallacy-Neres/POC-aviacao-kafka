package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.CheckinResponseDTO;

public interface CheckinProducerPort {
    void producerSucesso(CheckinResponseDTO responseDTO);
}
