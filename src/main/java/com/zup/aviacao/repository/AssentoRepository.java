package com.zup.aviacao.repository;

import com.zup.aviacao.model.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {

    @Query(value = "SELECT * FROM ASSENTO u WHERE u.ASSENTO = :numero and u.AERONAVE_ASSENTO = :idAeronave", nativeQuery = true)
    Optional<Assento> findByAssentos(@Param("numero") String numero, @Param("idAeronave") Long id);

//    Optional<Assento> findByAssentosAndAeronave(@Param("numero") String numero, @Param("idAeronave") Long id);
}
