package com.backend.labskyapi.repositories;

import com.backend.labskyapi.models.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {
    Assento findAssentoByNome(@Param("nome") String nome);
}
