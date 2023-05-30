package com.backend.labskyapi.repositories;

import com.backend.labskyapi.models.Passagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagemRepository extends JpaRepository<Passagem, Long> {
    Passagem findByCpf(@Param("cpf") String cpf);
}
