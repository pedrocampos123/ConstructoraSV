
package com.modelo;

/**
 * Nombre de la clase: Proyecto
 *Fecha: 01/11/2020
 * Derechos: Free
 *Última modificación:01/11/2020
 * @author luis-
 */
public class Proyecto {
    private int idProyecto;
    private String nombreProyecto;
    private String fechaInicio;
    private String tiempoEstimado;
    private double precioTotal;

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String nombreProyecto, String fechaInicio, String tiempoEstimado, double precioTotal) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.fechaInicio = fechaInicio;
        this.tiempoEstimado = tiempoEstimado;
        this.precioTotal = precioTotal;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }
    
    
}
