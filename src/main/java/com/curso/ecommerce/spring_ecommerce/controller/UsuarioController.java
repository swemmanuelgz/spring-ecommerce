package com.curso.ecommerce.spring_ecommerce.controller;

import java.util.List;
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

import com.curso.ecommerce.spring_ecommerce.model.Orden;
import com.curso.ecommerce.spring_ecommerce.model.Usuario;
import com.curso.ecommerce.spring_ecommerce.service.IOrdenService;
import com.curso.ecommerce.spring_ecommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrdenService ordenService;

    @GetMapping("/registro")
    public String create(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        logger.info(ProductoController.ANSI_GREEN+"\nUsuario Registrado: {}",usuario+ProductoController.ANSI_RESET);
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @PostMapping("/acceder")
    public String acceder(Usuario usuario ,HttpSession session){
        logger.info(ProductoController.ANSI_GREEN+"\n Username: {}"+usuario+ProductoController.ANSI_RESET);
        Optional <Usuario> user = usuarioService.findByEmail(usuario.getEmail());
        //logger.info(ProductoController.ANSI_GREEN+"Usuario ID de la base de datos: {}",user.get().getId()+ProductoController.ANSI_RESET);

        if (user.isPresent()) {
            session.setAttribute("idusuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")) {
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            //logger.info("Usuario no existe");
        }
        return "redirect:/";
    }

    @GetMapping("/compras")
    public String obtenerComptras(Model model,HttpSession session){
        model.addAttribute("sesion", session.getAttribute("idusuario"));
        Usuario usuario =usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        List<Orden> ordenes = ordenService.findByUsuario(usuario);

        model.addAttribute("ordenes", ordenes);
        return "usuario/compras";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalleCompra(@PathVariable Integer id , HttpSession session,Model model){
        Optional<Orden> orden = ordenService.findById(id);

        model.addAttribute("detalles", orden.get().getDetalle());
        //session
        model.addAttribute("sesion", model.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    @GetMapping("/cerrar")
    public String cerrarSesion(HttpSession session){
        session.removeAttribute("idusuario");
        return "redirect:/";
    }
}
