package com.eme22.hotelws.controller;

import com.eme22.hotelws.model.Habitacion;
import com.eme22.hotelws.service.HabitacionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    private final HabitacionService habitacionService;

    public HabitacionController(HabitacionService habitacionService) {
        this.habitacionService = habitacionService;
    }

    @GetMapping
    public Page<Habitacion> obtenerTodasLasHabitaciones(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return habitacionService.getHabitaciones(pageable);
    }

    @GetMapping("/buscar-por-sucursal")
    public Page<Habitacion> buscarHabitacionesPorSucursal(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return habitacionService.getHabitacionesBySucursal(UUID.fromString(sucursalId), pageable);
    }

    @GetMapping("/buscar-por-precio-por-hora")
    public Page<Habitacion> buscarHabitacionesPorPrecioPorHora(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "precioPorHora") Double precioPorHora,
            @RequestParam(name = "operator") String operator,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (operator.equals("lessThan"))
            return habitacionService.getHabitacionesByPrecioPorHoraLessThanEqual(UUID.fromString(sucursalId), precioPorHora, pageable);

        else if (operator.equals("greaterThan"))
            return habitacionService.getHabitacionesByPrecioPorHoraGreaterThanEqual(UUID.fromString(sucursalId), precioPorHora, pageable);

        else
            return habitacionService.getHabitacionesByPrecioPorHora(UUID.fromString(sucursalId), precioPorHora, pageable);
    }

    @GetMapping("/buscar-por-precio-por-dia")
    public Page<Habitacion> buscarHabitacionesPorPrecioPorDia(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "precioPorDia") Double precioPorDia,
            @RequestParam(name = "operator") String operator,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        if (operator.equals("lessThan"))
            return habitacionService.getHabitacionesByPrecioPorDiaLessThanEqual(UUID.fromString(sucursalId), precioPorDia, pageable);

        else if (operator.equals("greaterThan"))
            return habitacionService.getHabitacionesByPrecioPorDiaGreaterThanEqual(UUID.fromString(sucursalId), precioPorDia, pageable);

        else
            return habitacionService.getHabitacionesByPrecioPorDia(UUID.fromString(sucursalId), precioPorDia, pageable);
    }

    @GetMapping("/buscar-por-piso")
    public Page<Habitacion> buscarHabitacionesPorPiso(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "piso") Integer piso,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return habitacionService.getHabitacionesByPiso(UUID.fromString(sucursalId), piso, pageable);
    }

    @GetMapping("/buscar-por-numero")
    public Habitacion buscarHabitacionPorNumero(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "numero") Integer numero) {
        return habitacionService.getHabitacionByNumero(UUID.fromString(sucursalId), numero).orElse(null);
    }

    @PostMapping
    public Habitacion crearHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.createHabitacion(habitacion);
    }

    @PutMapping
    public Habitacion actualizarHabitacion(@RequestBody Habitacion habitacion) {
        return habitacionService.updateHabitacion(habitacion);
    }

    @DeleteMapping("/{id}")
    public void eliminarHabitacion(@PathVariable String id) {
        habitacionService.deleteHabitacion(UUID.fromString(id));
    }

    @GetMapping("/{id}")
    public Habitacion obtenerHabitacion(@PathVariable String id) {
        return habitacionService.getHabitacion(UUID.fromString(id)).orElse(null);
    }

    @GetMapping("/buscar-por-fecha-disponible")
    public Page<Habitacion> buscarHabitacionesPorFechaDisponible(
            @RequestParam(name = "sucursalId") String sucursalId,
            @RequestParam(name = "fechaInicio") String fechaInicio,
            @RequestParam(name = "fechaFin") String fechaFin,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return habitacionService.getHabitacionesByFechaDisponible(UUID.fromString(sucursalId), java.time.OffsetDateTime.parse(fechaInicio), java.time.OffsetDateTime.parse(fechaFin),pageable);
    }
}
