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
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;

@RestController
public class MenuController {

    @Autowired
    private MenuServices menuServices;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> getTotalRows() {
        List<Menu> menu1 = new ArrayList<>();
        List<Menu> menuTmp = menuServices.getAllMenu();
        try {
            for (Menu menus : menuTmp) {
                System.out.println("EN TOTAL ROWS");
                System.out.println("MENU getMenu: " + menus.getMenu());
                if (menus.getMenu() == null) {
                    System.out.println("MENU PADRE getId: " + menus.getId());
                    subMenu(menuTmp, menus);
                    menu1.add(menus);
                }
            }
            return new ResponseEntity<>(menu1, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void subMenu(List<Menu> listMenu, Menu menuPadre) {
        Map<Long, Menu> menuMap = listMenu.stream().collect(Collectors.toMap(Menu::getId, Function.identity()));
        System.out.println("MENU PADRE: " + menuPadre.getId());
        List<Menu> menuHijoList = new ArrayList<>();
        for (Menu menuAux : listMenu) {
            System.out.println("MENU AUX getMenu: " + menuAux.getMenu());
            if (Objects.nonNull(menuAux.getMenu())) {

                if (menuAux.getMenu().getId() == menuPadre.getId()) {
                    System.out.println("MENU AUX getMenu.getId: " + menuAux.getMenu().getId());
                    System.out.println("Menu HIJO: "+menuAux.getId());
                }
            }
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