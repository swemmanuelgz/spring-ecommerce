package com.curso.ecommerce.spring_ecommerce.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String username;
    private String email;
    private String direccion;
    private String telefono;
    private String tipo;
    private String password;
    @OneToMany(mappedBy = "usuario")
    private List<Producto> productos;
    @OneToMany(mappedBy = "usuario")
    private List<Orden> ordenes;
    public Usuario(Integer id, String nombre, String username, String email, String direccion, String telefono,
            String tipo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
    }

    
}
