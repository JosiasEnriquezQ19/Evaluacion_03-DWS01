package com.idat.eva03.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.idat.eva03.util.EstadoAutor;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "publicaciones")
@Table(name = "autores")
@Builder
public class Autores {
    @Id
    @Column(name = "id_autor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "correo", unique = true, nullable = false, length = 100)
    private String correo;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private EstadoAutor estado = EstadoAutor.ACTIVO;

    @OneToMany(mappedBy = "autor")
    @JsonIgnoreProperties("autor")
    private List<Publicaciones> publicaciones ;

    @PrePersist
    public void prePersist() {
        if (estado == null) {
            estado = EstadoAutor.ACTIVO;
        }
    }
}
