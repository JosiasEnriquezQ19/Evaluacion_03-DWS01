package com.idat.eva03.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Valid
@Builder
public class PublicacionesDTO {
    private long id;

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    private String estado;

    private LocalDate fechaPublicacion;

    private LocalDateTime fechaModificacion;

    // Relación con autor (solo id para operaciones de guardado/edición)
    private Long autorId;
}
