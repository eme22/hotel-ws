package com.eme22.hotelws.service;

import com.eme22.hotelws.model.Sucursal;
import com.eme22.hotelws.repository.SucursalRepository;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public Optional<Integer> getPisos(UUID id) {
        return sucursalRepository.findById(id).map(Sucursal::getPisos);
    }

    public void uploadImageToAssets(UUID id, MultipartFile file) throws IOException {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if (sucursal.isPresent()) {
            String fileName = UUID.randomUUID() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
            String path = "/app/static/" + fileName;
            File newFile = new File(path);
            FileUtils.writeByteArrayToFile(newFile, file.getBytes());
            sucursal.get().setImagen(fileName);
            sucursalRepository.save(sucursal.get());
        }
    }

}
