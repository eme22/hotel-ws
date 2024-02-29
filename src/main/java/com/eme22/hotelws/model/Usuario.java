package com.eme22.hotelws.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.math.BigInteger;
import java.util.*;

@Getter
@Setter
@ToString
@Schema
@RequiredArgsConstructor
@Entity
public class Usuario {
    @Id
    @Column
    @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID id;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column(unique = true)
    private BigInteger dni;
    @Column(unique = true)
    private String correoElectronico;
    @Column(nullable = false)
    private String password;
    @Column
    private String numeroTelefono;
    @Column
    private Integer tipoUsuario;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, , cascade = CascadeType.ALL)
    @ToString.Exclude
    private Set<Reserva> reservas = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Usuario usuario = (Usuario) o;
        return getId() != null && Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}