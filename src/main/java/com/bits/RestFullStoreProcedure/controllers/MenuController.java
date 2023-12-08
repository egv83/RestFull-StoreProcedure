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

/*    @GetMapping("/menu")
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

    }*/

    //creacion de menú con menus hijos en lista
    @PostMapping("/menu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        Menu crearMenu;
        Menu me = new Menu();
        try{
            return new ResponseEntity<>(crearMenu(menu),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // creacion antigua
    /*@PostMapping("/menu")
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

            if(!crearMenu.getChildMenu().isEmpty()){

            }

            return new ResponseEntity<>(menuServices.createMenu(crearMenu),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/

    private Menu crearMenu(Menu menu){
        Menu newMenu;

        try{
            newMenu = new Menu();
            if (menu != null){
                newMenu.setId((menuServices.getCountAllRows()+1));
                newMenu.setNombre(menu.getNombre());
                newMenu.setUrl(menu.getUrl());
                newMenu.setOrden(menu.getOrden());
                newMenu.setActivo(menu.isActivo());

                newMenu = menuServices.createMenu(newMenu);
                if( (newMenu != null && newMenu.getId() != null) && (menu.getChildMenu() != null && !menu.getChildMenu().isEmpty()) ){
                    newMenu.setChildMenu(createSubMenu(menu.getChildMenu(), newMenu));
                }
            }
            return  newMenu;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR AL CREAR EL MENÚ");
        }
        return null;
    }

    private List<Menu> createSubMenu(List<Menu> subMenu,Menu menuPadre){
        Menu newMenu;
        List<Menu> listaMenu = new ArrayList<>();
        List<Menu> listaPadre = new ArrayList<>();
        try{

            if(!subMenu.isEmpty()) {
                for (Menu subMenu1 : subMenu) {
                    newMenu = new Menu();
                    newMenu.setId((menuServices.getCountAllRows()+1));
                    newMenu.setNombre(subMenu1.getNombre());
                    newMenu.setUrl(subMenu1.getUrl());
                    newMenu.setOrden(subMenu1.getOrden());
                    newMenu.setActivo(subMenu1.isActivo());

                    listaPadre.add(menuPadre);
                    newMenu.setChildMenu(listaPadre);

                    newMenu = menuServices.createMenu(newMenu);
                    if( (newMenu != null && newMenu.getId() != null) && (subMenu1.getChildMenu() != null && !subMenu1.getChildMenu().isEmpty()) ){
                        createSubMenu(subMenu1.getChildMenu(),subMenu1);
                    }
                    listaMenu.add(newMenu);
                }
            }
            return listaMenu;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("ERROR EN LISTA SUB MENÚ");
        }
        return null;
    }
}