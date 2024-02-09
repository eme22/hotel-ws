package com.eme22.hotelws.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.Type;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Schema
@Entity
public class Habitacion {

    @Id
    @Column
    @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID id;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "sucursal_id")
    private Sucursal sucursal;
    @Column
    private Integer numeroHabitacion;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "tipo_habitacion_id")
    private TipoHabitacion tipoHabitacion;
    @Column
    private String estado;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Habitacion that = (Habitacion) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}