package com.alura.forohub.controller;

import com.alura.forohub.domain.respuesta.*;
import com.alura.forohub.domain.topico.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {

    @Autowired
    RespuestaRepository respuestaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TopicoRepository topicoRepository;


    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaDevuelta> registrarRespuestaATopico(@RequestBody @Valid DatosRespuestaRecibida datosRespuestaRecibida){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(datosRespuestaRecibida.usuario().getId());
        if(usuarioOptional.isEmpty()){
            throw new RuntimeException("El usuario no fue encontrado");
        }
        Optional<Topico> topicoOptional = topicoRepository.findById(datosRespuestaRecibida.topico().getId());
        if(topicoOptional.isEmpty()){
            throw new RuntimeException("El topico no fue encontrado");
        }
        Respuesta respuesta = new Respuesta(datosRespuestaRecibida);
        respuestaRepository.save(respuesta);
        DatosRespuestaDevuelta datosRespuestaDevuelta = new DatosRespuestaDevuelta(respuesta.getId(), datosRespuestaRecibida.mensaje(), respuesta.getFechaCreacion());

        return ResponseEntity.ok().body(datosRespuestaDevuelta);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<DatosListadoRespuestas>> listarTodasLasRespuestasATopicoId(@PageableDefault(value = 10,
            sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable, @PathVariable Long id){

        Page<Respuesta> respuestasATopico = respuestaRepository.findAllByTopicosId(pageable, id);
        System.out.println("respuestasATopico: " + respuestasATopico);

        Page<DatosListadoRespuestas> listaDeRespuestas = respuestasATopico.map(
                r -> new DatosListadoRespuestas(r.getMensaje(), r.getFechaCreacion(), r.getSolucion())
        );
        return ResponseEntity.ok(listaDeRespuestas);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Optional<Respuesta> existeRespuesta = respuestaRepository.findById(id);

        if(existeRespuesta.isPresent()){
            var respuestaAModificar = existeRespuesta.get();
            if (datosActualizarRespuesta.mensaje() != null) {
                respuestaAModificar.setMensaje(datosActualizarRespuesta.mensaje());
            }
            if(datosActualizarRespuesta.solucion() != null){
                respuestaAModificar.setSolucion(datosActualizarRespuesta.solucion());
            }

            respuestaRepository.save(respuestaAModificar);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarRespuesta(@PathVariable Long id){
        Optional<Respuesta> respuestaParaEliminar = respuestaRepository.findById(id);
        if(respuestaParaEliminar.isPresent()){
            var respuestaAEliminar = respuestaParaEliminar.get();
            respuestaRepository.deleteById(respuestaAEliminar.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    }
