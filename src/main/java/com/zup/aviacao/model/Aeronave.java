package com.zup.aviacao.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Aeronave implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aeronave_assento")
    private List<Assento> assentos;

}
