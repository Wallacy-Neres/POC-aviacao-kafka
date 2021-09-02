package com.zup.aviacao.converter;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.DTO.CheckinResponseDTO;
import com.zup.aviacao.model.CheckinVoo;
import com.zup.aviacao.model.CompraVoo;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinRequest;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinResponse;
import com.zup.aviacao.solicitacao_cadastro_voo.SolicitarCheckinResponseData;
import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
public class CheckinConverter {

    public CheckinVoo checkinVooEntity(CheckinRequestDTO request, EntityManager manager){

        CompraVoo compraVoo = manager.find(CompraVoo.class, request.getIdCompraVoo());

        return CheckinVoo.builder()
                .cpf(request.getCpf())
                .dataCheckin(LocalDateTime.now())
                .idCompraVoo(compraVoo)
                .build();
    }

    public CheckinRequestDTO avroToDto(SolicitarCheckinRequest avro) {
        return CheckinRequestDTO.builder()
                .cpf(avro.getData().getCpf().toString())
                .idCompraVoo(avro.getData().getIdVoo())
                .build();
    }

    public CheckinResponseDTO checkinEntityToDto(CheckinVoo checkinVoo) {
        return CheckinResponseDTO.builder()
                .nome(checkinVoo.getIdCompraVoo().getNomeCliente())
                .assento(checkinVoo.getIdCompraVoo().getAssento())
                .horaEmbarque(checkinVoo.getIdCompraVoo().getVoo().getHoraEmbarque())
                .fechaPortao(checkinVoo.getIdCompraVoo().getVoo().getFechaPortao())
                .portao(checkinVoo.getIdCompraVoo().getVoo().getPortao())
                .origem(checkinVoo.getIdCompraVoo().getVoo().getOrigem())
                .destino(checkinVoo.getIdCompraVoo().getVoo().getDestino())
                .dataVoo(checkinVoo.getIdCompraVoo().getVoo().getDataVoo())
                .tempoDuracao(checkinVoo.getIdCompraVoo().getVoo().getTempoDuracao())
                .build();
    }

    public SolicitarCheckinResponse dtoToAvro(CheckinResponseDTO responseDTO) {
        return SolicitarCheckinResponse.newBuilder()
                .setData(
                        SolicitarCheckinResponseData.newBuilder()
                                .setNome(responseDTO.getNome())
                                .setOrigem(responseDTO.getOrigem())
                                .setDestino(responseDTO.getDestino())
                                .setAssento(responseDTO.getAssento())
                                .setDataVoo(responseDTO.getDataVoo().toString())
                                .setPortao(responseDTO.getPortao().toString())
                                .setFechaPortao(responseDTO.getPortao().toString())
                                .setHoraEmbarque(responseDTO.getHoraEmbarque().toString())
                                .setTempoDuracao(responseDTO.getTempoDuracao().toString())
                                .build()
                )
                .build();
    }
}
