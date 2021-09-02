package com.zup.aviacao.adapters.kafka.producers;

import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.converter.VooConverter;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCadastroVooProducerTest {

    @InjectMocks
    private SolicitarCadastroVooProducer producer;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private VooConverter converter;

    @Test
    public void producer_sucesso(){

        ReflectionTestUtils.setField(this.producer, "topicProducer", "abc");
        this.producer.produzindoMensagemVoo(criaResponseVoo());
        Mockito.verify(this.kafkaTemplate, Mockito.times(1)).send(Mockito.any(ProducerRecord.class));
    }

    private VooResponseDTO criaResponseVoo() {
        return VooResponseDTO.builder()
                .origem("Guarulhos")
                .destino("Curitiba")
                .tempoDuracao(LocalTime.parse("09:00"))
//                .dataVoo(converteStringTOLocalDate("2021-08-26"))
                .valor(BigDecimal.valueOf(2000.0))
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }
}
