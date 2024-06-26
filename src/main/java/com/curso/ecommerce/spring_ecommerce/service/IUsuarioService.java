package com.curso.ecommerce.spring_ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.curso.ecommerce.spring_ecommerce.model.Usuario;

public interface IUsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findById(Integer id);
    Usuario save(Usuario usuario);
    Optional<Usuario> findByEmail(String email);
}
