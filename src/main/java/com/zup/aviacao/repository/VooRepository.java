package com.zup.aviacao.repository;

import com.zup.aviacao.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {

}
