package com.zup.aviacao.converter;

import com.zup.aviacao.DTO.CompraVooRequestDTO;
import com.zup.aviacao.DTO.CompraVooResponseDTO;
import com.zup.aviacao.model.CompraVoo;
import com.zup.aviacao.model.Voo;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooResponseData;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooResponse;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCompraVooResponseData;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CompraVooConverter {

    public CompraVoo compraVooEntity(CompraVooRequestDTO request, EntityManager manager){

        Voo voo = manager.find(Voo.class, request.getIdVoo());
        return CompraVoo.builder()
                .celular(request.getCelular())
                .cpf(request.getCpf())
                .dataNascimento(request.getDataNascimento())
                .email(request.getEmail())
                .assento(request.getAssento())
                .nacionalidade(request.getNacionalidade())
                .nomeCliente(request.getNomeCliente())
                .sexo(request.getSexo())
                .voo(voo)
                .build();
    }

    public CompraVooRequestDTO avroToDto(SolicitarCompraVooRequest avro) {
        return CompraVooRequestDTO.builder()
                .nomeCliente(avro.getData().getNomeCliente().toString())
                .cpf(avro.getData().getCpf().toString())
                .dataNascimento(converteStringToLocalDate(avro.getData().getDataNascimento().toString()))
                .sexo(avro.getData().getSexo().toString())
                .nacionalidade(avro.getData().getNacionalidade().toString())
                .celular(avro.getData().getCelular().toString())
                .email(avro.getData().getEmail().toString())
                .assento(avro.getData().getAssento().toString())
                .idVoo(avro.getData().getIdVoo())
                .build();
    }

    public LocalDate converteStringToLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(data, parser);
        return localDate;
    }

    public CompraVooResponseDTO compraVooEntityToDto(CompraVoo compraVoo) {
        return CompraVooResponseDTO.builder()
                .nomeCliente(compraVoo.getNomeCliente())
                .cpf(compraVoo.getCpf())
                .idVoo(compraVoo.getVoo().getIdVoo())
                .build();
    }

    public SolicitarCompraVooResponse dtoResponseToAvro(CompraVooResponseDTO responseDTO) {
        return SolicitarCompraVooResponse.newBuilder()
                .setData(
                        SolicitarCompraVooResponseData.newBuilder()
                                .setNomeCliente(responseDTO.getNomeCliente())
                                .setCpf(responseDTO.getCpf())
                                .setIdVoo(responseDTO.getIdVoo())
                                .build()
                ).build();
    }
}
