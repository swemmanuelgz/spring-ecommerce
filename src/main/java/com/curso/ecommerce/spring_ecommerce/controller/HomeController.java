package com.curso.ecommerce.spring_ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.jdbc.env.internal.LobCreationLogging_.logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.curso.ecommerce.spring_ecommerce.model.DetalleOrden;
import com.curso.ecommerce.spring_ecommerce.model.Orden;
import com.curso.ecommerce.spring_ecommerce.model.Producto;
import com.curso.ecommerce.spring_ecommerce.service.ProductoService;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

        //para almacenar los detalles de la orden
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    //Datos de la orden
    Orden orden = new Orden();

        @GetMapping
        public String home(Model model) {
            
            model.addAttribute("productos", productoService.findAll());

            return "usuario/home";
        }

        @GetMapping("/productohome/{id}")
        public String productoHome(@PathVariable Integer id,Model model){
            log.info("\nProducto Buscado {}", id);
            Producto producto = new Producto();
            Optional<Producto> productOptional = productoService.get(id);
            producto = productOptional.get();
            model.addAttribute("producto",producto);
            return "usuario/productohome";
        }

        @PostMapping("/cart")
        public String addCart(@RequestParam Integer id,@RequestParam Integer cantidad){
            DetalleOrden detalleOrden = new DetalleOrden();
            Producto producto = new Producto();
            double sumaTotal =0;
            Optional<Producto> productOptional = productoService.get(id);
            log.info(ProductoController.ANSI_GREEN+"Producto añadido: {}",productOptional.get());
            log.info(ProductoController.ANSI_GREEN+"\nID: "+id+" Cantidad: "+cantidad+ProductoController.ANSI_RESET);
            return "usuario/carrito";
        }
}
