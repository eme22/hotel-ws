package com.eme22.hotelws.repository;

import com.eme22.hotelws.model.Habitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, UUID>, PagingAndSortingRepository<Habitacion, UUID> {

    Page<Habitacion> findBySucursalId(UUID sucursalId, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorHora(UUID id, Double precioPorHora, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorHoraLessThanEqual(UUID id, Double precioPorHora, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorHoraGreaterThanEqual(UUID id, Double precioPorHora, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorDia(UUID id, Double precioPorDia, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorDiaLessThanEqual(UUID id, Double precioPorDia, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndTipoHabitacion_PrecioPorDiaGreaterThanEqual(UUID id, Double precioPorDia, Pageable pageable);

    Page<Habitacion> findBySucursal_IdAndPiso(UUID id, Integer piso, Pageable pageable);





}