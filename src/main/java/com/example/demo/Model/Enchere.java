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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.demo.utilitaire.Connexion;

/**
 *
 * @author Mandresy
 */
public class Enchere extends BaseModel{
    Utilisateur util;
    Categorie_enchere categ;
    Timestamp debut;
    String nom;
    double misemin;
    int statut;
    String photo;
    Utilisateur gagnant;
    Double price;

    public Enchere(Utilisateur util, Categorie_enchere categ, Timestamp debut, String nom, double misemin, int statut) {
        this.util = util;
        this.categ = categ;
        this.debut = debut;
        this.nom = nom;
        this.misemin = misemin;
        this.statut = statut;
    }
    
    public Enchere(int id) {this.id=id;
    }
    public Enchere() {
    }
    public Utilisateur getUtil() {
        return util;
    }

    public void setUtil(Utilisateur util) {
        this.util = util;
    }

    public Categorie_enchere getCateg() {
        return categ;
    }

    public void setCateg(Categorie_enchere categ) {
        this.categ = categ;
    }

    public Timestamp getDebut() {
        return debut;
    }

    public void setDebut(Timestamp debut) {
        this.debut = debut;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getMisemin() {
        return misemin;
    }

    public void setMisemin(double misemin) {
        this.misemin = misemin;
    }

    public int getStatut() {
        return statut;
    }

    public void setStatut(int statut) {
        this.statut = statut;
    }
    public Utilisateur getGagnant() {
        return gagnant;
    }

    public void setGagnant(Utilisateur gagnant) {
        this.gagnant = gagnant;
    }
    
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public void setnaresy(Connection con) throws SQLException{
        PreparedStatement stmt=null;
        // Connection con = null;
        ResultSet rs=null;
        try {
            // con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from infohist where idmettre_enchere=? limit 1");
            stmt.setInt(1,this.id);
            System.out.print(stmt.toString());
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Utilisateur u=new Utilisateur(rs.getInt("idutilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getDouble("solde"));
                  
                    this.gagnant=u;
                   this.price=rs.getDouble("montant");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
           
        }
        
    }
    public ArrayList<Enchere> historique() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        ArrayList<Enchere> liste=new ArrayList<>();
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from historique where statut=1");
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Utilisateur u=new Utilisateur(rs.getInt("idutilisateur"),rs.getString("nomutil"),rs.getString("prenomutil"),rs.getDouble("solde"));
                    Categorie_enchere ce=new Categorie_enchere(rs.getInt("idcategorie_enchere"),rs.getString("libelle"));
                    Enchere e=new Enchere(u,ce,rs.getTimestamp("debut"),rs.getString("nom"),rs.getDouble("mise_minimale"),rs.getInt("statut"));
                    e.setId(rs.getInt("idmettre_enchere"));
                    e.setnaresy(con);
                    liste.add(e);
                   
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return liste;
    }
    public ArrayList<Enchere> encours() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        ArrayList<Enchere> liste=new ArrayList<>();
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from historique where statut=0");
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Utilisateur u=new Utilisateur(rs.getInt("idutilisateur"),rs.getString("nomutil"),rs.getString("prenomutil"),rs.getDouble("solde"));
                    Categorie_enchere ce=new Categorie_enchere(rs.getInt("idcategorie_enchere"),rs.getString("libelle"));
                    Enchere e=new Enchere(u,ce,rs.getTimestamp("debut"),rs.getString("nom"),rs.getDouble("mise_minimale"),rs.getInt("statut"));
                    e.setId(rs.getInt("idmettre_enchere"));
                    liste.add(e);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return liste;
    }
    
    public ArrayList<Mouvement_enchere> liste_mouv() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        ArrayList<Mouvement_enchere> liste=new ArrayList<>();
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select *from mouvement where idmettre_enchere = ?");
            stmt.setInt(1,this.id);
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Utilisateur u=new Utilisateur(rs.getInt("idutilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getDouble("solde"));
                    Mouvement_enchere me=new Mouvement_enchere(rs.getInt("idencherir"),u,rs.getDouble("montant"),this);
                    liste.add(me);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Enchere.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        
        return liste;
    }
}
