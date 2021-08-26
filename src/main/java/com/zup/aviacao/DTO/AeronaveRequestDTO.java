package com.zup.aviacao.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AeronaveRequestDTO {

    @NotBlank
    private String marca;

    @NotBlank
    private String modelo;

    private List<AssentoRequestDTO> assentos;

}
