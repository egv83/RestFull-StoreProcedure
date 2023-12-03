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
    public Persona CreatePersonasAsName(String operacion, String params){
        return personaRepository.CreatePersona(operacion,params);
    }

    @Transactional /* se utiliza esta anotacion cuando no se usa la opcion de native query */
    public List<Persona> getPersonas(String operacion, String params){
        return personaRepository.GetPersonaAll(operacion,params);
        //return personaRepository.getPersonasCallSp(operacion,params);
    }

    @Transactional /* se utiliza esta anotacion cuando no se usa la opcion de native query */
    public Persona getPersona(String operacion, String params){
        return personaRepository.GetPersona(operacion,params);
        //return personaRepository.getPersonasCallSp(operacion,params);
    }



    public String getPersonasCallSP(String params){
        System.out.println("JSON: "+params);
        return personaRepository.createPersonasCallSp("I",params);
    }

}
