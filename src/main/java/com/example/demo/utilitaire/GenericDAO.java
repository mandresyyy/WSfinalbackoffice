/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilitaire;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mandr
 */
public class GenericDAO{
    Object objet;
    Connection con;
    public GenericDAO(Object objet) throws SQLException {
        this.objet = objet;
        try {
            Class.forName("org.postgresql.Driver");
            this.con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/enchere?user=test&password=qwerty&ssl=false");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Object getObjet() {
        return objet;
    }

    public void setObjet(Object objet) {
        this.objet = objet;
    }
    
    public Method trouver_methode(String setters){
        Method m=null;
        Method[] mth=objet.getClass().getDeclaredMethods();
            for(int i=0;i<mth.length;i++){
                if(mth[i].getName().equalsIgnoreCase(setters)){ m=mth[i];}
            }
        
        return m;
    }
     public Method trouver_methGet(String field){
        Method m=null;
        Method[]meth=this.getClass().getDeclaredMethods();
        String ref="get"+field;
        
        for(int i=0;i<meth.length;i++){
            if(meth[i].getName().equalsIgnoreCase(ref)){
                m=meth[i];
                break;
            }
        
        }
        return m;
    }
    
    public ArrayList<Object> Select() {
        ArrayList<Object> v=new ArrayList<>();
       String table=this.objet.getClass().getSimpleName();
       String sql="select * from "+table;
//       System.out.print(sql);
       PreparedStatement stmt=null;
       ResultSet rs=null;
       Field[] att=objet.getClass().getDeclaredFields();
        try {
            stmt=this.con.prepareStatement(sql);
            rs=stmt.executeQuery();
            if(rs!=null){
                while(rs.next()){
                    Object o=this.objet.getClass().getConstructor().newInstance();
                    for(int i=0;i<att.length;i++){
                        this.trouver_methode("Set"+att[i].getName()).invoke(o,rs.getObject(att[i].getName()));
                    }
                    v.add(o);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       finally{ try {
           rs.close();
            stmt.close();
           con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
}
       return v;
    }

    public void Save() {
        String table=objet.getClass().getSimpleName();
        String sql="insert into "+table+"(";
        Field[] field=objet.getClass().getDeclaredFields();
        ArrayList<Field> ff=new ArrayList<>();
        for(int i=0;i<field.length;i++){
            Method tempM=this.trouver_methode("Get"+field[i].getName());
            if(tempM.getReturnType()==int.class){
                try {
                    if((int)this.trouver_methode("Get"+field[i].getName()).invoke(objet)!=0){ff.add(field[i]);}
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(tempM.getReturnType()==String.class){
                try {
                    if(!this.trouver_methode("Get"+field[i].getName()).invoke(objet).toString().equalsIgnoreCase("")){ff.add(field[i]);}
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                    Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        sql=sql+field[0].getName();
            for(int i=1;i<ff.size();i++){
                sql=sql+","+ff.get(i).getName();
            }
        sql=sql+") values(";
       String sql2="?";
       for(int i=1;i<ff.size();i++){
           sql2=sql2+",?";
       }
       sql2=sql2+")";
//      System.out.print(sql+sql2);
      sql=sql+sql2;
     
        PreparedStatement stmt=null;
        try {
            stmt=con.prepareStatement(sql);
            for(int i=0;i<ff.size();i++){
                stmt.setObject(i+1,this.trouver_methode("Get"+ff.get(i).getName()).invoke(objet));
            }
            System.out.print(stmt.toString());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(GenericDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
