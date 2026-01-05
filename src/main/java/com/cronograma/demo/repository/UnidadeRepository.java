package com.cronograma.demo.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cronograma.demo.model.Unidade;

public interface UnidadeRepository extends JpaRepository<Unidade, Long> {
    @Query("SELECT u FROM Unidade u JOIN u.usuarios us WHERE us.uuid = :usuarioId")
    List<Unidade> findAllByUsuarioId(@Param("usuarioId") UUID usuarioId);

    @Query("SELECT u FROM Unidade u JOIN u.usuarios usr WHERE u.id = :idUnidade AND usr.uuid = :usuarioId")
    Optional<Unidade> findByIdAndUsuario(
        @Param("idUnidade") Long idUnidade,
        @Param("usuarioId") UUID usuarioId
    );

}
