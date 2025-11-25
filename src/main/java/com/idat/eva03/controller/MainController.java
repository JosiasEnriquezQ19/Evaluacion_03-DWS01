package com.idat.eva03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        return "redirect:/autor";
    }

    // Redirección para la ruta antigua de búsqueda
    @GetMapping("/busqueda")
    public String redirectToBusqueda() {
        return "redirect:/publicacion/buscar";
    }

    // Endpoint para reportes que muestra error (funcionalidad no implementada)
    @GetMapping("/reportes")
    public String reportes() {
        throw new RuntimeException("La funcionalidad de reportes aún no está implementada");
    }
}