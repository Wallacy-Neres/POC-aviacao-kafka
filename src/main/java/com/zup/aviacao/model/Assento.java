package com.zup.aviacao.model;

import com.zup.aviacao.enums.StatusAssento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String assento;

    @Enumerated(EnumType.STRING)
    private StatusAssento statusAssento;

}
