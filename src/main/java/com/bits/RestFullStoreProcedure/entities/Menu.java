package com.bits.RestFullStoreProcedure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

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

    //@OneToMany(mappedBy = "menuId")
    //private Collection<Menu> menuCollection;
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @ManyToOne
    private Menu menuId;
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
}
