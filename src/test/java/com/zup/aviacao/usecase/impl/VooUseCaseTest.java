package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.port.impl.VooPortImpl;
import com.zup.aviacao.port.impl.VooProducerPort;
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
public class VooUseCaseTest {

    @InjectMocks
    private VooUseCase vooUseCase;

    @Mock
    private VooPortImpl vooPort;

    @Mock
    private VooProducerPort producerPort;

    @Mock
    private EntityManager manager;

    @Test
    public void executeTest(){

        VooRequestDTO requestDTO = requestDTOBuilder();
        vooUseCase.execute(requestDTO);

        Mockito.verify(vooPort, Mockito.times(1)).saveVoo(Mockito.any());
        Mockito.verify(producerPort, Mockito.times(1)).producerSucesso(Mockito.any());
    }

    private VooRequestDTO requestDTOBuilder() {
        return VooRequestDTO.builder()
                .idAeronave(1L)
                .dataVoo(converteStringTOLocalDate("2021-10-02 10:00"))
                .horaEmbarque(LocalTime.parse("09:00"))
                .fechaPortao(LocalTime.parse("09:30"))
                .portao("2")
                .tempoDuracao(LocalTime.parse("05:00"))
                .origem("Guarulhos")
                .destino("Paris")
                .valor(BigDecimal.valueOf(2000.0))
                .build();

    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }
}
