package com.backend.labskyapi.repositories;

import com.backend.labskyapi.models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
    Passageiro findByCpf(@Param("cpf") String cpf);
}
