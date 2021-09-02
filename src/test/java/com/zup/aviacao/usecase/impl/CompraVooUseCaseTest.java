package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.port.impl.CompraVooSavePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CompraVooUseCaseTest {

    @InjectMocks
    private CompraVooUseCase useCase;

    @Mock
    private CompraVooSavePort compraVooSavePort;

    @Test
    public void compraVooSavePortTest(){

        CompraVooRequestDTO compraVooRequestDTO = compraVooRequestDTOBuilder();
        useCase.execute(compraVooRequestDTO);

        Mockito.verify(compraVooSavePort, Mockito.times(1)).saveCompraVoo(compraVooRequestDTO);
    }

    private CompraVooRequestDTO compraVooRequestDTOBuilder() {
        return CompraVooRequestDTO.builder()
                .idVoo(1L)
                .email("varney@gmail.com")
                .celular("2345678")
                .dataNascimento(converteStringToLocalDate("1980-09-25"))
                .assento("30")
                .sexo("Masculino")
                .nacionalidade("Brasileira")
                .cpf("123.456.789-05")
                .build();

    }

    public LocalDate converteStringToLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, parser);
        return localDate;
    }
}
