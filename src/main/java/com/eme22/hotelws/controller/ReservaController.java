package com.eme22.hotelws.controller;

import com.eme22.hotelws.model.Reserva;
import com.eme22.hotelws.service.ReservaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping("/buscar-por-habitacion")
    public Page<Reserva> buscarReservasPorHabitacion(
            @RequestParam(name = "habitacionId") String habitacionId,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reservaService.getReservasByHabitacion(UUID.fromString(habitacionId), pageable);
    }

    @GetMapping("/buscar-por-fecha")
    public Page<Reserva> buscarReservasPorFecha(
            @RequestParam(name = "fechaEntrada") String fechaEntrada,
            @RequestParam(name = "fechaSalida") String fechaSalida,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reservaService.getReservasByFechaEntradaGreaterThanEqualAndFechaSalidaLessThanEqual(
                java.time.OffsetDateTime.parse(fechaEntrada),
                java.time.OffsetDateTime.parse(fechaSalida),
                pageable);
    }

    @GetMapping("/buscar-por-usuario")
    public Page<Reserva> buscarReservasPorUsuario(
            @RequestParam(name = "usuarioId") String usuarioId,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return reservaService.findByUsuarioId(UUID.fromString(usuarioId), pageable);
    }

    @PostMapping
    public Reserva crearReserva(@RequestBody Reserva reserva) {
        return reservaService.createReserva(reserva);
    }

    @PutMapping
    public Reserva actualizarReserva(@RequestBody Reserva reserva) {
        return reservaService.updateReserva(reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminarReserva(@PathVariable String id) {
        reservaService.deleteReserva(UUID.fromString(id));
    }

    @GetMapping("/{id}")
    public Reserva obtenerReserva(@PathVariable String id) {
        return reservaService.getReserva(UUID.fromString(id)).orElse(null);
    }

}
