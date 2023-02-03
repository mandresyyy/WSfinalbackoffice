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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.demo.utilitaire.Connexion;
import java.sql.Connection;

/**
 *
 * @author Mandresy
 */
public class Admin extends BaseModel{
    String email;
    String mdp;

    public Admin() {
    }

    public Admin(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public Boolean login() throws SQLException{
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Connection con = null;
        Boolean v=false;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("Select * from administrateur where email=? and motdepasse=? ");
            stmt.setString(1,this.email);
            stmt.setString(2,this.mdp);
            System.out.print(stmt.toString());
            rs=stmt.executeQuery();
            if(rs!=null){
                
                while(rs.next()){
                  this.setId(rs.getInt("idadmin"));
                  if(this.id!=0){v=true;}
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return v;
    }
    public ArrayList<Rechargement> lister_rechargement() throws SQLException{
        PreparedStatement stmt=null;
        ResultSet rs=null;
        Connection con = null;
        ArrayList<Rechargement> liste=new ArrayList<>();
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select rechargement.*,utilisateur.nom,utilisateur.prenom,utilisateur.solde from rechargement join utilisateur on rechargement.idutilisateur=utilisateur.idutilisateur where etat_validation=0");
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Utilisateur u=new Utilisateur(rs.getInt("idutilisateur"),rs.getString("nom"),rs.getString("prenom"),rs.getDouble("solde"));
                    Rechargement r=new Rechargement(rs.getInt("idrechargement"),u,rs.getDouble("montant"),rs.getDate("date_rechargement").toLocalDate(),rs.getInt("etat_validation"));
                    liste.add(r);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return liste;
    }
    public void valider_demande(Rechargement r) throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        try {
            con = new Connexion().getCon();
            con.setAutoCommit(false);
            stmt=con.prepareStatement("update rechargement set etat_validation=1 where idrechargement=?");
            stmt.setInt(1, r.getId());
            System.out.print(stmt.toString());
            stmt.executeUpdate();
            stmt=con.prepareStatement("update utilisateur set solde=? where idutilisateur=?");
            Double v=r.getUtil().getSolde()+r.getMontant();
            System.out.print(v+"TYYYYYYYYYY");
            stmt.setDouble(1,v);
            
            stmt.setInt(2,r.getUtil().idutilisateur);
             System.out.print(stmt.toString());
            stmt.executeUpdate();
            
            con.commit();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            con.close();
        }
    }
     public double getCommmission() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        double f=0;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from commission_cout order by idcommission_cout desc limit 1");
            
            rs=stmt.executeQuery();
            while(rs.next()){
                f=rs.getDouble("pourcentage");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return f;
    }
     public double getCommmission(Connection con) throws SQLException{
        PreparedStatement stmt=null;
        // Connection con = null;
        ResultSet rs=null;
        double f=0;
        try {
            // con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from commission_cout order by idcommission_cout desc limit 1");
            
            rs=stmt.executeQuery();
            while(rs.next()){
                f=rs.getDouble("pourcentage");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            // con.close();
        }
        return f;
    }
    public double getCommmission(int mois) throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        double f=0;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from commission_cout where extract(month from date)=? order by idcommission_cout desc limit 1");
            stmt.setInt(1, mois);
            rs=stmt.executeQuery();
            while(rs.next()){
                f=rs.getDouble("pourcentage");
            }
            if(f==0){
                this.getCommmission(con);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return f;
    }
     public double getCommmission(int mois ,Connection con) throws SQLException{
        PreparedStatement stmt=null;
        // Connection con = null;
        ResultSet rs=null;
        double f=0;
        try {
            // con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from commission_cout where extract(month from date)=? order by idcommission_cout desc limit 1");
            stmt.setInt(1, mois);
            rs=stmt.executeQuery();
            while(rs.next()){
                f=rs.getDouble("pourcentage");
            }
            if(f==0){
                this.getCommmission(con);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            // con.close();
        }
        return f;
    }
    public void update_com(double m) throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("insert into commission_cout values(default,?,default)");
            stmt.setDouble(1,m);
            stmt.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            con.close();
        }
    }
     public Double getDure() throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        ResultSet rs=null;
        Double f=0.0;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("select * from duree order by idduree desc limit 1");
            rs=stmt.executeQuery();
            while(rs.next()){
                f=rs.getDouble("valeur");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            rs.close();
            con.close();
        }
        return f;
    }
    public void update_duree(double duree) throws SQLException{
        PreparedStatement stmt=null;
        Connection con = null;
        try {
            con = new Connexion().getCon();
            stmt=con.prepareStatement("insert into duree values(default,?,default)");
            stmt.setDouble(1,duree);
            stmt.executeUpdate();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            stmt.close();
            con.close();
        }
    }

    public int[] get_ben_par_mois() throws SQLException{
        int [] v=new int[12];
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            
            con = new Connexion().getCon();
            for(int i=1;i<=12;i++){
                double com=this.getCommmission(i,con);
            stmt=con.prepareStatement("select sum(montant) as somme from prxfinal_par_enchere where extract(month from debut )=?");
            stmt.setInt(1,i);
            rs=stmt.executeQuery();
                while(rs.next()){
                    v[i-1]=(int) (rs.getInt("somme")*com/100);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
    public int get_ben_ce_mois() throws SQLException{
        int  v=0;
           LocalDate ajd=LocalDate.now();
           int mois=ajd.getMonthValue();
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            con = new Connexion().getCon();
            double com=this.getCommmission(mois,con);
            stmt=con.prepareStatement("select sum(montant) as somme from prxfinal_par_enchere where extract(month from debut )=?");
            stmt.setInt(1,mois);
            System.out.print(stmt.toString());
            rs=stmt.executeQuery();
                while(rs.next()){
                    v=(int) (rs.getInt("somme")*com/100);
                }
                   
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
    public int get_ben_ce_mois( Connection con) throws SQLException{
        int  v=0;
           LocalDate ajd=LocalDate.now();
           int mois=ajd.getMonthValue();
         PreparedStatement stmt=null;
        //  Connection con = null;
         ResultSet rs=null;
        try {
            // con = new Connexion().getCon();
            double com=this.getCommmission(mois,con);
            stmt=con.prepareStatement("select sum(montant) as somme from prxfinal_par_enchere where extract(month from debut )=?");
            stmt.setInt(1,mois);
            System.out.print(stmt.toString());
            rs=stmt.executeQuery();
                while(rs.next()){
                    v=(int) (rs.getInt("somme")*com/100);
                }
                   
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            // con.close();
        }
        return v;
    }
    public int bentotal(){
        int v=0;
        int[] tab;
        try {
            tab = this.get_ben_par_mois();
            for(int i=0;i<tab.length;i++){
                v=v+tab[i];
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
            return v;
    }
    public int get_ben_total() throws SQLException{
        int v=0;
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select sum(montant) as somme from prxfinal_par_enchere");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                    v=rs.getInt("somme");
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
    public int get_ben_total( Connection con) throws SQLException{
        int v=0;
         PreparedStatement stmt=null;
        //  Connection con = null;
         ResultSet rs=null;
        try {
            // con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select sum(montant) as somme from prxfinal_par_enchere");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                    v=rs.getInt("somme");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            // con.close();
        }
        return v;
    }
     public Categorie_enchere categ_plus_vendu() throws SQLException{
        Categorie_enchere v=new Categorie_enchere();
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select * from vente_parcateg");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                 v.setIdcategorie_enchere(rs.getInt("idcategorie_enchere"));
                 v.setLibelle(rs.getString("libelle"));
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
       public int getnbdemande() throws SQLException{
        int v=0;
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select count(*)as nb from rechargement where etat_validation=0");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                 v=rs.getInt("nb");
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
    public int getnbdemande(  Connection con) throws SQLException{
        int v=0;
         PreparedStatement stmt=null;
        //  Connection con = null;
         ResultSet rs=null;
        try {
            // con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select count(*)as nb from rechargement where etat_validation=0");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                 v=rs.getInt("nb");
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            // con.close();
        }
        return v;
    }
        public ArrayList<Object[]> enchereparcateg() throws SQLException{
        ArrayList<Object[]> v=new ArrayList<>();
         PreparedStatement stmt=null;
         Connection con = null;
         ResultSet rs=null;
        try {
            con = new Connexion().getCon();
            
            stmt=con.prepareStatement("select count(*) as nb,mettre_enchere.idcategorie_enchere,categorie_enchere.libelle from mettre_enchere join categorie_enchere on mettre_enchere.idcategorie_enchere=categorie_enchere.idcategorie_enchere group by mettre_enchere.idcategorie_enchere,categorie_enchere.libelle");
            
            rs=stmt.executeQuery();
                while(rs.next()){
                    Object[] obj=new Object[2];
                    obj[0]=rs.getInt("nb");
                    obj[1]=rs.getString("libelle");
                    v.add(obj);
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
         finally{
            rs.close();
            stmt.close();
            con.close();
        }
        return v;
    }
    public int[] all() throws SQLException{
        Connection con = null;
        int[] l=new int[3];
        try {
            con = new Connexion().getCon();
            l[0]=this.get_ben_ce_mois(con);
            l[1]=this.get_ben_total(con);
            l[2]=this.getnbdemande(con);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            con.close();
        }
        return l;
    }
    
}
