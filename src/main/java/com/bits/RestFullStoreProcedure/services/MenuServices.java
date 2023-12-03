package com.bits.RestFullStoreProcedure.services;

import com.bits.RestFullStoreProcedure.entities.Menu;
import com.bits.RestFullStoreProcedure.repositories.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServices {

    @Autowired
    private MenuRepository menuRepository;

    public Long getCountAllRows(){
        return menuRepository.countAllRows();
    }

    public Menu ceateMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public List<Menu> getAllMenu(){
        return menuRepository.findAll();
    }
}
