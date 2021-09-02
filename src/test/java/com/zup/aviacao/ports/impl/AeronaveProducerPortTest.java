package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCadastroAeronaveProducer;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.Assento;
import com.zup.aviacao.port.impl.AeronaveProducerPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class AeronaveProducerPortTest {

    @InjectMocks
    private AeronaveProducerPort aeronaveProducerPort;

    @Mock
    private SolicitarCadastroAeronaveProducer producer;

    @Test
    public void producerSucesso() {

        AeronaveResponseDTO responseDTO = responseDTOBuilder();
        aeronaveProducerPort.producerSucesso(responseDTO);

        Mockito.verify(producer, Mockito.times(1)).produzindoMensagemAeronave(responseDTO);
    }

    private AeronaveResponseDTO responseDTOBuilder() {

        List<String> listaAssentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<Assento> assentos = listaAssentos.stream().map(assento -> Assento.builder()
                .assento(assento)
                .statusAssento(StatusAssento.DISPONIVEL)
                .build()).collect(Collectors.toList());

        return AeronaveResponseDTO.builder()
                .marca("Boing")
                .modelo("747")
                .assentos(assentos)
                .build();

    }
}
