package com.zup.aviacao.adapters.kafka.producers;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCadastroAeronaveProducerTest {

    @InjectMocks
    private SolicitarCadastroAeronaveProducer producer;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Mock
    private AeronaveConverter converter;


    @Test
    public void producer_sucesso(){

        ReflectionTestUtils.setField(this.producer, "topicProducer", "abc");
        this.producer.produzindoMensagemAeronave(criaRequest());
        Mockito.verify(this.kafkaTemplate, Mockito.times(1)).send(Mockito.any(ProducerRecord.class));
    }

    private AeronaveResponseDTO criaRequest(){

        List<String> lista = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<AssentoRequestDTO> assentos = lista.stream().map(assento -> AssentoRequestDTO.builder()
                .numeroAssento(assento)
                .build()).collect(Collectors.toList());

        return AeronaveResponseDTO.builder()
                .marca("Boing")
                .modelo("747")
                .build();
    }
}
