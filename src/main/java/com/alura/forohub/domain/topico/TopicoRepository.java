package com.alura.forohub.domain.topico;

import com.alura.forohub.domain.usuario.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
            SELECT t FROM Topico t
            WHERE t.usuario.id = :id
            """)
    Page<Topico> findAllporAutor(Pageable pageable,@Param("id") Long id);



}
