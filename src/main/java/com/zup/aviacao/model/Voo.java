package com.zup.aviacao.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Voo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVoo;

    private String origem;
    private String destino;
    private String portao;
    private LocalTime horaEmbarque;
    private LocalTime fechaPortao;
    private LocalDateTime dataCriacaoVoo;
    private LocalDateTime dataVoo;
    private LocalTime tempoDuracao;
    private BigDecimal valor;

    @OneToOne
    private Aeronave aeronave;

}
