package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.port.impl.CheckinSavePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckinUseCase implements com.zup.aviacao.usecase.interfac.CheckinUseCase {

    private final CheckinSavePort savePort;


    @Override
    public void execute(CheckinRequestDTO requestDTO) {
        CheckinResponseDTO response = savePort.saveCheckin(requestDTO);

    }
}
