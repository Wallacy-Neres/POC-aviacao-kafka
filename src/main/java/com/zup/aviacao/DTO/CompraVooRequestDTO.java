package com.zup.aviacao.DTO;

import com.zup.aviacao.config.validator.ValidaSeTemIdValue;
import com.zup.aviacao.model.Voo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompraVooRequestDTO {

    @NotBlank
    private String nomeCliente;

    @NotBlank
    @CPF
    private String cpf;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String sexo;

    @NotBlank
    private String nacionalidade;

    @NotBlank
    private String celular;

    @NotBlank
    private String email;

    private String assento;

    @NotNull
    @ValidaSeTemIdValue(domaiClass = Voo.class, fieldName = "id", message = "Não existe um voo com este ID")
    private Long idVoo;

}
