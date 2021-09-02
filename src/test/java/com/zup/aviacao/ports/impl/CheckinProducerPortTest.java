package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.adapters.kafka.producers.SolicitarCheckinProducer;
import com.zup.aviacao.port.impl.CheckinProducerPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CheckinProducerPortTest {

    @InjectMocks
    private CheckinProducerPort producerPort;

    @Mock
    private SolicitarCheckinProducer producer;

    @Test
    public void producerSucessoTest(){
        CheckinResponseDTO responseDTO = responseDTOBuilder();
        producerPort.producerSucesso(responseDTO);

        Mockito.verify(producer, Mockito.times(1)).produzindoMensagemCheckin(responseDTO);
    }

    private CheckinResponseDTO responseDTOBuilder() {
        return CheckinResponseDTO.builder()
                .nome("Varney")
                .origem("Guarulhos")
                .destino("Holanda")
                .portao("2")
                .dataVoo(converteStringTOLocalDate("2022-09-05 14:00"))
                .tempoDuracao(LocalTime.parse("09:00"))
                .assento("12")
                .horaEmbarque(LocalTime.parse("13:00"))
                .fechaPortao(LocalTime.parse("13:40"))
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }

}
