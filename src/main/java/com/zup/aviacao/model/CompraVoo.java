package com.zup.aviacao.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CompraVoo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private String cpf;
    private LocalDate dataNascimento;
    private String sexo;
    private String nacionalidade;
    private String celular;
    private String email;
    private String assento;

    @OneToOne
    private Voo voo;

}
