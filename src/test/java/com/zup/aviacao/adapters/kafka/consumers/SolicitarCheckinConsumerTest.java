package com.zup.aviacao.adapters.kafka.consumers;


import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequestData;
import com.zup.aviacao.usecase.impl.CheckinUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.support.Acknowledgment;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCheckinConsumerTest {

    @InjectMocks
    private SolicitarCheckinConsumer solicitarCheckinConsumer;

    @Mock
    private CheckinConverter converter;

    @Mock
    private CheckinUseCase useCase;

    @Mock
    Acknowledgment acknowledgment;

    @Test
    public void consumer_sucesso(){

        SolicitarCheckinRequest avro = criaAvro();

        this.solicitarCheckinConsumer.consumer(avro, acknowledgment);

        Mockito.verify(this.useCase, Mockito.times(1)).execute(Mockito.any());
        Mockito.verify(this.acknowledgment, Mockito.times(1)).acknowledge();

    }

    private SolicitarCheckinRequest criaAvro() {

        SolicitarCheckinRequestData avro = SolicitarCheckinRequestData.newBuilder()
                .setCpf("356.987.968-05")
                .setIdVoo(1L)
                .build();

        return SolicitarCheckinRequest.newBuilder()
                .setData(avro)
                .build();
    }

}
