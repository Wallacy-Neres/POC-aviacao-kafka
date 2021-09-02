package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.converter.AeronaveConverter;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.Aeronave;
import com.zup.aviacao.model.Assento;
import com.zup.aviacao.port.impl.AeronaveSavePort;
import com.zup.aviacao.repository.AeronaveRepository;
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
public class AeronaveSavePortTest {

    @InjectMocks
    private AeronaveSavePort aeronaveSavePort;

    @Mock
    private AeronaveConverter converter;

    @Mock
    private AeronaveRepository repository;

    @Test
    public void saveAeronaveTest() {

        AeronaveRequestDTO requestDTO = aeronaveRequestBuilder();
        aeronaveSavePort.saveAeronave(requestDTO);
        Aeronave aeronave = converter.aeronavetoEntity(requestDTO);
        Mockito.verify(repository, Mockito.times(1)).save(aeronave);

    }

    private AeronaveRequestDTO aeronaveRequestBuilder() {

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
