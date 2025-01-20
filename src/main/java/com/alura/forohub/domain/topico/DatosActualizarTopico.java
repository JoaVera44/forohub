package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.Curso;
import com.alura.forohub.domain.curso.CursoDto;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        CursoDto cursoDto

) {


}
