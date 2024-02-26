package com.eme22.hotelws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.util.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Schema
@Entity
public class Sucursal {
    @Id
    @Column
    @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID id;
    @Column
    private String nombre;
    @Column
    private String direccion;
    @Column
    private String imagen;
    @Column
    private String numeroTelefono;
    @Column
    private Integer pisos;
    @OneToMany(targetEntity = Habitacion.class , mappedBy = "sucursal", fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private Set<Habitacion> habitaciones = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Sucursal sucursal = (Sucursal) o;
        return getId() != null && Objects.equals(getId(), sucursal.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}