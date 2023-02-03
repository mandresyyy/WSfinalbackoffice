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
public class Utilisateur {
    int idutilisateur;
    String nom;
    String prenom;
    LocalDate date_naissance;
    String email;
    String motdepasse;
    double solde;
    String token;

    public Utilisateur() {
    }
    public Utilisateur(int id,double d) {
        this.idutilisateur=id;
        this.solde=d;
    }

    public Utilisateur(int idutilisateur, String nom, String prenom, double solde) {
        this.idutilisateur = idutilisateur;
        this.nom = nom;
        this.solde = solde;
        this.prenom=prenom;
    }

    public Utilisateur(int idutilisateur, String nom, String prenom, LocalDate date_naissance, String email, String motdepasse, double solde) {
        this.idutilisateur = idutilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.email = email;
        this.motdepasse = motdepasse;
        this.solde = solde;
    }

    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
