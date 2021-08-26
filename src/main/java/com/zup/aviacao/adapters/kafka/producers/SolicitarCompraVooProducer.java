package com.zup.aviacao.adapters.kafka.producers;

import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCompraVooProducer {
    @Value("${spring.kafka.producer.compra-voo.topic}")
    private String topicProducer;

    private final CompraVooConverter converter;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produzindoMensagemCompraVoo(CompraVooResponseDTO responseDTO){
        log.info("SolicitaCompraVooProducer - INICIANDO");

        SolicitarCompraVooResponse CompraVooResponse = converter.dtoResponseToAvro(responseDTO);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicProducer, null, null, CompraVooResponse);
        kafkaTemplate.send(producerRecord);
    }
}
