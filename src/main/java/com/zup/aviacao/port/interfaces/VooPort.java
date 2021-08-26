package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.DTO.VooResponseDTO;

public interface VooPort {
    public VooResponseDTO saveVoo(VooRequestDTO request);
}
