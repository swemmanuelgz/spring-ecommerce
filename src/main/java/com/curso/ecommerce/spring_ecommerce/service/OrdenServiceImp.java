package com.curso.ecommerce.spring_ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.spring_ecommerce.model.Orden;
import com.curso.ecommerce.spring_ecommerce.model.Usuario;
import com.curso.ecommerce.spring_ecommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImp implements IOrdenService {

    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    public String generarNumeroOrden(){
        int numero =0;
        String numeroConcatenado="";

        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<>();

        ordenes.stream().forEach(o -> numeros.add(Integer.parseInt(o.getNumero())));

        if (ordenes.isEmpty()) {
            numero=1;
        }else{
            numero = numeros.stream().max(Integer::compare).get();
            numero++;
        }
         // Formatear el número con ceros a la izquierda
         numeroConcatenado = String.format("%010d", numero);

        return numeroConcatenado;
    }

    @Override
    public List<Orden> findByUsuario(Usuario usuario) {
        return ordenRepository.findByUsuario(usuario);
    }

    @Override
    public Optional<Orden> findById(Integer id) {
        return ordenRepository.findById(id);
    }

}
