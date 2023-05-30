package com.backend.labskyapi.Repositories;

import com.backend.labskyapi.Models.Assento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssentoRepository extends JpaRepository<Assento, Long> {
}
