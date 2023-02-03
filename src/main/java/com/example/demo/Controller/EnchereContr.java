package com.example.demo.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Enchere;
import com.example.demo.Model.Mouvement_enchere;

@RestController
@CrossOrigin
public class EnchereContr {
    @GetMapping("/encours")
    public ArrayList<Enchere> listeencours() throws SQLException{
        return new Enchere().encours();
    }
    @GetMapping("/historique")
    public ArrayList<Enchere> listevita() throws SQLException{
        return new Enchere().historique();
    }

    @GetMapping("/mouv")
    public ArrayList<Mouvement_enchere> getmouv(@RequestParam (value="id") int id) throws SQLException{
        return new Enchere(id).liste_mouv();
    }

}
