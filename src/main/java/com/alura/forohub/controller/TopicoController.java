package com.alura.forohub.controller;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.topico.*;
import com.alura.forohub.domain.usuario.AutorDelTopico;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosEnvioTopico datosEnvioTopico, UriComponentsBuilder uriComponentsBuilder){
        System.out.println(datosEnvioTopico);

        Topico topicoCreado =(new Topico(datosEnvioTopico));
        topicoRepository.save(topicoCreado);

        LocalDateTime fechaCreacion = LocalDateTime.now();
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topicoCreado.getId(), topicoCreado.getTitulo(), topicoCreado.getMensaje(), fechaCreacion);
        URI url = uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topicoCreado.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopicos>> listarTodosLosTopicos(@PageableDefault(value = 10,
            sort = "fechaCreacion", direction = Sort.Direction.ASC)Pageable pageable){

        Page<Topico> topicos = topicoRepository.findAll(pageable);

        Page<DatosListadoTopicos> respuesta = topicos.map(t -> new DatosListadoTopicos(
                t.getTitulo(), t.getMensaje(),
                t.getFechaCreacion(), t.isStatus(),
                new AutorDelTopico(t.getUsuario()))
        );

        return  ResponseEntity.ok(respuesta);
    }

    @GetMapping("/autor/{id}")
    public ResponseEntity<Page<DatosListadoTopicos>> listarTodosLosTopicosPorUnUsuario(@PageableDefault(value = 10,
            sort = "fechaCreacion", direction = Sort.Direction.ASC)Pageable pageable, @PathVariable Long id){
        Usuario autor = usuarioRepository.getReferenceById(id);

        Page<Topico> topicos = topicoRepository.findAllporAutor(pageable, autor.getId());

        Page<DatosListadoTopicos> respuesta = topicos.map(t -> new DatosListadoTopicos(
                t.getTitulo(), t.getMensaje(),
                t.getFechaCreacion(), t.isStatus(),
                new AutorDelTopico(t.getUsuario()))
        );

        return  ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopicos> listarTopicoPorId(@PathVariable Long id){
        var existeTopico = topicoRepository.findById(id);
        if (existeTopico.isEmpty()){

            throw new RuntimeException("no existe topico que coincida con el id");
        }
        Topico topicoEncontrado = existeTopico.get();
        DatosListadoTopicos topicoPorId = new DatosListadoTopicos(topicoEncontrado.getTitulo(), topicoEncontrado.getMensaje(),
                topicoEncontrado.getFechaCreacion(), topicoEncontrado.isStatus(), new AutorDelTopico(topicoEncontrado.getUsuario()));

        return ResponseEntity.ok(topicoPorId);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Optional<Topico> existeTopico = topicoRepository.findById(id);
        if(existeTopico.isPresent()){
            var topicoAModificar = existeTopico.get();
            if(datosActualizarTopico.titulo() != null){
                topicoAModificar.setTitulo(datosActualizarTopico.titulo());
            }
            if(datosActualizarTopico.mensaje() != null){
                topicoAModificar.setMensaje(datosActualizarTopico.mensaje());
            }
            if(datosActualizarTopico.cursoDto() != null){
                topicoAModificar.setCurso(new Curso(datosActualizarTopico.cursoDto()));
            }
            topicoRepository.save(topicoAModificar);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity borrarTopico(@PathVariable Long id){
        Optional<Topico> topicoParaEliminar = topicoRepository.findById(id);
        if (topicoParaEliminar.isPresent()){
            var topico = topicoParaEliminar.get();
            topicoRepository.deleteById(topico.getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
