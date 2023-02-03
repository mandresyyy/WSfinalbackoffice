/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Model;

import java.time.LocalDate;

/**
 *
 * @author Mandresy
 */
public class Mouvement_enchere extends BaseModel{
    Utilisateur util;
    double montant;
    Enchere enchere;

    public Mouvement_enchere() {
    }

    public Mouvement_enchere(int id,Utilisateur util, double montant, Enchere enchere) {
        this.util = util;
        this.montant = montant;
        this.enchere = enchere;
        this.id=id;
    }

    public Utilisateur getUtil() {
        return util;
    }

    public void setUtil(Utilisateur util) {
        this.util = util;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Enchere getEnchere() {
        return enchere;
    }

    public void setEnchere(Enchere enchere) {
        this.enchere = enchere;
    }
    
    
}
