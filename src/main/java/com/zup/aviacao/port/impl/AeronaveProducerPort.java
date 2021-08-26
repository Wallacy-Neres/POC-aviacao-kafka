package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCadastroAeronaveProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AeronaveProducerPort implements com.zup.aviacao.port.interfaces.AeronaveProducerPort {

    private final SolicitarCadastroAeronaveProducer producer;

    @Override
    public void producerSucesso(AeronaveResponseDTO requestDTO) {
        producer.produzindoMensagemAeronave(requestDTO);
    }
}
