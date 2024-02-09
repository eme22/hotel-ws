package com.eme22.hotelws.repository;

import com.eme22.hotelws.model.Sucursal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, UUID>, PagingAndSortingRepository<Sucursal, UUID> {

    Page<Sucursal> findByNombreLikeIgnoreCase(String nombre, Pageable pageable);

    Page<Sucursal> findByDireccionLikeIgnoreCase(String direccion, Pageable pageable);

}