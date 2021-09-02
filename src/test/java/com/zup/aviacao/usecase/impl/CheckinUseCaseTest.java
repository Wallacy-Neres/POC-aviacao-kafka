package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.port.impl.CheckinSavePort;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckinUseCaseTest {

    @InjectMocks
    private CheckinUseCase useCase;

    @Mock
    private CheckinSavePort checkinSavePort;

    @Test
    public void executeTest(){

        CheckinRequestDTO requestDTO = requestDTOBuilder();
        useCase.execute(requestDTO);

        Mockito.verify(checkinSavePort, Mockito.times(1)).saveCheckin(requestDTO);
    }



    private CheckinRequestDTO requestDTOBuilder() {
        return CheckinRequestDTO.builder()
                .cpf("123.456.789-05")
                .idCompraVoo(1L)
                .build();
    }
}
