package com.agencia.rotasdosol.repository;

import com.agencia.rotasdosol.model.PacoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<PacoteModel, Integer> {
}
