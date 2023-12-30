package com.bits.RestFullStoreProcedure.controllers;

import com.bits.RestFullStoreProcedure.entities.Menu;
import com.bits.RestFullStoreProcedure.entities.Menu2;
import com.bits.RestFullStoreProcedure.services.MenuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.awt.SystemColor.menu;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuServices menuServices;

    //METODO GET DE MENUS
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

    @PostMapping("/createMenuList")
    public ResponseEntity<Menu> createMenuList(@RequestBody Menu menu){
        try{
            Long count = (menuServices.getCountAllRows()+1);
            Long orden = 1L;

            menu.setId(count);
            menu.setOrden(orden);
            Menu menuSaved = menuServices.createMenu(menu);

            if(menu.getMenuList() != null && !menu.getMenuList().isEmpty()){
                menuSaved.setMenuList(createSubMenu(menu.getMenuList(),menuSaved.getId(),orden,count));
            }

            return new ResponseEntity<>(menuSaved,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Menu> createSubMenu(List<Menu> menuHijos, Long menuPadre,Long orden,Long count){
        List<Menu> listaSubMenus = null;
        if(menuHijos != null && !menuHijos.isEmpty()){
            listaSubMenus = new ArrayList<>();
            orden++;
            for (Menu menuHijo : menuHijos){
                count++;
                menuHijo.setId(count);
                menuHijo.setOrden(orden);
                menuHijo.setMenuId(new Menu(menuPadre));
                Menu subMenuSaved = menuServices.createMenu(menuHijo);
                //menuServices.createMenu(menuHijo);
                if(menuHijo.getMenuList() != null && !menuHijo.getMenuList().isEmpty()){
                   subMenuSaved.setMenuList(createSubMenu(menuHijo.getMenuList(),subMenuSaved.getId(),orden,count));
                }
                listaSubMenus.add(subMenuSaved);
            }
        }
        return listaSubMenus;
    }

    // CREACION DE MENU ASIGNANDO EL PADRE
    @PostMapping("/createMenu")
    public ResponseEntity<Menu> createMenu(@RequestBody Menu menu){
        Menu tmpMenu;
        try{

            menu.setId((menuServices.getCountAllRows()+1));

            if(menu.getMenuId() != null && menu.getMenuId().getId() != null){
                Optional<Menu> menuOptional = menuServices.findById(menu.getMenuId().getId());
                if(menuOptional.isPresent()){
                    tmpMenu = menuOptional.get();
                    if(tmpMenu.getOrden() !=null ){
                        menu.setOrden(tmpMenu.getOrden()+1);
                    }
                }
            } else if (menu.getMenuId() ==null ){
                menu.setOrden(1L);
            }

            return new ResponseEntity<>(menuServices.createMenu(menu),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deletMenu")
    public void deleteMenu(@RequestBody Menu menu){
        try{
            if(menu != null){
                menuServices.delete(menu.getId());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}