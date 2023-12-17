package com.agencia.rotasdosol.repository;

import com.agencia.rotasdosol.model.ReservaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaModel, Integer> {
    List<ReservaModel> findByClienteIdUsuario(Integer idCliente);
}
