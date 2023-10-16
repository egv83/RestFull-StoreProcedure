package com.bits.RestFullStoreProcedure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedStoredProcedureQuery(name = "Persona.spPer", procedureName = "spPersona"
,parameters = {
        @StoredProcedureParameter(mode = ParameterMode.IN,name = "operacion", type = String.class), //PARAMETRO DE ENTRADA DEL SP, SE DEFINE EL TIPO DE DATO DE ENTRADA
        //@StoredProcedureParameter(mode = ParameterMode.OUT, name = "paramOUT",type = List.class) //PARAMETRO DE SALIDA DEL SP, SE DEFINE EL TIPO DE DATO DE SALIDA
})
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer idPersona;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String direccion;

    @Column
    private String ciudad;

}
