package com.zup.aviacao.adapters.kafka.producers;


import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCompraVooProducerTest {

    @InjectMocks
    private SolicitarCompraVooProducer producer;

    @Mock
    private CompraVooConverter converter;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void producer_sucesso(){
        ReflectionTestUtils.setField(this.producer, "topicProducer", "abc");
        this.producer.produzindoMensagemCompraVoo(criaResponse());
        Mockito.verify(this.kafkaTemplate, Mockito.times(1)).send(Mockito.any(ProducerRecord.class));
    }

    private CompraVooResponseDTO criaResponse() {
        return CompraVooResponseDTO.builder()
                .idVoo(1L)
                .cpf("223.968.458-25")
                .nomeCliente("Varney")
                .build();
    }
}
