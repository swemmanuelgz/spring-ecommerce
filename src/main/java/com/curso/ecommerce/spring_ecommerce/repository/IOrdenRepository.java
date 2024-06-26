package com.curso.ecommerce.spring_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.curso.ecommerce.spring_ecommerce.model.Orden;
import com.curso.ecommerce.spring_ecommerce.model.Usuario;

import java.util.List;


@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer> {

    List<Orden> findByUsuario(Usuario usuario);
}
