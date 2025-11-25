package com.idat.eva03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;

import com.idat.eva03.dto.AutoresDTO;
import com.idat.eva03.service.AutorService;


@Controller
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorService autorService;

    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("autor", new AutoresDTO());
        return "autor/autores-register";
    }

    @PostMapping("/save")
    public String saveAutor(@Valid AutoresDTO autorDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                return "autor/autores-register";
            }
            autorService.saveAutor(autorDTO);
            model.addAttribute("mensaje", "Autor guardado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("autor", autorDTO);
            return "autor/autores-register";
        }
    }

    @GetMapping("/autor/{idAutor}")
    public String getAutorById(@PathVariable Long idAutor, Model model) {
        try {
            AutoresDTO autor = autorService.findById(idAutor);
            model.addAttribute("autor", autor);
            return "autor-detalle";
        } catch (RuntimeException e) {
            // Si el autor no existe, Spring Boot manejará automáticamente el 404
            model.addAttribute("error", "Autor no encontrado con ID: " + idAutor);
            return "error";
        }
    }

    @PutMapping("/autor/update/{idAutor}/{nuevoEstado}")
    public String updateAutorEstado(@PathVariable Long idAutor, @PathVariable String nuevoEstado, Model model) {
        try {
            autorService.cambiarEstado(idAutor, nuevoEstado);
            model.addAttribute("mensaje", "Estado del autor actualizado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateAutor(@PathVariable Long id, @Valid AutoresDTO autorDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("autor", autorDTO);
                return "autor/autores-edit";
            }
            autorService.updateAutor(id, autorDTO);
            model.addAttribute("mensaje", "Autor actualizado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("autor", autorDTO);
            return "autor/autores-edit";
        }
    }

    @PutMapping("/autor/update/{id}")
    public String updateAutorPut(@PathVariable Long id, @Valid AutoresDTO autorDTO, BindingResult result, Model model) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("autor", autorDTO);
                return "autor/autores-edit";
            }
            autorService.updateAutor(id, autorDTO);
            model.addAttribute("mensaje", "Autor actualizado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping
    public String listarAutores(Model model) {
        try {
            model.addAttribute("autores", autorService.findAllAutores());
            return "autor/autores-list";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "autor/autores-list";
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        try {
            AutoresDTO autor = autorService.findById(id);
            model.addAttribute("autor", autor);
            return "autor/autores-edit";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Autor no encontrado con ID: " + id);
            return "error";
        }
    }

    @GetMapping("/publicaciones/{id}")
    public String verPublicacionesAutor(@PathVariable Long id, Model model) {
        try {
            AutoresDTO autor = autorService.findById(id);
            model.addAttribute("autor", autor);
            // Si necesitas mostrar publicaciones, deberías agregarlas al DTO o hacer otra consulta
            // model.addAttribute("publicaciones", autor.getPublicaciones());
            return "autor/autor-publicaciones";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Autor no encontrado con ID: " + id);
            return "error";
        }
    }

    @PostMapping("/inactivar/{id}")
    public String inactivarAutor(@PathVariable Long id, Model model) {
        try {
            AutoresDTO autor = autorService.findById(id);
            if (autor == null) {
                model.addAttribute("error", "Autor no encontrado");
                return "redirect:/autor";
            }
            autor.setEstado("INACTIVO");
            autorService.updateAutor(id, autor);
            model.addAttribute("mensaje", "Autor inactivado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/autor";
        }
    }

    @PostMapping("/activar/{id}")
    public String activarAutor(@PathVariable Long id, Model model) {
        try {
            AutoresDTO autor = autorService.findById(id);
            if (autor == null) {
                model.addAttribute("error", "Autor no encontrado");
                return "redirect:/autor";
            }
            autor.setEstado("ACTIVO");
            autorService.updateAutor(id, autor);
            model.addAttribute("mensaje", "Autor activado correctamente");
            return "redirect:/autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/autor";
        }
    }
}
