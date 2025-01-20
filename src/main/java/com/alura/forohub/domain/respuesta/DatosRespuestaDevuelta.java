package com.alura.forohub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosRespuestaDevuelta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion
) {
}
