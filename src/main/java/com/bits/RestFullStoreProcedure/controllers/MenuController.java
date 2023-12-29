package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Menu;
import com.bits.RestFullStoreProcedure.entities.Menu2;
import com.bits.RestFullStoreProcedure.services.MenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServices menuServices;


//    @GetMapping("/menu")
//    public ResponseEntity<List<Menu>> getTotalRows() {
//        List<Menu> menu1 = new ArrayList<>();
//        List<Menu> menuTmp = menuServices.getAllMenu();
//        try {
//            for (Menu menus : menuTmp) {
//                System.out.println("EN TOTAL ROWS");
//                System.out.println("MENU getMenu: " + menus.getMenu());
//                if (menus.getMenu() == null) {
//                    System.out.println("MENU PADRE getId: " + menus.getId());
//                    subMenu(menuTmp, menus);
//                    menu1.add(menus);
//                }
//            }
//            return new ResponseEntity<>(menu1, HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }

    @GetMapping("/getMenu")
    public ResponseEntity<List<Menu>> getMenuPadreHijo(){
        List<Menu> listaMenuPadre;
        try {
            listaMenuPadre = menuServices.getMenuPadre();

            return new ResponseEntity<>(listaMenuPadre, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //return listaMenuPadre;
    }

    @PostMapping("/createMenu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        try{
            Long count = (menuServices.getCountAllRows()+1);

            menu.setId(count);
            Menu menuSaved = menuServices.createMenu(menu);

            if(menu.getMenuList() != null && !menu.getMenuList().isEmpty()){

                createSubMenu(menu.getMenuList(),menuSaved,count);
            }
            return new ResponseEntity<>(menuSaved,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void createSubMenu(List<Menu> menuHijos, Menu menuPadre,Long count){
        if(menuHijos != null && !menuHijos.isEmpty()){
            for (Menu menuHijo : menuHijos){
                count++;
                menuHijo.setId(count);
                menuHijo.setMenuId(menuPadre);
                Menu subMenuSaved = menuServices.createMenu(menuHijo);
                //menuServices.createMenu(menuHijo);
                if(menuHijo.getMenuList() != null && !menuHijo.getMenuList().isEmpty()){
                    createSubMenu(menuHijo.getMenuList(),subMenuSaved ,count);
                }
            }
        }
    }

    //@CrossOrigin
    @GetMapping("/menuTEMP")
    public List<Menu> getMenu() {

        List<Menu> todosLosMenus = menuServices.getAllMenu();
        List<Menu> getListaMenus = new ArrayList<>();

        for (Menu menu : todosLosMenus) {
            if(menu.getMenuId() == null){
                getListaMenus.add(menu);
                System.out.println("Menú Padre:");
                System.out.println("ID: " + menu.getId());
                System.out.println("Nombre: " + menu.getNombre());
                System.out.println("URL: " + menu.getUrl());
                System.out.println("Orden: " + menu.getOrden());
                System.out.println("Activo: " + menu.isActivo());
                System.out.println("--------------------------------------");

//                if(menu.getMenuList() != null && !menu.getMenuList().isEmpty()) {
//                    subMenus(menu);
//                }

            }
        }
        return getListaMenus;
    }


    private void subMenus(Menu menuPadre){
        // Mostrar los menús hijos, si existen
        List<Menu> menusHijos = menuPadre.getMenuList();

        if (menusHijos != null && !menusHijos.isEmpty()) {
            System.out.println("Menús Hijos:");
            for (Menu menuHijo : menusHijos) {
                System.out.println("ID: " + menuHijo.getId());
                System.out.println("Nombre: " + menuHijo.getNombre());
                System.out.println("URL: " + menuHijo.getUrl());
                System.out.println("Orden: " + menuHijo.getOrden());
                System.out.println("Activo: " + menuHijo.isActivo());
                // Puedes agregar más información que desees mostrar
                System.out.println("--------------------------------------");

                if (menuHijo.getMenuList() != null && !menuHijo.getMenuList().isEmpty()) {
                    subMenus(menuHijo);
                }
            }
        } else {
            System.out.println("El menú padre no tiene menús hijos.");
        }
    }

    // PARA CREAR MENU
//    @PostMapping("/crearMenu")
//    public void guardarMenu(@RequestBody Menu menu) {
//        // CONTADOR PARA EL ID DEL MENU
//        Long count = (menuServices.getCountAllRows()+1);
//
//        // Guardar menú padre
//        Menu menuGuardado = menuServices.createMenu(menu);
//
//        if(menu.getMenuList()getChildMenus() != null && !menu.getChildMenus().isEmpty()) {
//            crearSubMenu(menu,menuGuardado.getId());
//        }
//
//    }
//
//    private void crearSubMenu(Menu menu, Menu menuPadre){
//
//        // Si el menú tiene menús hijos, establecer la relación y guardarlos
//        List<Menu> menusHijos = menu.getChildMenus();
//        if (menusHijos != null && !menusHijos.isEmpty()) {
//            for (Menu menuHijo : menusHijos) {
//                menuHijo.setParentMenu(menuPadre); // Establecer el menú padre para cada menú hijo
//                menuServices.createMenu(menuHijo); // Guardar cada menú hijo
//            }
//        }
//
//    }

    //creacion de menú con menus hijos en lista
//    @PostMapping("/menu")
//    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu) {
//        Menu newMenu;
//        Long count = 0L;
//
//        try {
//            count = (menuServices.getCountAllRows() + 1);
//
//            menu.setId(count);
//
//            return new ResponseEntity<>(menuServices.createMenu(menu), HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


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

}