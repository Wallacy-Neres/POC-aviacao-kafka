package com.zup.aviacao.adapters.kafka.producers;


import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.converter.CheckinConverter;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCheckinProducerTest {

    @InjectMocks
    private SolicitarCheckinProducer producer;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private CheckinConverter converter;

    @Test
    public void producer_sucesso(){
        ReflectionTestUtils.setField(this.producer, "topicProducer", "abc");
        this.producer.produzindoMensagemCheckin(criaResponse());
        Mockito.verify(this.kafkaTemplate, Mockito.times(1)).send(Mockito.any(ProducerRecord.class));
    }

    private CheckinResponseDTO criaResponse() {
        return CheckinResponseDTO.builder()
                .tempoDuracao(LocalTime.parse("05:00"))
//                .dataVoo(LocalDateTime.parse("2021-08-30"))
                .origem("Guarulhos")
                .destino("Curitiba")
                .fechaPortao(LocalTime.parse("10:00"))
                .horaEmbarque(LocalTime.parse("09:30"))
                .portao("2")
                .nome("Varney")
                .assento("30")
                .build();
    }
}
