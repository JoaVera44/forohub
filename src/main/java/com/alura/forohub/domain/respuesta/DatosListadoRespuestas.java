package com.alura.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListadoRespuestas(
        String mensaje,
        LocalDateTime fechaCreacion,
        String solucion

) {
}
