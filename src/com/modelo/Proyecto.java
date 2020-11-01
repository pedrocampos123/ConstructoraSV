
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
    private String tiempoEstimado;
    private String fechaInicio;
    private double precioTotal;
    private int idUbicacion;

    public Proyecto() {
    }

    public Proyecto(int idProyecto, String nombreProyecto, String tiempoEstimado, String fechaInicio, double precioTotal, int idUbicacion) {
        this.idProyecto = idProyecto;
        this.nombreProyecto = nombreProyecto;
        this.tiempoEstimado = tiempoEstimado;
        this.fechaInicio = fechaInicio;
        this.precioTotal = precioTotal;
        this.idUbicacion = idUbicacion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }    

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
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
