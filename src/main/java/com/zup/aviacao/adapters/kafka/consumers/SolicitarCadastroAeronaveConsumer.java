package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequest;
import com.zup.aviacao.usecase.interfac.AeronaveUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCadastroAeronaveConsumer {

    private final AeronaveConverter converter;

    private final AeronaveUseCase useCase;

    @KafkaListener(topics = "${spring.kafka.consumer.aeronave.topic}", groupId = "${spring.kafka.consumer.aeronave.group-id}")
    public void consumer(@Payload SolicitarCadastroAeronaveRequest request, final Acknowledgment acknowledgment){
        log.info("SolicitaCadastroVooRequest - INICIANDO");
        AeronaveRequestDTO aeronaveSave = converter.avroToDto(request);
        useCase.execute(aeronaveSave);
        acknowledgment.acknowledge();
        log.info("SolicitaCadastroVooRequest - FINALIZADA");
    }
}
