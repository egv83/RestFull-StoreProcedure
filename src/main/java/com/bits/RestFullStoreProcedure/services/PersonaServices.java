package com.bits.RestFullStoreProcedure.services;

import com.bits.RestFullStoreProcedure.entities.Persona;
import com.bits.RestFullStoreProcedure.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonaServices {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> getPersonas(String operacion){
        return personaRepository.spPersona(operacion);
    }

    public List<Persona> getPersonasAsName(){
        return personaRepository.getPersonaAsName("I");
    }

    public List<Persona> getPersonasCallSP(){
        return personaRepository.getPersonasCallSp("I");
    }

}
