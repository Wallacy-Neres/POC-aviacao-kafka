package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.port.interfaces.AeronavePort;
import com.zup.aviacao.port.interfaces.AeronaveProducerPort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class AeronaveUseCaseTest {

    @InjectMocks
    private AeronaveUseCase aeronaveUseCase;

    @Mock
    private AeronavePort aeronavePort;

    @Mock
    private AeronaveProducerPort aeronaveProducerPort;

    @Mock
    private AeronaveConverter converter;

    @Test
    public void executeTest(){
        AeronaveRequestDTO requestDTO = requestDTOBuilder();
        aeronaveUseCase.execute(requestDTO);

        Mockito.verify(aeronavePort, Mockito.times(1)).saveAeronave(Mockito.any());
        Mockito.verify(aeronaveProducerPort, Mockito.times(1)).producerSucesso(Mockito.any());
    }

    private AeronaveRequestDTO requestDTOBuilder() {
        List<String> listaAssentos = new ArrayList<String>() {
            {
                add("20");
                add("30");
                add("40");
            }
        };

        List<AssentoRequestDTO> assentos = listaAssentos.stream().map(assento -> AssentoRequestDTO.builder()
                .numeroAssento(assento).build()).collect(Collectors.toList());

        return AeronaveRequestDTO.builder()
                .marca("Boing")
                .modelo("747")
                .assentos(assentos)
                .build();
    }
}
