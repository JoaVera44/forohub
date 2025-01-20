package com.alura.forohub.domain.curso;

import com.alura.forohub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.List;

@Entity(name = "Curso")
@Table(name = "cursos")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String nombre;
    String categoria;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    public Curso() {
    }

    public Curso(CursoDto cursoDto){
        this.id = cursoDto.id();
        this.nombre = cursoDto.nombreCurso();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Topico> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<Topico> topicos) {
        this.topicos = topicos;
    }
}
