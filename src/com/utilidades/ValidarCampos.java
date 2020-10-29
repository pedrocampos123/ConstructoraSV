/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utilidades;

import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 * Nombre de la clase: ComboItem
 * Fecha: 26/10/2020
 * CopyRigth: Pedro Campos 
 * Version: 1.0
 * @author pedro
 */
public class ValidarCampos {
       
    private String nivelAcceso;

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
    
    
    public ValidarCampos() {
    }
            
    /**
     * Validar solo numeros
     */
    public void numbersOnly(KeyEvent evt){
        if(!Character.isDigit(evt.getKeyChar()))
            evt.consume();
    }
    
    /**
     * Validar solo palabras
     */
    
    public void wordsOnly(KeyEvent evt){
        if(!Character.isLetter(evt.getKeyChar()))
            evt.consume();
    }
    
    /**
     * Validar solo numeros y un punto
     */
    public void numbersAndPoint(KeyEvent evt, JTextField textField){
        if(!Character.isDigit(evt.getKeyChar()) && evt.getKeyChar() != '.'){
            evt.consume();
        }
        if(evt.getKeyChar() == '.' && textField.getText().contains(".")){
            evt.consume();
        }
    }
}
