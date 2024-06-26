package com.curso.ecommerce.spring_ecommerce.service;

import java.util.List;
import java.util.Optional;

import com.curso.ecommerce.spring_ecommerce.model.Orden;
import com.curso.ecommerce.spring_ecommerce.model.Usuario;

public interface IOrdenService {
    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden);
    String generarNumeroOrden();
    List<Orden> findByUsuario(Usuario usuario);

}
