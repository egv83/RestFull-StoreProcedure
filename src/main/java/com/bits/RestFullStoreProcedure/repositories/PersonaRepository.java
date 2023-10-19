package com.bits.RestFullStoreProcedure.repositories;

import com.bits.RestFullStoreProcedure.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {

    //LLAMAR AL SP COMO SI FUERA UN METODO
    @Procedure
    List<Persona> spPersona(String operacion);

    //LLAMAR A SP POR NOMBRE DEFINIDO EN EL ENTITY
    @Procedure(name = "Persona.spPer")
    String getPersonaAsName(String operacion);

    //OBTENER DATOS LLAMANDO AL STORE PROCEDURE COMO QUERY
    @Query(value = "call spPersona(:operacion,CAST(:paramData AS JSON));", nativeQuery = true)
    String getPersonasCallSp(@Param("operacion") String operacion
            , @Param("paramData") String params);
}
