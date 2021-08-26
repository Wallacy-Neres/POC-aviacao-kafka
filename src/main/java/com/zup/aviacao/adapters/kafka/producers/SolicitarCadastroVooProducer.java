package com.zup.aviacao.adapters.kafka.producers;

import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCadastroVooProducer {

    @Value("${spring.kafka.producer.voo.topic}")
    private String topicProducer;

    private final VooConverter converter;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void produzindoMensagemVoo(VooResponseDTO dto) {
        log.info("SolicitarCadastroVooProducer - iniciando");
        SolicitarCadastroVooResponse cadastroVooResponse = converter.dtoResponseToAvro(dto);
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topicProducer, null, null, cadastroVooResponse);
        kafkaTemplate.send(producerRecord);
    }
}
