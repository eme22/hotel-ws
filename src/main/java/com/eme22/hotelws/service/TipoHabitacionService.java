package com.eme22.hotelws.service;

import com.eme22.hotelws.model.TipoHabitacion;
import com.eme22.hotelws.repository.TipoHabitacionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TipoHabitacionService {

    private final TipoHabitacionRepository tipoHabitacionRepository;

    public TipoHabitacionService(TipoHabitacionRepository tipoHabitacionRepository) {
        this.tipoHabitacionRepository = tipoHabitacionRepository;
    }

    public TipoHabitacion createTipoHabitacion(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    public TipoHabitacion updateTipoHabitacion(TipoHabitacion tipoHabitacion) {
        return tipoHabitacionRepository.save(tipoHabitacion);
    }

    public void deleteTipoHabitacion(UUID id) {
        tipoHabitacionRepository.deleteById(id);
    }

    public Optional<TipoHabitacion> getTipoHabitacion(UUID id) {
        return tipoHabitacionRepository.findById(id);
    }

    public Page<TipoHabitacion> getTipoHabitaciones(Pageable pageable) {
        return tipoHabitacionRepository.findAll(pageable);
    }

    public Page<TipoHabitacion> getTiposHabitacionByNombreLikeIgnoreCase(String nombre, Pageable pageable) {
        return tipoHabitacionRepository.findByNombreLikeIgnoreCase(nombre, pageable);
    }
}
