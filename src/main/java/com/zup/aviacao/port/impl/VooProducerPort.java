package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCadastroVooProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VooProducerPort implements com.zup.aviacao.port.interfaces.VooProducerPort {

    private final SolicitarCadastroVooProducer producer;

    @Override
    public void producerSucesso(VooResponseDTO requestDTO) {
        producer.produzindoMensagemVoo(requestDTO);
    }
}
