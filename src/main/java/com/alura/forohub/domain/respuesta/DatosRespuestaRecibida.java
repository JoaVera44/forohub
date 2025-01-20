package com.alura.forohub.domain.respuesta;

import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.usuario.Usuario;

public record DatosRespuestaRecibida(
        String mensaje,
        String solucion,
        Topico topico,
        Usuario usuario
) {
}
