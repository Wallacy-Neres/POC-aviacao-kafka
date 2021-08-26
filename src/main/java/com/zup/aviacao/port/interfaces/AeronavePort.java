package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;

public interface AeronavePort {

     AeronaveResponseDTO saveAeronave(AeronaveRequestDTO request);

}
