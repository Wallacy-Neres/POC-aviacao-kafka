package com.zup.aviacao.DTO;

import com.zup.aviacao.model.Assento;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class AeronaveResponseDTO {


    private String marca;

    private String modelo;

    private List<Assento> assentos;

}
