package com.example.jwt.DB;

import com.example.jwt.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, String> {

}
