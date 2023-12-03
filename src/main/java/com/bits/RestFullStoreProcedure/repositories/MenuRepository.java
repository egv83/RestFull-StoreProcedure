package com.bits.RestFullStoreProcedure.repositories;

import com.bits.RestFullStoreProcedure.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("Select COUNT(m) FROM Menu m")
    Long countAllRows();

    @Override
    List<Menu> findAll();

}
