package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Persona;
import com.bits.RestFullStoreProcedure.services.PersonaServices;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class PersonaController {

    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private PersonaServices personaServices;

//    @GetMapping(path = "/spPersonas")
//    public String getSpPersona(@RequestParam("operacion") String operacion){
//        HashMap<String,String> params = new HashMap<>();
//        Persona persona = new Persona();
//
//        persona.setId(3);
//        persona.setNombre("Emilia");
//        persona.setApellido("Vallejo");
//        persona.setDireccion("calle 1");
//        persona.setCiudad("Ibarra");
//
//        params.put("id",persona.getId().toString());
//        params.put("nombre",persona.getNombre());
//        params.put("apellido",persona.getApellido());
//        params.put("direccion",persona.getDireccion());
//        params.put("ciudad",persona.getCiudad());
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode json = mapper.valueToTree(params);
//
//
//        return personaServices.getPersonas(operacion,json.toString());
//    }

    @PostMapping(path = "/persona")
    @ResponseBody
    public ResponseEntity<Persona> createPersona(@RequestBody Persona persona){
        //Persona persona;

        try{
            System.out.println("PERSONA: "+persona.toString());
            String personaJson = objectMapper.writeValueAsString(persona);
            System.out.println("json: "+personaJson);

            return new ResponseEntity<>(personaServices.CreatePersonasAsName("C",personaJson),HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/personas")
    public ResponseEntity<List> getPersonas(@RequestParam("operacion") String operacion){
        Persona persona = new Persona();

        try {
            String personaJson = objectMapper.writeValueAsString(persona);

            return new ResponseEntity<>(personaServices.getPersonas(operacion,personaJson),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/persona")
    public ResponseEntity<Persona> getPersona(@RequestBody Persona persona){
        //Persona persona = new Persona();

        try {
            String personaJson = objectMapper.writeValueAsString(persona);

            return new ResponseEntity<>(personaServices.getPersona("R",personaJson),HttpStatus.OK);
           // return null;
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/persona")
    public ResponseEntity updatePersna(@RequestBody Persona persona){
        Persona personaFind = new Persona();
        String personaJson = null;

        try{
            personaJson = objectMapper.writeValueAsString(persona);
            personaFind = personaServices.getPersona("R",personaJson);
            if(!personaFind.getNombre().equals(persona.getNombre())){
                personaFind.setNombre(persona.getNombre());
            }
            if (!personaFind.getApellido().equals(persona.getApellido())){
                personaFind.setApellido(persona.getApellido());
            }
            if (!personaFind.getDireccion().equals(persona.getDireccion())){
                personaFind.setDireccion(persona.getDireccion());
            }
            if (!personaFind.getCiudad().equals(persona.getCiudad())){
                personaFind.setCiudad(persona.getCiudad());
            }

            personaJson = objectMapper.writeValueAsString(personaFind);
            return new ResponseEntity<>(personaServices.CreatePersonasAsName("U",personaJson),HttpStatus.OK);
            //return null;
        }catch (Exception e){
            return new ResponseEntity(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**********************************/
    /*  llamado de sp por metodo call */
    /*********************************/
    @GetMapping(path = "/spCall")
    public String getPersonaCall(){
        HashMap<String,String> params = new HashMap<>();
        Persona persona = new Persona();

        persona.setId(Long.parseLong(String.valueOf(1)));
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
