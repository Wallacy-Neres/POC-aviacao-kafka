package com.zup.aviacao.DTO;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompraVooResponseDTO {

    private String nomeCliente;
    private String cpf;
    private Long idVoo;
}
