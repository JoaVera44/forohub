package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.curso.CursoDto;
import com.alura.forohub.domain.usuario.AutorDelTopico;


import java.time.LocalDateTime;

public record DatosEnvioTopico(

        String titulo,

        String mensaje,

        AutorDelTopico usuario,

        CursoDto curso

) {
}
