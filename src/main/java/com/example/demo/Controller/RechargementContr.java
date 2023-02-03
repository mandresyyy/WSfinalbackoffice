package com.example.demo.Controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Admin;
import com.example.demo.Model.Rechargement;

@RestController
@CrossOrigin
public class RechargementContr {
    @GetMapping("/listerecharg")
    public ArrayList<Rechargement> listedemande() throws SQLException{
        return new Admin().lister_rechargement();
        
    }

    @PostMapping("/validerdemande")
    public void validerrecharge(@RequestBody Rechargement r) throws SQLException{
        new Admin().valider_demande(r);

    }
}
