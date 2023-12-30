package com.bits.RestFullStoreProcedure.repositories;

import com.bits.RestFullStoreProcedure.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("Select COUNT(m) FROM Menu m")
    Long countAllRows();

    @Query("SELECT m FROM Menu m WHERE m.menuId IS NULL")
    List<Menu> getMenuPadre();

    @Override
    List<Menu> findAll();

    Optional<Menu> findById(Long id);

    Menu getById(Long id); //SE DEMORA EN TRAER TODO LOS OBJETOS CUANDO TIENE UN HIJO

//    @Override
//    void delete(Long id) {
//
//    }
}
