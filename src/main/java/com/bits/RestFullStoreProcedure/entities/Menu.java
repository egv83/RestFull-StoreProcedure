package com.bits.RestFullStoreProcedure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "id", unique = true,nullable = false)
    @Column(name = "id")
    private Long id;

    /***************************************************/
    /* RELACION SIN LISTA O COLECTION ES PRIMERA OPCIÓN*/
    /***************************************************/
    /*@ManyToOne
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;*/

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "url")
    private String url;

    @Column(name = "orden")
    private Long orden;

    @Column(name = "activo")
    private boolean activo;


    /* pruebas para relacion uno a muchos*/

    //@JsonIgnore //ESTE SOLO TOMA LOS DATOS QUE EL PADRE ES NULL
    @JsonManagedReference
    @OneToMany(fetch=FetchType.LAZY,mappedBy = "menuId")
    private List<Menu> menuList;

    @JsonBackReference
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @ManyToOne(fetch=FetchType.LAZY)
    private Menu menuId;


}
