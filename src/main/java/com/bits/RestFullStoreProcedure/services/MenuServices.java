package com.bits.RestFullStoreProcedure.services;

import com.bits.RestFullStoreProcedure.entities.Menu;
import com.bits.RestFullStoreProcedure.entities.Menu2;
import com.bits.RestFullStoreProcedure.repositories.MenuRepository;
import com.bits.RestFullStoreProcedure.repositories.MenuRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServices {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuRepository2 menuRepository2;

    public Long getCountAllRows(){
        return menuRepository.countAllRows();
    }

    public Menu createMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }

    public List<Menu> getMenuPadre(){
        return menuRepository.getMenuPadre();
    }

    public Optional<Menu> findById(Long id){
        return menuRepository.findById(id);
    }

    public void delete(Long id){
        menuRepository.deleteById(id);
    }

    public Menu getById(Long id){
        return menuRepository.getById(id);
    }

    public Menu2 createMnu2(Menu2 menu2){
        return  menuRepository2.save(menu2);
    }
}
