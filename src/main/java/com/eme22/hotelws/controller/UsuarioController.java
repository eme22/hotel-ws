package com.eme22.hotelws.controller;

import com.eme22.hotelws.model.Usuario;
import com.eme22.hotelws.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/buscar-por-nombre")
    public Page<Usuario> buscarUsuariosPorNombre(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return usuarioService.getUsuariosByNombreLikeIgnoreCase(nombre, pageable);
    }

    @GetMapping("/buscar-por-apellido")
    public Page<Usuario> buscarUsuariosPorApellido(
            @RequestParam(name = "apellido") String apellido,
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return usuarioService.getUsuariosByApellidoLikeIgnoreCase(apellido, pageable);
    }

    @GetMapping("/buscar-por-email/{email}")
    public Usuario buscarUsuarioPorEmail(@RequestParam String email) {
        return usuarioService.getUsuarioByCorreoElectronico(email).orElse(null);
    }

    @GetMapping
    public Page<Usuario> obtenerTodosLosUsuarios(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return usuarioService.getUsuarios(pageable);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.createUsuario(usuario);
    }

    @PutMapping
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        usuarioService.deleteUsuario(java.util.UUID.fromString(id));
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable String id) {
        return usuarioService.getUsuario(java.util.UUID.fromString(id)).orElse(null);
    }

}
