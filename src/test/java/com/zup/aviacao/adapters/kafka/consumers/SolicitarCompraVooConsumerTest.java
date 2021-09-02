package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequestData;
import com.zup.aviacao.usecase.impl.CompraVooUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.support.Acknowledgment;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCompraVooConsumerTest {

    @InjectMocks
    private SolicitarCompraVooConsumer solicitarCompraVooConsumer;

    @Mock
    private CompraVooConverter converter;

    @Mock
    private CompraVooUseCase useCase;

    @Mock
    Acknowledgment acknowledgment;

    @Test
    public void consumer_sucesso(){

        SolicitarCompraVooRequest avro = criaAvro();

        this.solicitarCompraVooConsumer.consumer(avro, acknowledgment);

        Mockito.verify(this.useCase, Mockito.times(1)).execute(Mockito.any());
        Mockito.verify(this.acknowledgment, Mockito.times(1)).acknowledge();
    }

    private SolicitarCompraVooRequest criaAvro() {

        SolicitarCompraVooRequestData avro = SolicitarCompraVooRequestData.newBuilder()
                .setNomeCliente("Varney")
                .setEmail("varney@gmail.com")
                .setCelular("2345678")
                .setCpf("225.684.987-85")
                .setSexo("Masculino")
                .setAssento("20")
                .setNacionalidade("Brasileira")
                .setIdVoo(1L)
                .build();

        return SolicitarCompraVooRequest.newBuilder()
                .setData(avro)
                .build();
    }
}
