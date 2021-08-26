package com.zup.aviacao.repository;

import com.zup.aviacao.model.CheckinVoo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckinRepository extends JpaRepository<CheckinVoo, Long> {
}
