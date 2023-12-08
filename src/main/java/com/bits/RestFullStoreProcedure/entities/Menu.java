package com.bits.RestFullStoreProcedure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @ManyToOne
//    @JoinColumn(name = "menu_id", referencedColumnName = "id")
//    private Menu menu;

//    @OneToMany
//    @JoinColumn(name = "menu_id", referencedColumnName = "id")
//    private List<Menu> childMenu;


    /*@Column(name = "menu_id")
    private Long menuId;*/

    @Column
    private String nombre;

    @Column
    private String url;

    @Column
    private Long orden;

    @Column
    private boolean activo;


    @JsonManagedReference
    @OneToMany(mappedBy = "padre")
//    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Set<Menu> childMenu;

    @ManyToOne
    @JsonBackReference
    private Menu padre;

}
