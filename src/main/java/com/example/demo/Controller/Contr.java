package com.example.demo.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Categorie_enchere;
@RestController
@CrossOrigin
public class Contr {

    @GetMapping("/log")
    public Boolean log(@RequestParam (value = "email") String email,@RequestParam(value = "mdp" )String mdp ) throws SQLException{
        Admin adm=new Admin(email,mdp);
        Boolean v=adm.login();

        return v;
    }

    @GetMapping("/bencemois")
    public int bencemois() throws SQLException{
        return new Admin().get_ben_ce_mois();
    }  
    
    @GetMapping("/benparmois")
    public int[] benparmois() throws SQLException{
        return new Admin().get_ben_par_mois();
    }

    @GetMapping("/bentotal")
    public int bentotal(){

        return new Admin().bentotal();
    }

    @GetMapping("/categplusvendu")
    public Categorie_enchere categ_plus_vendu() throws SQLException{
        return new Admin().categ_plus_vendu();
    }

    @GetMapping("/enchereparcateg")
    public ArrayList<Object[]> enchereparcateg() throws SQLException{
        return new Admin().enchereparcateg();
    }

    @GetMapping("/nbdemande")
    public int getnbdemande() throws SQLException{
        return new Admin().getnbdemande();
    }

    @GetMapping("/commission")
    public double getCom() throws SQLException{
        return new Admin().getCommmission();
    }

    @GetMapping("/duree")
    public double getduree() throws SQLException{
        return new Admin().getDure();
    }

    @GetMapping("/updateduree")
    public void updateduree(@RequestParam (value = "duree")double duree) throws SQLException{
        new Admin().update_duree(duree);
    }
    @GetMapping("/updatecom")
    public void updatecom(@RequestParam(value = "com") double com) throws SQLException{
        new Admin().update_com(com);
    }
    @GetMapping("/Info")
    public int[] all() throws SQLException{
        return new Admin().all();
    }
}
