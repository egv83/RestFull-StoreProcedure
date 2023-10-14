package com.bits.RestFullStoreProcedure.repositories;

import com.bits.RestFullStoreProcedure.entities.Perona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Perona, Long> {
    @Query(value = "call spPersona(: operacion);", nativeQuery = true)
    List<Perona> getPersonas(@Param("operacion") String operacion);
}
