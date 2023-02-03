/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mahenintsoa
 */
public class Connexion {

    Connection con;

    public Connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            // con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/enchere?user=test&password=qwerty&ssl=false");
             con = DriverManager.getConnection("jdbc:postgresql://containers-us-west-196.railway.app:6815/railway?user=postgres&password=MKyy3iS7wOBvOBDO4vxo");
            if (con == null) {
                System.out.println("error");
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }

    public Connection getCon() {
        return con;
    }

    public void close() {
        try {
            getCon().close();
        } catch (SQLException e) {
        }
    }

}
