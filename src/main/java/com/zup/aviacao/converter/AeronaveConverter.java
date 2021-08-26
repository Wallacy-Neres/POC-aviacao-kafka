package com.zup.aviacao.converter;

import com.zup.aviacao.DTO.AeronaveRequestDTO;
import com.zup.aviacao.DTO.AeronaveResponseDTO;
import com.zup.aviacao.DTO.AssentoRequestDTO;
import com.zup.aviacao.enums.StatusAssento;
import com.zup.aviacao.model.Aeronave;
import com.zup.aviacao.model.Assento;
import com.zup.aviacao.solicitacao_cadastro_aeronave.AssentosListaElementosResponse;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveRequest;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveResponse;
import com.zup.aviacao.solicitacao_cadastro_aeronave.SolicitarCadastroAeronaveResponseData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AeronaveConverter {

    public Aeronave aeronavetoEntity(AeronaveRequestDTO request) {

        List<Assento> assentos = request.getAssentos().stream().map(assento -> Assento.builder()
                .assento(assento.getNumeroAssento())
                .statusAssento(StatusAssento.DISPONIVEL)
                .build()).collect(Collectors.toList());

        return Aeronave.builder()
                .marca(request.getMarca())
                .modelo(request.getModelo())
                .assentos(assentos).build();
    }

    public AeronaveRequestDTO avroToDto(SolicitarCadastroAeronaveRequest avro) {
        List<AssentoRequestDTO> assentos = avro.getData().getAssentos().stream().map(assento -> AssentoRequestDTO.builder()
                .numeroAssento(assento.getNumeroAssento().toString())
                .build()).collect(Collectors.toList());

        return AeronaveRequestDTO.builder()
                .marca(avro.getData().getMarca().toString())
                .modelo(avro.getData().getModelo().toString())
                .assentos(assentos).build();
    }

    public AeronaveResponseDTO aeronaveEntityToDto(Aeronave aeronave) {
        return AeronaveResponseDTO.builder()
                .marca(aeronave.getMarca())
                .modelo(aeronave.getModelo())
                .assentos(aeronave.getAssentos()).build();
    }

    public SolicitarCadastroAeronaveResponse dtoResponseToAvro(AeronaveResponseDTO dto) {
        return SolicitarCadastroAeronaveResponse.newBuilder()
                .setData(
                        SolicitarCadastroAeronaveResponseData.newBuilder()
                                .setMarca(dto.getMarca())
                                .setModelo(dto.getModelo())
                                .setAssentosResponse(
                                        dto.getAssentos().stream()
                                                .map(assento -> AssentosListaElementosResponse.newBuilder()
                                                        .setNumeroAssento(assento.getAssento())
                                                        .build())
                                                .collect(Collectors.toList())
                                )
                                .build())
                .build();
    }
}
