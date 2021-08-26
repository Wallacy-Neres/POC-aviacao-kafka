package com.zup.aviacao.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinRequestDTO {

    @CPF
    private String cpf;

    @NotNull
    private Long idCompraVoo;

}
