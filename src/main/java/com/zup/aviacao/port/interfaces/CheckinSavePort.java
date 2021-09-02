package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.DTO.CheckinResponseDTO;

public interface CheckinSavePort {
    CheckinResponseDTO saveCheckin(CheckinRequestDTO requestDTO);
}
