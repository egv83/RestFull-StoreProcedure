package com.bits.RestFullStoreProcedure.repositories;

import com.bits.RestFullStoreProcedure.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    //LLAMAR AL SP COMO SI FUERA UN METODO
    @Procedure
    List<Persona> spPersona(String operacion);

    //LLAMAR A SP POR NOMBRE DEFINIDO EN EL ENTITY
    @Procedure(name = "Persona.spPer")
    List<Persona> getPersonaAsName(String operacion);

    //OBTENER DATOS LLAMANDO AL STORE PROCEDURE COMO QUERY
    @Query(value = "call spPersona(: operacion);", nativeQuery = true)
    List<Persona> getPersonasCallSp(@Param("operacion") String operacion);
}
