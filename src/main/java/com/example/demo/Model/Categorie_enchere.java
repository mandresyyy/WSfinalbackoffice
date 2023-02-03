/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.demo.utilitaire.Connexion;
import com.example.demo.utilitaire.GenericDAO;

/**
 *
 * @author Mandresy
 */
public class Categorie_enchere {
    String libelle;
    int idcategorie_enchere;

    public Categorie_enchere() {
    }

    public Categorie_enchere( int idcategorie_enchere,String libelle) {
        this.libelle = libelle;
        this.idcategorie_enchere = idcategorie_enchere;
    }

    public Categorie_enchere(String lib) {
        this.libelle = lib;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String lib) {
        this.libelle = lib;
    }

    public int getIdcategorie_enchere() {
        return idcategorie_enchere;
    }

    public void setIdcategorie_enchere(int id) {
        this.idcategorie_enchere = id;
    }
    
    public void update() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("update Categorie_enchere set libelle=? where idcategorie_enchere=?");
            stmt.setString(1,this.getLibelle());
            stmt.setInt(2, this.getIdcategorie_enchere());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorie_enchere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorie_enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            stmt.close();
            con.close();
        }
    }
    public void save() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("insert into categorie_enchere values (default,?)");
            stmt.setString(1,this.getLibelle());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Categorie_enchere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Categorie_enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            stmt.close();
            con.close();
        }
    }
    
}
