package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Menu;
import com.bits.RestFullStoreProcedure.services.MenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static java.awt.SystemColor.menu;

@RestController
public class MenuController {

    @Autowired
    private MenuServices menuServices;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getTotalRows(){
        List<Menu> menu1 = new ArrayList<>();
        try{
            for (Menu menus: menuServices.getAllMenu()){
                if(menus.getMenu() == null) {
                    menu1.add(menus);
                }
            }
           return new ResponseEntity<>(menu1,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        Menu crearMenu;
        Menu me = new Menu();
        try{
            //me.setId(menu.getMenuId().getId());

            crearMenu = new Menu();
            crearMenu.setId((menuServices.getCountAllRows()+1));
            crearMenu.setMenu(menu.getMenu());
            crearMenu.setNombre(menu.getNombre());
            crearMenu.setUrl(menu.getUrl());
            crearMenu.setOrden(menu.getOrden());
            crearMenu.setActivo(menu.isActivo());

            return new ResponseEntity<>(menuServices.ceateMenu(crearMenu),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}