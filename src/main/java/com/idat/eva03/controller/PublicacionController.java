package com.idat.eva03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.idat.eva03.dto.PublicacionesDTO;
import com.idat.eva03.service.AutorService;
import com.idat.eva03.service.PublicacionService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/publicacion")
public class PublicacionController {
    
    @Autowired
    private PublicacionService publicacionService;
    
    @Autowired
    private AutorService autorService;

    // RF7.6 - Listar todas las publicaciones (con estadísticas y filtros)
    @GetMapping
    public String listarPublicaciones(Model model) {
        try {
            model.addAttribute("publicaciones", publicacionService.findAllPublicaciones());
            model.addAttribute("autores", autorService.findAllAutores());
            return "publicacion/publicaciones-list";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "publicacion/publicaciones-list";
        }
    }

    // RF7.5 - Registrar publicaciones asociadas a un autor
    @GetMapping("/nuevo")
    public String mostrarFormularioRegistro(Model model) {
        PublicacionesDTO publicacion = new PublicacionesDTO();
        // Establecer valores por defecto
        publicacion.setFechaModificacion(LocalDateTime.now());
        publicacion.setEstado("BORRADOR");
        // La fechaPublicacion se deja null hasta que se publique
        
        model.addAttribute("publicacion", publicacion);
        model.addAttribute("autores", autorService.findAllAutores());
        return "publicacion/publicaciones-register";
    }

    @PostMapping("/nuevo")
    public String savePublicacion(@Valid PublicacionesDTO publicacionDTO, 
                                BindingResult result, 
                                Model model, 
                                @RequestParam(value = "action", defaultValue = "publish") String action,
                                RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("autores", autorService.findAllAutores());
                return "publicacion/publicaciones-register";
            }
            
            // Establecer estado según acción
            if ("draft".equals(action)) {
                publicacionDTO.setEstado("BORRADOR");
                // Los borradores no tienen fecha de publicación
                publicacionDTO.setFechaPublicacion(null);
            } else {
                publicacionDTO.setEstado("PUBLICADO");
                // Asignar fecha de publicación automáticamente
                publicacionDTO.setFechaPublicacion(LocalDate.now());
            }
            
            // Establecer fechas
            publicacionDTO.setFechaModificacion(LocalDateTime.now());
            
            publicacionService.savePublicacion(publicacionDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Publicación creada correctamente");
            return "redirect:/publicacion";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("publicacion", publicacionDTO);
            model.addAttribute("autores", autorService.findAllAutores());
            return "publicacion/publicaciones-register";
        }
    }

    // RF7.6 - Listar publicaciones por autor
    @GetMapping("/autor/{autorId}")
    public String listarPublicacionesPorAutor(@PathVariable Long autorId, Model model) {
        try {
            var autor = autorService.findById(autorId);
            if (autor == null) {
                model.addAttribute("error", "Autor no encontrado");
                return "redirect:/publicacion";
            }
            model.addAttribute("autor", autor);
            model.addAttribute("publicaciones", publicacionService.findByAutorId(autorId));
            return "publicacion/publicaciones-por-autor";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }

    // RF7.10 - Mostrar detalle de la publicación con su autor
    @GetMapping("/{id}")
    public String mostrarDetallePublicacion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            var publicacion = publicacionService.findById(id);
            if (publicacion == null) {
                redirectAttributes.addFlashAttribute("error", "Publicación no encontrada");
                return "redirect:/publicacion";
            }
            // Obtener datos del autor para mostrar en el detalle
            var autor = autorService.findById(publicacion.getAutorId());
            model.addAttribute("publicacion", publicacion);
            model.addAttribute("autor", autor);
            return "publicacion/publicaciones-detail";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }

    // RF7.8 - Editar el contenido de una publicación
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            var publicacion = publicacionService.findById(id);
            if (publicacion == null) {
                redirectAttributes.addFlashAttribute("error", "Publicación no encontrada");
                return "redirect:/publicacion";
            }
            model.addAttribute("publicacion", publicacion);
            model.addAttribute("autores", autorService.findAllAutores());
            return "publicacion/publicaciones-edit";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }

    @PostMapping("/editar/{id}")
    public String updatePublicacion(@PathVariable Long id, 
                                   @Valid PublicacionesDTO publicacionDTO, 
                                   BindingResult result, 
                                   Model model,
                                   RedirectAttributes redirectAttributes) {
        try {
            if (result.hasErrors()) {
                model.addAttribute("publicacion", publicacionDTO);
                model.addAttribute("autores", autorService.findAllAutores());
                return "publicacion/publicaciones-edit";
            }
            
            // Obtener publicación actual para preservar fechas
            var publicacionActual = publicacionService.findById(id);
            
            // Manejar fechas automáticamente
            if ("PUBLICADO".equals(publicacionDTO.getEstado()) && 
                !"PUBLICADO".equals(publicacionActual.getEstado()) && 
                publicacionActual.getFechaPublicacion() == null) {
                // Si se está publicando por primera vez, asignar fecha
                publicacionDTO.setFechaPublicacion(java.time.LocalDate.now());
            } else {
                // Preservar fecha de publicación existente
                publicacionDTO.setFechaPublicacion(publicacionActual.getFechaPublicacion());
            }
            
            // Actualizar fecha de modificación
            publicacionDTO.setFechaModificacion(LocalDateTime.now());
            
            publicacionService.editarPublicacion(id, publicacionDTO);
            redirectAttributes.addFlashAttribute("mensaje", "Publicación actualizada correctamente");
            return "redirect:/publicacion";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            model.addAttribute("publicacion", publicacionDTO);
            model.addAttribute("autores", autorService.findAllAutores());
            return "publicacion/publicaciones-edit";
        }
    }

    // RF7.7 - Cambiar estado de la publicación
    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstadoPublicacion(@PathVariable Long id, 
                                         @RequestParam("nuevoEstado") String nuevoEstado, 
                                         RedirectAttributes redirectAttributes) {
        try {
            publicacionService.cambiarEstadoPublicacion(id, nuevoEstado);
            redirectAttributes.addFlashAttribute("mensaje", "Estado de la publicación actualizado correctamente");
            return "redirect:/publicacion";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }

    // RF7.9 - Buscar publicaciones por título
    @GetMapping("/buscar")
    public String buscarPublicaciones(@RequestParam(value = "titulo", required = false) String titulo, Model model) {
        try {
            if (titulo != null && !titulo.trim().isEmpty()) {
                model.addAttribute("publicaciones", publicacionService.buscarPorTitulo(titulo));
                model.addAttribute("terminoBusqueda", titulo);
            } else {
                model.addAttribute("publicaciones", publicacionService.findAllPublicaciones());
            }
            return "publicacion/publicacion-buscar";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "publicacion/publicacion-buscar";
        }
    }

    // Buscar publicaciones por estado
    @GetMapping("/estado/{estado}")
    public String listarPublicacionesPorEstado(@PathVariable String estado, Model model) {
        try {
            model.addAttribute("publicaciones", publicacionService.findByEstado(estado));
            model.addAttribute("estadoFiltro", estado);
            return "publicacion/publicaciones-por-estado";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }

    // Eliminar publicación
    @PostMapping("/eliminar/{id}")
    public String eliminarPublicacion(@PathVariable Long id, Model model) {
        try {
            publicacionService.deletePublicacion(id);
            model.addAttribute("mensaje", "Publicación eliminada correctamente");
            return "redirect:/publicacion";
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "redirect:/publicacion";
        }
    }
}