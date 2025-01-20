package com.alura.forohub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    @Query("""
            select r from Respuesta r
            where r.topico.id = :id
            """)
    Page<Respuesta> findAllByTopicosId(Pageable pageable, Long id);
}
