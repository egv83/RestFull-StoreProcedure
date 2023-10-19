package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Persona;
import com.bits.RestFullStoreProcedure.services.PersonaServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
        HashMap<String,String> params = new HashMap<>();
        Persona persona = new Persona();

        persona.setId(2);
        persona.setNombre("Esteban");
        persona.setApellido("Vallejo");
        persona.setDireccion("calle 1");
        persona.setCiudad("Ibarra");

        params.put("id",persona.getId().toString());
        params.put("nombre",persona.getNombre());
        params.put("apellido",persona.getApellido());
        params.put("direccion",persona.getDireccion());
        params.put("ciudad",persona.getCiudad());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.valueToTree(params);

        return personaServices.getPersonasCallSP(json.toString());
    }

}
