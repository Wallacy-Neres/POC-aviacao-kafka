package com.zup.aviacao.adapters.kafka.consumers;

import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.solicitacao_cadastro_aeronave.AssentosListaElementos;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequest;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequestData;
import com.zup.aviacao.usecase.interfac.AeronaveUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.kafka.support.Acknowledgment;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class SolicitarCadastroAeronaveConsumerTest {

    @InjectMocks
    private SolicitarCadastroAeronaveConsumer solicitarCadastroAeronaveConsumer;

    @Mock
    private AeronaveUseCase aeronaveUseCase;

    @Mock
    Acknowledgment acknowledgment;

    @Mock
    private AeronaveConverter converter;

    @Test
    public void consumer_Sucesso() {

        SolicitarCadastroAeronaveRequest avro = criaAvro();

        this.solicitarCadastroAeronaveConsumer.consumer(avro, acknowledgment);

        Mockito.verify(this.aeronaveUseCase, Mockito.times(1)).execute(Mockito.any());
        Mockito.verify(this.acknowledgment, Mockito.times(1)).acknowledge();

    }

    private SolicitarCadastroAeronaveRequest criaAvro() {

        List<String> assentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        SolicitarCadastroAeronaveRequestData avro = SolicitarCadastroAeronaveRequestData.newBuilder()
                .setMarca("Boing")
                .setModelo("747")
                .setAssentos(assentos.stream().map(assento -> AssentosListaElementos.newBuilder()
                        .setNumeroAssento(assento).build()).collect(Collectors.toList()))
                .build();

        return SolicitarCadastroAeronaveRequest.newBuilder()
                .setData(avro)
                .build();
    }

}
