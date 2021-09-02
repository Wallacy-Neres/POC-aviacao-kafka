package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCompraVooProducer;
import com.zup.aviacao.port.impl.CompraVooProducerPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompraVooProducerPortTest {

    @InjectMocks
    private CompraVooProducerPort producerPort;

    @Mock
    private SolicitarCompraVooProducer producer;

    @Test
    public void producerSucesso(){
        CompraVooResponseDTO responseDTO = responseDTOBuilder();
        producerPort.producerSucesso(responseDTO);

        Mockito.verify(producer, Mockito.times(1)).produzindoMensagemCompraVoo(responseDTO);
    }

    private CompraVooResponseDTO responseDTOBuilder() {
        return CompraVooResponseDTO.builder()
                .idVoo(1L)
                .cpf("123.456.789-05")
                .nomeCliente("Varney")
                .build();
    }
}
