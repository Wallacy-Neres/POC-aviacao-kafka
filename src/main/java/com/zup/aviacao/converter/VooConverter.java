package com.zup.aviacao.converter;

import com.zup.aviacao.DTO.VooRequestDTO;
import com.zup.aviacao.DTO.VooResponseDTO;
import com.zup.aviacao.model.Aeronave;
import com.zup.aviacao.model.Voo;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooResponse;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCadastroVooResponseData;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component
public class VooConverter {

    public Voo vooEntity(VooRequestDTO request, EntityManager manager) {
        Aeronave aeronave = manager.find(Aeronave.class, request.getIdAeronave());
        return Voo.builder()
                .dataVoo(request.getDataVoo())
                .destino(request.getDestino())
                .portao(request.getPortao())
                .horaEmbarque(request.getHoraEmbarque())
                .fechaPortao(request.getFechaPortao())
                .dataCriacaoVoo(LocalDateTime.now())
                .tempoDuracao(request.getTempoDuracao())
                .origem(request.getOrigem())
                .valor(request.getValor())
                .aeronave(aeronave)
                .build();
    }

    public VooRequestDTO avroToDto(SolicitarCadastroVooRequest request) {

        return VooRequestDTO.builder()
                .origem(request.getData().getOrigem().toString())
                .destino(request.getData().getDestino().toString())
                .portao(request.getData().getPortao().toString())
                .horaEmbarque(converteStringTOLocalTime(request.getData().getHoraEmbarque().toString()))
                .fechaPortao(converteStringTOLocalTime(request.getData().getFechaPortao().toString()))
                .dataVoo(converteStringTOLocalDate(request.getData().getDataVoo().toString()))
                .tempoDuracao(converteStringTOLocalTime(request.getData().getTempoDuracao().toString()))
                .valor(convertDoubleToBigDecimal(request.getData().getValor().toString()))
                .idAeronave(request.getData().getIdAeronave())
                .build();

    }

    private BigDecimal convertDoubleToBigDecimal(String valor) {
        BigDecimal valorConvertido = new BigDecimal(valor);
        return valorConvertido;
    }

    public LocalTime converteStringTOLocalTime(String hora) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(hora, parser);
        return localTime;
    }

    public LocalDateTime converteStringTOLocalDate(String data) {
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDate = LocalDateTime.parse(data, parser);
        return localDate;
    }

    public VooResponseDTO vooEntityToDto(Voo voo) {
        return VooResponseDTO.builder()
                .origem(voo.getOrigem())
                .destino(voo.getDestino())
                .dataVoo(voo.getDataVoo())
                .tempoDuracao(voo.getTempoDuracao())
                .valor(voo.getValor())
                .build();
    }

    public SolicitarCadastroVooResponse dtoResponseToAvro(VooResponseDTO dto) {
        return SolicitarCadastroVooResponse.newBuilder()
                .setData(
                        SolicitarCadastroVooResponseData.newBuilder()
                                .setOrigem(dto.getOrigem())
                                .setDestino(dto.getDestino())
                                .setDataVoo(dto.getDataVoo().toString())
                                .setTempoDuracao(dto.getTempoDuracao().toString())
                                .setValor(dto.getValor().toString())
                                .build()
                )
                .build();
    }
}
