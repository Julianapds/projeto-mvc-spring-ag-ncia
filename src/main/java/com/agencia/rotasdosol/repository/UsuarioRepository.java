package com.agencia.rotasdosol.repository;

import com.agencia.rotasdosol.model.usuario.TipoUsuarioEnum;
import com.agencia.rotasdosol.model.usuario.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    Optional<UsuarioModel> findByEmail(String email);

    List<UsuarioModel> findAllByTipoUsuario(TipoUsuarioEnum tipoUsuarioEnum);
}
