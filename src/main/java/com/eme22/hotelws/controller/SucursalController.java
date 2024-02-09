package com.eme22.hotelws.controller;

import com.eme22.hotelws.model.Sucursal;
import com.eme22.hotelws.service.SucursalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    private final SucursalService sucursalService;

    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    @GetMapping("/buscar-por-nombre")
    public Page<Sucursal> buscarSucursalesPorNombre(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return sucursalService.getSucursalesByNombreLikeIgnoreCase(nombre, pageable);
    }

    @PostMapping
    public Sucursal crearSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.createSucursal(sucursal);
    }

    @PutMapping
    public Sucursal actualizarSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.updateSucursal(sucursal);
    }

    @DeleteMapping("/{id}")
    public void eliminarSucursal(@PathVariable String id) {
        sucursalService.deleteSucursal(UUID.fromString(id));
    }

    @GetMapping("/{id}")
    public Sucursal obtenerSucursal(@PathVariable String id) {
        return sucursalService.getSucursal(UUID.fromString(id)).orElse(null);
    }

    @GetMapping
    public Page<Sucursal> obtenerTodasLasSucursales(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return sucursalService.getSucursales(pageable);
    }

    //@PostMapping("/upload/{id}")
    @RequestMapping(method = RequestMethod.POST, value = "/upload/{id}",
            consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String id) {

        Sucursal sucursal = sucursalService.getSucursal(UUID.fromString(id)).orElse(null);

        if (sucursal == null) {
            System.out.println("Sucursal no encontrada");
            return;
        }

        try {

            byte[] image = file.getInputStream().readAllBytes();

            sucursal.setImagen(image);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        sucursalService.updateSucursal(sucursal);

    }

}
