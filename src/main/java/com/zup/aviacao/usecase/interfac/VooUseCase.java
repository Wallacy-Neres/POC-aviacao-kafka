package com.zup.aviacao.usecase.interfac;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.VooRequestDTO;

public interface VooUseCase {
    void execute(VooRequestDTO request);
}
