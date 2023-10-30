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
//    @Procedure
//    String spPersona(String operacion,String paramData);

    //LLAMAR A SP POR NOMBRE DEFINIDO EN EL ENTITY
    @Procedure(name = "Persona.spPer")
    Persona CreatePersona(String operacion,String paramData);

    @Procedure(name = "Persona.spPer")
    List<Persona> getPersonaAll(String operacion,String paramData);

    //OBTENER DATOS LLAMANDO AL STORE PROCEDURE COMO QUERY
    @Query(value = "call spPersona(:operacion,CAST(:paramData AS JSON));", nativeQuery = true)
    String createPersonasCallSp(@Param("operacion") String operacion
            , @Param("paramData") String params);

    @Query(value = "call spPersona(:operacion,CAST(:paramData AS JSON));", nativeQuery = true)
    List<Persona> getPersonasCallSp(@Param("operacion") String operacion
            , @Param("paramData") String params);
}
