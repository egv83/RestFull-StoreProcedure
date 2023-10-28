package com.bits.RestFullStoreProcedure.services;

import com.bits.RestFullStoreProcedure.entities.Persona;
import com.bits.RestFullStoreProcedure.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonaServices {

    @Autowired
    private PersonaRepository personaRepository;

//    public String getPersonas(String operacion,String params){
//        return personaRepository.spPersona(operacion, params);
//    }

    @Transactional
    public Persona CreatePersonasAsName(String params){
        return personaRepository.CreatePersona("C",params);
    }

    public List<Persona> getPersonas(String operacion){
        return personaRepository.getPersonaAll(operacion);
    }


    public String getPersonasCallSP(String params){
        System.out.println("JSON: "+params);
        return personaRepository.getPersonasCallSp("I",params);
    }

}
