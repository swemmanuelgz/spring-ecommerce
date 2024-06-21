package com.curso.ecommerce.spring_ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.curso.ecommerce.spring_ecommerce.model.Producto;


public interface ProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public Producto findById(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    public List<Producto> findAll();
}
