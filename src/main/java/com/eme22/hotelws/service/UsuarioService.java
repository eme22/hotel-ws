package com.eme22.hotelws.service;

import com.eme22.hotelws.model.Usuario;
import com.eme22.hotelws.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void deleteUsuario(UUID id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> getUsuario(UUID id) {
        return usuarioRepository.findById(id);
    }

    public Page<Usuario> getUsuarios(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    public Page<Usuario> getUsuariosByNombreLikeIgnoreCase(String nombre, Pageable pageable) {
        return usuarioRepository.findByNombreLikeIgnoreCase(nombre, pageable);
    }

    public Optional<Usuario> getUsuarioByCorreoElectronico(String correo) {
        return usuarioRepository.findByCorreoElectronico(correo);
    }

    public Page<Usuario> getUsuariosByApellidoLikeIgnoreCase(String apellido, Pageable pageable) {
        return usuarioRepository.findByApellidoLikeIgnoreCase(apellido, pageable);
    }
}
