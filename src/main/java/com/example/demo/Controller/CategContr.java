package com.example.demo.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Categorie_enchere;
import com.example.demo.utilitaire.GenericDAO;

@RestController
@CrossOrigin
public class CategContr {
    @GetMapping("/listercateg")
    public ArrayList<Object> lister() throws SQLException{
        GenericDAO gd=new GenericDAO(new Categorie_enchere());
        return  gd.Select();
    }
    @CrossOrigin
    @PostMapping("/savecateg")
    public void savecateg(@RequestBody Categorie_enchere c) throws SQLException{
        
        c.save();
    }
}
