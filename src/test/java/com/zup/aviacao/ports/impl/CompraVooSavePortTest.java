package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.converter.CompraVooConverter;
import com.zup.aviacao.model.CompraVoo;
import com.zup.aviacao.port.impl.CompraVooSavePort;
import com.zup.aviacao.repository.CompraVooRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RunWith(MockitoJUnitRunner.class)
public class CompraVooSavePortTest {

    @InjectMocks
    private CompraVooSavePort compraVooSavePort;

    @Mock
    private CompraVooConverter converter;

    @Mock
    private CompraVooRepository compraVooRepository;

    @Mock
    private EntityManager manager;

    @Test
    public void saveCompraVooTest(){

        CompraVooRequestDTO compraVooRequestDTO = compraVooRequestDTOBuilder();
        compraVooSavePort.saveCompraVoo(compraVooRequestDTO);
        CompraVoo compraVoo = converter.compraVooEntity(compraVooRequestDTO, manager);

        Mockito.verify(compraVooRepository, Mockito.times(1)).save(compraVoo);
    }

    private CompraVooRequestDTO compraVooRequestDTOBuilder() {
        return CompraVooRequestDTO.builder()
                .idVoo(1L)
                .nacionalidade("Brasileira")
                .sexo("Masculino")
                .cpf("123.456.789-05")
                .assento("20")
                .nomeCliente("Varney")
                .dataNascimento(converteStringTOLocalDate("1980-08-04"))
                .celular("2345678")
                .email("varney@gmail.com")
                .build();
    }

    public LocalDate converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, parser);
        return localDate;
    }
}
