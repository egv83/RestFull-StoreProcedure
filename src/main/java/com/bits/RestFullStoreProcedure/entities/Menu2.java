package com.bits.RestFullStoreProcedure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu2 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Lob
    @Column(name = "url")
    private String url;

    @Column(name = "orden")
    private Integer orden;

    @Column(name = "activo")
    private Boolean activo;

    @JsonManagedReference
    @OneToMany(mappedBy = "menuId")
    private Collection<Menu2> menuCollection;

    @JsonBackReference
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    @ManyToOne
    private Menu2 menuId;

}
