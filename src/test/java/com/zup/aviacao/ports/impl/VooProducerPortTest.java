package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCadastroVooProducer;
import com.zup.aviacao.port.impl.VooProducerPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class VooProducerPortTest {

    @InjectMocks
    private VooProducerPort producerPort;

    @Mock
    private SolicitarCadastroVooProducer producer;

    @Test
    public void producerSucesso(){
        VooResponseDTO vooResponseDTO = vooResponseDTOBuilder();
        producerPort.producerSucesso(vooResponseDTO);

        Mockito.verify(producer, Mockito.times(1)).produzindoMensagemVoo(vooResponseDTO);
    }

    private VooResponseDTO vooResponseDTOBuilder() {
        return VooResponseDTO.builder()
                .origem("Guarulhos")
                .destino("Reino Unido")
                .dataVoo(converteStringTOLocalDate("2022-09-10 14:30"))
                .tempoDuracao(LocalTime.parse("06:00"))
                .valor(BigDecimal.valueOf(2000.0))
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }
}
