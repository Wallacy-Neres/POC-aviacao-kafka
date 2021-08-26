package com.zup.aviacao.usecase.impl;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.port.impl.CompraVooSavePort;
import com.zup.aviacao.port.interfaces.CompraVooPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompraVooUseCase implements com.zup.aviacao.usecase.interfac.CompraVooUseCase {

    private final CompraVooSavePort compraVooSavePort;

    @Override
    public void execute(CompraVooRequestDTO request) {
        CompraVooResponseDTO responseDTO = compraVooSavePort.saveCompraVoo(request);
    }
}
