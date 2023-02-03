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
public class Rechargement extends BaseModel{
    Utilisateur util;
    Double montant;
    LocalDate date;
    int etat;

    public Rechargement() {
    }

    public Rechargement(int id,Utilisateur util, Double montant, LocalDate date, int etat) {
        this.util = util;
        this.montant = montant;
        this.date = date;
        this.etat = etat;
        this.id=id;
    }

    public Utilisateur getUtil() {
        return util;
    }

    public void setUtil(Utilisateur util) {
        this.util = util;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    
    
    
}
