package com.curso.ecommerce.spring_ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

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
import org.springframework.web.multipart.MultipartFile;

import com.curso.ecommerce.spring_ecommerce.model.Producto;
import com.curso.ecommerce.spring_ecommerce.model.Usuario;
import com.curso.ecommerce.spring_ecommerce.repository.ProductoRepository;
import com.curso.ecommerce.spring_ecommerce.service.ProductoService;
import com.curso.ecommerce.spring_ecommerce.service.ProductoServiceImpl;
import com.curso.ecommerce.spring_ecommerce.service.UploadFileService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UploadFileService uploadFileService;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {

        return "productos/create";
    }

    @PostMapping("/save")
    public String save(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        logger.info("\nEste es el objeto producto{}",producto);
        Usuario user = new Usuario(1, "", "", "", "", "", "", "");
        producto.setUsuario(user);
        //imagen 
        if (producto.getId()==null) { //Esta validacion se hace cuando se crea un producto
            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }else{
            
        }
        productoService.save(producto);
        return "redirect:/productos";
        

    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProduct = productoService.get(id);
        
        producto = optionalProduct.get();
       
        //logger.info("\nProducto Buscado {}", ANSI_BLUE+producto+ANSI_RESET);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }

    @PostMapping("/update")
    public String update(Producto producto ,@RequestParam("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
            p = productoService.get(producto.getId()).get();
        if (file.isEmpty()) {//editamos el producto pero no se cambia su imagen
            
            producto.setImagen(p.getImagen());
        }else{//editamos el producto y se cambia su imagen
           
        //elimina la imagen cuando no es la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            uploadFileService.deleteImage(p.getImagen());
        }

            String nombreImagen = uploadFileService.saveImage(file);
            producto.setImagen(nombreImagen);
        }
        producto.setUsuario(p.getUsuario());
        productoService.update(producto);
        return "redirect:/productos";  
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){

        Producto p = new Producto();
        p = productoService.get(id).get();
        //elimina la imagen cuando no es la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
            uploadFileService.deleteImage(p.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
