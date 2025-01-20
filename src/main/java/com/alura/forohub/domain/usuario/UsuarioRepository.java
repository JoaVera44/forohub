package com.alura.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByCorreo(String username);

    @Query(value = """
            SELECT * FROM usuarios
            WHERE correo = :correo;
            """, nativeQuery = true)
    Usuario findUsuarioByCorreo(@Param("correo") String correo);

    Usuario findByNombre(String subject);
}
