package com.bits.RestFullStoreProcedure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long menu_id;

    @Column
    private String nombre;

    @Column
    private String url;

    @Column
    private Long orden;

    @Column
    private boolean activo;
}
