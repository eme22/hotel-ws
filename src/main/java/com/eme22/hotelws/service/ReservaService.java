package com.eme22.hotelws.service;

import com.eme22.hotelws.model.Reserva;
import com.eme22.hotelws.repository.HabitacionRepository;
import com.eme22.hotelws.repository.ReservaRepository;
import com.eme22.hotelws.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final HabitacionRepository habitacionRepository;
    private final UsuarioRepository usuarioRepository;

    public ReservaService(ReservaRepository reservaRepository, HabitacionRepository habitacionRepository, UsuarioRepository usuarioRepository) {
        this.reservaRepository = reservaRepository;
        this.habitacionRepository = habitacionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Reserva createReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Reserva updateReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public void deleteReserva(UUID id) {
        reservaRepository.deleteById(id);
    }

    public Optional<Reserva> getReserva(UUID id) {
        return reservaRepository.findById(id);
    }

    public Page<Reserva> findByUsuarioId(UUID usuarioId, Pageable pageable) {
        return reservaRepository.findByUsuarioId(usuarioId, pageable);
    }

    public Page<Reserva> getReservas(Pageable pageable) {
        return reservaRepository.findAll(pageable);
    }

    public Page<Reserva> getReservasByHabitacion(UUID habitacionId, Pageable pageable) {
        return reservaRepository.findByHabitacionId(habitacionId, pageable);
    }

    public Page<Reserva> getReservasByFechaEntradaGreaterThanEqualAndFechaSalidaLessThanEqual(OffsetDateTime fechaEntrada, OffsetDateTime fechaSalida, Pageable pageable) {
        return reservaRepository.findByFechaCheckInGreaterThanEqualAndFechaCheckOutLessThanEqual(fechaEntrada, fechaSalida, pageable);
    }

}
