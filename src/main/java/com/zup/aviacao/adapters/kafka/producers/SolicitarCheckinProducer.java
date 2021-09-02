package com.zup.aviacao.adapters.kafka.producers;

import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SolicitarCheckinProducer {

    @Value("${spring.kafka.producer.checkin.topic}")
    private String topicProducer;

    private final CheckinConverter converter;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produzindoMensagemCheckin(CheckinResponseDTO responseDTO){
        log.info("SolicitarCheckinProducer - Iniciando");
        SolicitarCheckinResponse avro = converter.dtoToAvro(responseDTO);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicProducer, null, null, avro);
        kafkaTemplate.send(producerRecord);
    }
}
