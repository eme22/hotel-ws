package com.eme22.hotelws.service;

import com.eme22.hotelws.model.Sucursal;
import com.eme22.hotelws.repository.SucursalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class SucursalService {

    private final SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public Sucursal createSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal updateSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void deleteSucursal(UUID id) {
        sucursalRepository.deleteById(id);
    }

    public Optional<Sucursal> getSucursal(UUID id) {
        return sucursalRepository.findById(id);
    }
    public Page<Sucursal> getSucursales(Pageable pageable) {
        return sucursalRepository.findAll(pageable);
    }

    public Page<Sucursal> getSucursalesByNombreLikeIgnoreCase(String nombre, Pageable pageable) {
        return sucursalRepository.findByNombreLikeIgnoreCase(nombre, pageable);
    }

}
