package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.converter.VooConverter;
import com.zup.aviacao.model.Voo;
import com.zup.aviacao.port.impl.VooPortImpl;
import com.zup.aviacao.repository.VooRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class VooPortSaveTest {

    @InjectMocks
    private VooPortImpl vooPort;

    @Mock
    private VooRepository vooRepository;

    @Mock
    private VooConverter converter;

    @Mock
    private EntityManager manager;

    @Test
    public void saveVooTest() {

        VooRequestDTO vooRequestDTO = vooRequestDtoBuilder();
        vooPort.saveVoo(vooRequestDTO);
        Voo voo = converter.vooEntity(vooRequestDTO, manager);
        Mockito.verify(vooRepository, Mockito.times(1)).save(voo);
    }

    private VooRequestDTO vooRequestDtoBuilder() {
        return VooRequestDTO.builder()
                .origem("Guarulhos")
                .destino("Santa Catarina")
                .portao("2")
                .fechaPortao(LocalTime.parse("09:30"))
                .horaEmbarque(LocalTime.parse("09:00"))
                .tempoDuracao(LocalTime.parse("01:00"))
                .valor(BigDecimal.valueOf(2000.0))
                .dataVoo(converteStringTOLocalDate("2021-09-01 10:00"))
                .idAeronave(1L)
                .build();
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }
}
