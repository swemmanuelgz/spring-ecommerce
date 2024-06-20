package com.curso.ecommerce.spring_ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @GetMapping
        public String show(){
            System.out.println("\nshow");
            return "productos/show";
        }
}
