package com.alura.forohub.controller;

import com.alura.forohub.domain.usuario.DatosActualizarUsuario;
import com.alura.forohub.domain.usuario.DatosListadoUsuarios;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<Usuario> crearUsuario(@RequestBody @Valid Usuario usuario){
        Usuario nuevoUsuario = new Usuario(usuario);
        nuevoUsuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        var usuarioCreado = usuarioRepository.save(nuevoUsuario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(usuario.getId())
                .toUri();
        return ResponseEntity.created(uri).body(usuarioCreado);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> listarTodosLosUsuarios(@PageableDefault(value = 10,
            sort = "nombre", direction = Sort.Direction.ASC) Pageable pageable){

        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);

        Page<DatosListadoUsuarios> listadoUsuarios = usuarios.map(u -> new DatosListadoUsuarios(u.getNombre(),
                u.getCorreo(), u.getPerfil()));

        return ResponseEntity.ok(listadoUsuarios);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarDatosUsuario(@PathVariable Long id, @RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        var existeUsuario = usuarioRepository.findById(id);
        if (existeUsuario.isPresent()){
            var usuarioModificado = existeUsuario.get();
            if(datosActualizarUsuario.nombre() != null){
                usuarioModificado.setNombre(datosActualizarUsuario.nombre());
            }
            if(datosActualizarUsuario.correo() != null){
                usuarioModificado.setCorreo(datosActualizarUsuario.correo());
            }
            if (datosActualizarUsuario.password() != null){
                usuarioModificado.setPassword(passwordEncoder.encode(datosActualizarUsuario.password()));
            }
            usuarioRepository.save(usuarioModificado);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarUsuario(@PathVariable Long id) {
        var existeUsuario = usuarioRepository.findById(id);
        if(existeUsuario.isPresent()){
            var usuarioAEliminar = existeUsuario.get();
            usuarioRepository.deleteById(usuarioAEliminar.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    }
