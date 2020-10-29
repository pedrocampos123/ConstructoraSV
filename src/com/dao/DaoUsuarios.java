/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.conexion.Conexion;
import com.modelo.Usuarios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoUsuarios
 * Fecha: 26/10/2020
 * CopyRigth: Pedro Campos 
 * Version: 1.0
 * @author pedro
 */
public class DaoUsuarios extends Conexion{
    
    public Usuarios validarUsuario(String usuario, String password, int cargo){
        Usuarios user = new Usuarios();
        ResultSet res;
        try {
            this.conectar();
            String sql ="select * from usuario where nombreUsuario=? and password=? and idRol=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, usuario);
            pre.setString(2, password);
            pre.setInt(3, cargo);
            res = pre.executeQuery();
            
            while (res.next()) {                
                user.setIdUsuario(res.getInt("idUsuario"));
                user.setNombreUsuario(res.getString("nombreUsuario"));
                user.setPassword(res.getString("password"));
                user.setIdRol(res.getInt("idRol"));
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar los registros", "Error",JOptionPane.ERROR_MESSAGE);
        }finally{
            this.desconectar();
        }
        return user;
    }
}
