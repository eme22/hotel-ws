package com.eme22.hotelws.repository;

import com.eme22.hotelws.model.Habitacion;
import com.eme22.hotelws.model.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.UUID;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, UUID>, PagingAndSortingRepository<Reserva, UUID> {
    Page<Reserva> findByHabitacionId(UUID habitacionId, Pageable pageable);

    Page<Reserva> findByUsuarioId(UUID usuarioId, Pageable pageable);

    Page<Reserva> findByFechaCheckInGreaterThanEqualAndFechaCheckOutLessThanEqual(OffsetDateTime fechaEntrada, OffsetDateTime fechaSalida, Pageable pageable);

    Page<Reserva> findByFechaCheckInLessThanEqualAndFechaCheckOutGreaterThanEqual(OffsetDateTime fechaSalida, OffsetDateTime fechaEntrada, Pageable pageable);


}