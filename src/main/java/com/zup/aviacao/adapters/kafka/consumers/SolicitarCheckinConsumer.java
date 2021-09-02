package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequest;
import com.zup.aviacao.usecase.impl.CheckinUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SolicitarCheckinConsumer {

    private final CheckinConverter converter;

    private final CheckinUseCase useCase;

    @KafkaListener(topics = "${spring.kafka.consumer.checkin.topic}", groupId = "${spring.kafka.consumer.checkin.group-id}")
    public void consumer(@Payload SolicitarCheckinRequest avro, Acknowledgment acknowledgment){
        log.info("SolicitaCheckinRequest - Iniciando consumo de mensagem");
        CheckinRequestDTO checkin = converter.avroToDto(avro);
        useCase.execute(checkin);
        acknowledgment.acknowledge();
        log.info("SolicitaCheckinRequest - Mensagem comitada");
    }
}
