package com.bits.RestFullStoreProcedure.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "persona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Perona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String direccion;

    @Column
    private String ciudad;

}
