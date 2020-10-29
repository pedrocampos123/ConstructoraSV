/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.conexion.Conexion;
import com.modelo.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoCargo
 * Fecha: 26/10/2020
 * CopyRigth: Pedro Campos 
 * Version: 1.0
 * @author pedro
 */
public class DaoRol extends Conexion{
    public List<Rol> mostrarRol(){
        List<Rol>listaRol;
        listaRol = new ArrayList();
        ResultSet res;
        try {
            this.conectar();
            String sql ="select * from rol";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res = pre.executeQuery();
            
            while (res.next()) {                
                Rol rol = new Rol();
                rol.setIdRol(res.getInt("idRol"));
                rol.setTipo(res.getString("tipo"));
                listaRol.add(rol);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Mostrar" + e.getMessage());
        }finally{
            this.desconectar();
        }
        return listaRol;
    }
    
}
