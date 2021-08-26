package com.zup.aviacao.port.impl;

import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCadastroVooProducer;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCompraVooProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompraVooProducerPort implements com.zup.aviacao.port.interfaces.CompraVooProducerPort {

    private final SolicitarCompraVooProducer producer;

    @Override
    public void producerSucesso(CompraVooResponseDTO responseDTO) {
        producer.produzindoMensagemCompraVoo(responseDTO);
    }
}
