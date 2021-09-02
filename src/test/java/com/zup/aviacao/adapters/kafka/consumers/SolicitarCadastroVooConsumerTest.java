package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequestData;
import com.zup.aviacao.usecase.impl.VooUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.support.Acknowledgment;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCadastroVooConsumerTest {

    @InjectMocks
    private SolicitarCadastroVooConsumer solicitarCadastroVooConsumer;

    @Mock
    private VooUseCase useCase;

    @Mock
    private VooConverter converter;

    @Mock
    Acknowledgment acknowledgment;

    @Test
    public void consumer_sucesso(){

        SolicitarCadastroVooRequest avro = criaAvro();

        this.solicitarCadastroVooConsumer.consumer(avro, acknowledgment);

        Mockito.verify(this.useCase, Mockito.times(1)).execute(Mockito.any());
        Mockito.verify(this.acknowledgment, Mockito.times(1)).acknowledge();
    }

    private SolicitarCadastroVooRequest criaAvro() {

        SolicitarCadastroVooRequestData avro = SolicitarCadastroVooRequestData.newBuilder()
                .setDataVoo("2021-08-30")
                .setDestino("Amapá")
                .setOrigem("Cuiabá")
                .setFechaPortao("09:30")
                .setHoraEmbarque("09:00")
                .setTempoDuracao("08:00")
                .setPortao("2")
                .setValor("300.0")
                .setIdAeronave(1L)
                .build();

        return SolicitarCadastroVooRequest.newBuilder()
                .setData(avro)
                .build();
    }

}
