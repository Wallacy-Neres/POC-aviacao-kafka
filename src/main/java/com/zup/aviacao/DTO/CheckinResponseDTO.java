package com.zup.aviacao.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.aviacao.model.CheckinVoo;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CheckinResponseDTO {

    private String nome;
    private String assento;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime horaEmbarque;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime fechaPortao;
    private String portao;
    private String origem;
    private String destino;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime dataVoo;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tempoDuracao;

    public CheckinResponseDTO(CheckinVoo checkinVoo){
        this.nome = checkinVoo.getIdCompraVoo().getNomeCliente();
        this.assento = checkinVoo.getIdCompraVoo().getAssento();
        this.horaEmbarque = checkinVoo.getIdCompraVoo().getVoo().getHoraEmbarque();
        this.fechaPortao = checkinVoo.getIdCompraVoo().getVoo().getFechaPortao();
        this.portao = checkinVoo.getIdCompraVoo().getVoo().getPortao();
        this.origem = checkinVoo.getIdCompraVoo().getVoo().getOrigem();
        this.destino = checkinVoo.getIdCompraVoo().getVoo().getDestino();
        this.dataVoo = checkinVoo.getIdCompraVoo().getVoo().getDataVoo();
        this.tempoDuracao = checkinVoo.getIdCompraVoo().getVoo().getTempoDuracao();
    }
}
