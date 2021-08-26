package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequest;
import com.zup.aviacao.usecase.interfac.CompraVooUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Log4j
@Component
@RequiredArgsConstructor
public class SolicitarCompraVooConsumer {

    private final CompraVooConverter converter;

    private final CompraVooUseCase useCase;

    @KafkaListener(topics = "${spring.kafka.consumer.compra-voo.topic}", groupId = "${spring.kafka.consumer.compra-voo.group-id}")
        public void consumer(SolicitarCompraVooRequest avro, final Acknowledgment acknowledgment){

        log.info("SolicitarCompraVooRequest - INICIANDO");
        CompraVooRequestDTO compraVooRequestDTO = converter.avroToDto(avro);

        useCase.execute(compraVooRequestDTO);
        acknowledgment.acknowledge();

        log.info("SolicitaCadastroVooRequest - FINALIZADA");
    }
}
