package com.backend.labskyapi.Repositories;

import com.backend.labskyapi.Models.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {
}
