package com.alura.forohub.domain.usuario;

public record AutorDelTopico(
        Long id,
        String nombreUsuario
) {
    public AutorDelTopico(Usuario usuario){
        this(usuario.getId(), usuario.getNombre());
    }
}
