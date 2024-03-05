package com.eme22.hotelws.service;

import com.eme22.hotelws.model.Habitacion;
import com.eme22.hotelws.model.Reserva;
import com.eme22.hotelws.repository.HabitacionRepository;
import com.eme22.hotelws.repository.ReservaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HabitacionService {

    private final HabitacionRepository habitacionRepository;
    private final ReservaRepository reservaRepository;

    public HabitacionService(HabitacionRepository habitacionRepository, ReservaRepository reservaRepository) {
        this.habitacionRepository = habitacionRepository;
        this.reservaRepository = reservaRepository;
    }

    public Habitacion createHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Habitacion updateHabitacion(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void deleteHabitacion(UUID id) {
        habitacionRepository.deleteById(id);
    }

    public Optional<Habitacion> getHabitacion(UUID id) {
        return habitacionRepository.findById(id);
    }

    public Page<Habitacion> getHabitaciones(Pageable pageable) {
        return habitacionRepository.findAll(pageable);
    }

    public Page<Habitacion> getHabitacionesBySucursal(UUID sucursalId, Pageable pageable) {
        return habitacionRepository.findBySucursalId(sucursalId, pageable);
    }

    public Optional<Habitacion> getHabitacionByNumero(UUID sucursalId, Integer numero) {
        return habitacionRepository.findBySucursal_IdAndNumeroHabitacion(sucursalId, numero);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorHora(UUID sucursalId, Double precioPorHora, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorHora(sucursalId, precioPorHora, pageable);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorHoraLessThanEqual(UUID sucursalId, Double precioPorHora, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorHoraLessThanEqual(sucursalId, precioPorHora, pageable);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorHoraGreaterThanEqual(UUID sucursalId, Double precioPorHora, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorHoraGreaterThanEqual(sucursalId, precioPorHora, pageable);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorDia(UUID sucursalId, Double precioPorDia, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorDia(sucursalId, precioPorDia, pageable);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorDiaLessThanEqual(UUID sucursalId, Double precioPorDia, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorDiaLessThanEqual(sucursalId, precioPorDia, pageable);
    }

    public Page<Habitacion> getHabitacionesByPrecioPorDiaGreaterThanEqual(UUID sucursalId, Double precioPorDia, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndTipoHabitacion_PrecioPorDiaGreaterThanEqual(sucursalId, precioPorDia, pageable);
    }

    public Page<Habitacion> getHabitacionesByPiso(UUID sucursalId, Integer piso, Pageable pageable) {
        return habitacionRepository.findBySucursal_IdAndPiso(sucursalId, piso, pageable);
    }


    public Page<Habitacion> getHabitacionesByFechaDisponible(UUID sucursal, OffsetDateTime fechaInicio, OffsetDateTime fechaFin,Pageable pageable) {

        Pageable pageRequest = createPageRequestUsing(pageable.getPageNumber(), pageable.getPageSize());

        List<UUID> habitacionesEnReserva = reservaRepository.findByFechaCheckInLessThanEqualAndFechaCheckOutGreaterThanEqual(fechaInicio, fechaFin, Pageable.unpaged())
                .getContent().stream()
                .map(reserva -> reserva.getHabitacion().getId())
                .collect(Collectors.toList());

        Page<Habitacion> habitaciones = habitacionRepository.findBySucursalId(sucursal, Pageable.unpaged());

        List<Habitacion> habitacionList = habitaciones.getContent();

        habitacionList.forEach(habitacion -> {
            if (habitacionesEnReserva.contains(habitacion.getId())) {
                habitacion.setEstado(Habitacion.EstadoHabitacion.NO_DISPONIBLE);
            }
        });

        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), habitacionList.size());

        List<Habitacion> pageContent = habitacionList.subList(start, end);

        return new PageImpl<>(pageContent, pageRequest, pageContent.size());

    }

    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }

}
