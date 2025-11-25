package com.idat.eva03.model;

import java.time.LocalDate;
import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idat.eva03.util.EstadoPublicacion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "publicaciones")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "autor")
@Builder
public class Publicaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Lob
    @Column(name = "contenido", nullable = false, columnDefinition = "LONGTEXT")
    private String contenido;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EstadoPublicacion estado = EstadoPublicacion.BORRADOR;

    @ManyToOne
    @JoinColumn(name = "id_autor", nullable = false)
    @JsonIgnoreProperties("publicaciones")
    private Autores autor;

    @Builder.Default
    private LocalDate fechaPublicacion = LocalDate.now();

    @Builder.Default
    private LocalDateTime fechaModificacion = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = EstadoPublicacion.BORRADOR;
        }
    }
}
