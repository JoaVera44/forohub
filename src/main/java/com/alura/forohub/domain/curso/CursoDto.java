package com.alura.forohub.domain.curso;

public record CursoDto(
        Long id,
        String nombreCurso
) {
    public CursoDto(Curso curso){
        this(curso.getId(), curso.getNombre());
    }
}
