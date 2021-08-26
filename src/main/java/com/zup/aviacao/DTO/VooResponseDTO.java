package com.zup.aviacao.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zup.aviacao.model.Voo;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class VooResponseDTO {

    private String origem;
    private String destino;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime dataVoo;

    @JsonFormat(pattern = "HH:mm")
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime tempoDuracao;
    private BigDecimal valor;

//    public VooResponseDTO(Voo voo) {
//        this.origem = voo.getOrigem();
//        this.destino = voo.getDestino();
//        this.dataVoo = voo.getDataVoo();
//        this.tempoDuracao = voo.getTempoDuracao();
//        this.valor = voo.getValor();
//    }
//
//    public String getOrigem() {
//        return origem;
//    }
//
//    public String getDestino() {
//        return destino;
//    }
//
//    public LocalDateTime getDataVoo() {
//        return dataVoo;
//    }
//
//    public LocalTime getTempoDuracao() {
//        return tempoDuracao;
//    }
//
//    public BigDecimal getValor() {
//        return valor;
//    }
//
//    public static List<VooResponseDTO> converter(List<Voo> voos) {
//        return voos.stream().map(voo -> VooResponseDTO.builder()
//                        .origem(voo.getOrigem())
//                        .destino(voo.getDestino())
//                        .dataVoo(voo.getDataVoo())
//                        .tempoDuracao(voo.getTempoDuracao())
//                        .valor(voo.getValor())
//                        .build())
//                .collect(Collectors.toList());
//    }
}


