package com.zup.aviacao.port.interfaces;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.DTO.CompraVooResponseDTO;

public interface CompraVooPort {

    CompraVooResponseDTO saveCompraVoo(CompraVooRequestDTO request);
}
