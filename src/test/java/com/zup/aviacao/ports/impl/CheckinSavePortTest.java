package com.zup.aviacao.ports.impl;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.converter.CheckinConverter;
import com.zup.aviacao.model.CheckinVoo;
import com.zup.aviacao.port.impl.CheckinSavePort;
import com.zup.aviacao.repository.CheckinRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class CheckinSavePortTest {

    @InjectMocks
    private CheckinSavePort checkinSavePort;

    @Mock
    private CheckinRepository repository;

    @Mock
    private CheckinConverter converter;

    @Mock
    private EntityManager manager;

    @Test
    public void saveCheckinTest(){

        CheckinRequestDTO requestDTO = requestDTOBuilder();
        checkinSavePort.saveCheckin(requestDTO);
        CheckinVoo checkinVoo = converter.checkinVooEntity(requestDTO, manager);

        Mockito.verify(repository, Mockito.times(1)).save(checkinVoo);
    }

    private CheckinRequestDTO requestDTOBuilder() {
        return CheckinRequestDTO.builder()
                .idCompraVoo(1L)
                .cpf("123.456.789-05")
                .build();
    }
}
