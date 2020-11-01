package com.dao;

import com.conexion.Conexion;
import com.modelo.Proyecto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Nombre de la clase: DaoProyecto
 * Fecha: 01/11/2020 
 * Derechos: Free Última
 * modificación:01/11/2020
 * @author luis-
 */
public class DaoProyecto extends Conexion {

    public String insertarProyecto(Proyecto proy) {
        try {
            this.conectar();
            String sql = "insert into Proyecto values (?,?,?,?,?,?)";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, proy.getIdProyecto());
            pre.setString(2, proy.getNombreProyecto());
            pre.setString(3, proy.getFechaInicio());
            pre.setString(4, proy.getTiempoEstimado());
            pre.setDouble(5, proy.getPrecioTotal());
            pre.setInt(6, proy.getIdUbicacion());
            
            pre.executeUpdate();
            return "Insertado en DAO";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en DAO insertar" + e.getMessage());
            return "No insertó DAO";
        } finally {
            this.desconectar();
        }
    }

    public String modificarProyecto(Proyecto proy) {
        try {
            this.conectar();
            String sql = "update Proyecto set nombreProyecto=?, fechaInicio=?, tiempoEstimado=?, precioTotal=?, idUbicacion=? "
                    + "where idProyecto=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setString(1, proy.getNombreProyecto());
            pre.setString(2, proy.getFechaInicio());
            pre.setString(3, proy.getTiempoEstimado());
            pre.setDouble(4, proy.getPrecioTotal());
            pre.setInt(5, proy.getIdUbicacion());
            pre.setInt(6, proy.getIdProyecto());
            
            pre.executeUpdate();
            return "Modificó en DAO";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en DAO modificar" + e.getMessage());
            return "No modificó DAO";
        } finally {
            this.desconectar();
        }
    }

    public String eliminarProyecto(Proyecto proy) {
        try {
            this.conectar();
            String sql = "delete from proyecto where idProyecto=?;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, proy.getIdProyecto());
            pre.executeUpdate();
            return "Modificó en DAO";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en DAO modificar" + e.getMessage());
            return "No modificó DAO";
        } finally {
            this.desconectar();
        }
    }

    public List<Proyecto> mostrarProyecto() {
        List<Proyecto> listaProyecto;
        listaProyecto = new ArrayList();
        ResultSet res;
        try {
            this.conectar();
            String sql = "select * from proyecto;";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            res = pre.executeQuery();
            while (res.next()) {
                Proyecto proy = new Proyecto();
                proy.setIdProyecto(res.getInt("idProyecto"));
                proy.setNombreProyecto(res.getString("nombreProyecto"));
                proy.setTiempoEstimado(res.getString("tiempoEstimado"));
                proy.setPrecioTotal(res.getDouble("precioTotal"));
                proy.setIdUbicacion(res.getInt("idUbicacion"));
                proy.setFechaInicio(res.getString("fechaInicio"));
                listaProyecto.add(proy);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en mostrar DAO");
        } finally {
            this.desconectar();
        }
        return listaProyecto;
    }

    public Proyecto getProyecto(int codigoProyecto) {
        Proyecto proye = new Proyecto();
        ResultSet res = null;
        try {
            this.conectar();
            String sql = "select * from proyecto where idProyecto=?";
            PreparedStatement pre = this.getCon().prepareStatement(sql);
            pre.setInt(1, codigoProyecto);
            res = pre.executeQuery();

            while (res.next()) {
                proye.setIdProyecto(res.getInt("idProyecto"));
                proye.setNombreProyecto(res.getString("nombreProyecto"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar datos getProyecto" + e.getMessage());
        } finally {
            this.desconectar();
        }
        return proye;
    }
}
