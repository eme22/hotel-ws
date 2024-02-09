package com.eme22.hotelws.repository;

import com.eme22.hotelws.model.TipoHabitacion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, UUID>, PagingAndSortingRepository<TipoHabitacion, UUID> {
    Page<TipoHabitacion> findByNombreLikeIgnoreCase(String nombre, Pageable pageable);
}