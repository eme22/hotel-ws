package com.eme22.hotelws.repository;

import com.eme22.hotelws.model.Sucursal;
import com.eme22.hotelws.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, PagingAndSortingRepository<Usuario, UUID> {

    Page<Usuario> findByNombreLikeIgnoreCase(String nombre, Pageable pageable);

    Optional<Usuario> findByCorreoElectronico(String correo);

    Page<Usuario> findByApellidoLikeIgnoreCase(String apellido, Pageable pageable);
}