/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: Conexion
 * Fecha: 26/10/2020
 * CopyRigth: Pedro Campos 
 * Version: 1.0
 * @author pedro
 */
public class Conexion {
    
    private Connection con = null;

    public Connection getCon() {
        return con;
    }

    public boolean conectar() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/constructoraSV","root","");

            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lamentablemnte no se ha podido conectar al servidor", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public boolean desconectar() {
        try {
            if (con != null) {
                if (con.isClosed() == false) {
                    con.close();
                }
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lamentablemente no se ha podido desconectar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
