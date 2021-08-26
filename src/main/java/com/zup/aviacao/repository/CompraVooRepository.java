package com.zup.aviacao.repository;

import com.zup.aviacao.model.CompraVoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraVooRepository extends JpaRepository<CompraVoo, Long> {
}
