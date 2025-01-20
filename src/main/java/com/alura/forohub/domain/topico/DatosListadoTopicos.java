package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.usuario.AutorDelTopico;

import java.time.LocalDateTime;

public record DatosListadoTopicos(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        AutorDelTopico autor
) {
}
