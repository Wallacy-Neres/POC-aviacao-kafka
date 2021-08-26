package com.zup.aviacao.repository;

import com.zup.aviacao.model.Aeronave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AeronaveRepository extends JpaRepository<Aeronave, Long> {


}
