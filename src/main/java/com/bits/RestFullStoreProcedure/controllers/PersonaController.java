package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Persona;
import com.bits.RestFullStoreProcedure.services.PersonaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @GetMapping(path = "/spPersonas")
    public List<Persona> getSpPersona(@RequestParam("operacion") String operacion){
        return personaServices.getPersonas(operacion);
    }

    @GetMapping(path = "/personasAsName")
    public String getPersonasAsName(){
        return personaServices.getPersonasAsName();
    }

    @GetMapping(path = "/spCall")
    public String getPersonaCall(){
        return personaServices.getPersonasCallSP();
    }

}
