package com.eme22.hotelws.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Schema
@RequiredArgsConstructor
public class TipoHabitacion {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String nombre;
    @Column
    private String descripcion;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] imagen;
    @Column
    private Integer cantidadPersonas;
    @Column
    private Double precioPorHora;
    @Column
    private Double precioPorDia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TipoHabitacion that = (TipoHabitacion) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
