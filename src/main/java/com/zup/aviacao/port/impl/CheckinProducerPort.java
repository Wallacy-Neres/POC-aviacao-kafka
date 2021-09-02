package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCheckinProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckinProducerPort implements com.zup.aviacao.port.interfaces.CheckinProducerPort {

    private final SolicitarCheckinProducer producer;

    @Override
    public void producerSucesso(CheckinResponseDTO responseDTO) {
        producer.produzindoMensagemCheckin(responseDTO);
    }
}
