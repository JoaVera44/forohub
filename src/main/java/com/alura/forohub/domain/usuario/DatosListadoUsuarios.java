package com.alura.forohub.domain.usuario;

import com.alura.forohub.domain.perfil.Perfil;

public record DatosListadoUsuarios(
        String nombre,
        String correo,
        Perfil perfil
) {
}
