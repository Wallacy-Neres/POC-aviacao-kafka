package com.zup.aviacao.adapters.kafka.producers;


import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCadastroAeronaveProducer {

    @Value("${spring.kafka.producer.aeronave.topic}")
    private String topicProducer;

    private final AeronaveConverter converter;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produzindoMensagemAeronave(AeronaveResponseDTO dto) {
        log.info("SolicitarCadastroAeronaveProducer - iniciando");
        SolicitarCadastroAeronaveResponse cadastroAeronaveResponse = converter.dtoResponseToAvro(dto);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicProducer, null, null, cadastroAeronaveResponse);
        kafkaTemplate.send(producerRecord);
    }
}
