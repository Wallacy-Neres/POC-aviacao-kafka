package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequest;
import com.zup.aviacao.usecase.impl.VooUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCadastroVooConsumer {

    private final VooConverter converter;

    private final VooUseCase useCase;


    @KafkaListener(topics = "${spring.kafka.consumer.voo.topic}", groupId = "${spring.kafka.consumer.voo.group-id}")
    public void consumer(@Payload SolicitarCadastroVooRequest request, Acknowledgment acknowledgment){
        log.info("SolicitaCadastroVooRequest - INICIANDO");
        VooRequestDTO vooRequestDTO = converter.avroToDto(request);
        useCase.execute(vooRequestDTO);
        acknowledgment.acknowledge();
        log.info("SolicitaCadastroVooRequest - FINALIZADA");
    }
}
