package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String solucion;

    @ManyToOne
    @JoinColumn(name = "usuarios_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "topicos_id")
    private Topico topico;


    public Respuesta() {
    }

    public Respuesta(@Valid DatosRespuestaRecibida datosRespuestaRecibida) {
    this.mensaje = datosRespuestaRecibida.mensaje();
    if(datosRespuestaRecibida.solucion() != null){
    this.solucion = datosRespuestaRecibida.solucion();}
    this.fechaCreacion = LocalDateTime.now();
    this.topico = datosRespuestaRecibida.topico();
    this.usuario = datosRespuestaRecibida.usuario();
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
