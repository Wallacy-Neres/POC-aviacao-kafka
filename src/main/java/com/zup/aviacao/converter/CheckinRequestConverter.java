package com.zup.aviacao.converter;

import com.zup.aviacao.DTO.CheckinRequestDTO;
import com.zup.aviacao.model.CheckinVoo;
import com.zup.aviacao.model.CompraVoo;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
public class CheckinRequestConverter {

    public CheckinVoo checkinVooEntity(CheckinRequestDTO request, EntityManager manager){

        CompraVoo compraVoo = manager.find(CompraVoo.class, request.getIdCompraVoo());

        return CheckinVoo.builder()
                .cpf(request.getCpf())
                .dataCheckin(LocalDateTime.now())
                .idCompraVoo(compraVoo)
                .build();
    }

}
