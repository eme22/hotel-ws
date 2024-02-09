package com.eme22.hotelws.controller;

import com.eme22.hotelws.model.Sucursal;
import com.eme22.hotelws.model.TipoHabitacion;
import com.eme22.hotelws.service.TipoHabitacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/tipos-habitacion")
public class TipoHabitacionController {

    private final TipoHabitacionService tipoHabitacionService;

    public TipoHabitacionController(TipoHabitacionService tipoHabitacionService) {
        this.tipoHabitacionService = tipoHabitacionService;
    }

    @GetMapping("/buscar-por-nombre")
    public Page<TipoHabitacion> buscarTiposHabitacionPorNombre(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipoHabitacionService.getTiposHabitacionByNombreLikeIgnoreCase(nombre, pageable);
    }

    @GetMapping
    public Page<TipoHabitacion> obtenerTodosLosTiposHabitacion(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return tipoHabitacionService.getTipoHabitaciones(pageable);
    }

    @GetMapping("/{id}")
    public TipoHabitacion obtenerTipoHabitacion(@RequestParam String id) {
        return tipoHabitacionService.getTipoHabitacion(UUID.fromString(id)).orElse(null);
    }

    @PostMapping
    public TipoHabitacion crearTipoHabitacion(@RequestBody TipoHabitacion tipoHabitacion) {
        return tipoHabitacionService.createTipoHabitacion(tipoHabitacion);
    }

    @PutMapping
    public TipoHabitacion actualizarTipoHabitacion(@RequestBody TipoHabitacion tipoHabitacion) {
        return tipoHabitacionService.updateTipoHabitacion(tipoHabitacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarTipoHabitacion(@PathVariable String id) {
        tipoHabitacionService.deleteTipoHabitacion(UUID.fromString(id));
    }
    @RequestMapping(method = RequestMethod.POST, value = "/upload/{id}",
            consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public void cargarImagen(@PathVariable String id, @RequestParam("file") MultipartFile file) {

        try {
            tipoHabitacionService.uploadImageToAssets(UUID.fromString(id), file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
