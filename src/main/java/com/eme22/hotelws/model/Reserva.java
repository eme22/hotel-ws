package com.eme22.hotelws.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.VarcharJdbcType;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Schema
@Entity
public class Reserva {
    @Id
    @Column
    @GeneratedValue
    @JdbcType(VarcharJdbcType.class)
    private UUID id;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "habitacion_id")
    private Habitacion habitacion;
    @Column
    private OffsetDateTime fechaCheckIn;
    @Column
    private OffsetDateTime fechaCheckOut;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Reserva reserva = (Reserva) o;
        return getId() != null && Objects.equals(getId(), reserva.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}