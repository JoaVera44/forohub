package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.respuesta.Respuesta;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "usuarios_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cursos_id", nullable = false)
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;

    public Topico() {
    }

    public Topico(@Valid DatosEnvioTopico datosEnvioTopico) {
        this.titulo = datosEnvioTopico.titulo();
        this.mensaje = datosEnvioTopico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.usuario = new Usuario(datosEnvioTopico.usuario());
        this.curso = new Curso(datosEnvioTopico.curso());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }
}
