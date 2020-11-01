
package com.vistas;

import com.dao.DaoProyecto;
import com.modelo.Proyecto;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmProyecto
 *Fecha: 01/11/2020
 * Derechos:
 *Última modificación:01/11/2020
 * @author luis-
 */

public class FrmProyecto extends javax.swing.JInternalFrame {

    DaoProyecto daoProy = new DaoProyecto();
    Proyecto proy = new Proyecto();
    boolean agregando=false;
    boolean editando = false;
    
    
    public FrmProyecto() {
        initComponents();
        setResizable(false);
        mostrar();
        botones();
        campos(false);
        this.btnNuevo.setEnabled(true);
        this.btnInsertar.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnLimpiar.setEnabled(false);
        this.btnEliminar.setEnabled(false);
    }
    
     public void mostrar(){
        DefaultTableModel tabla;
        String encabezados[]={"ID Proyecto","Nombre del proyecto","Fecha de Inicio","Tiempo estimado","Presupuesto total"};
        tabla = new DefaultTableModel(null, encabezados);
        Object datos[]= new Object[5];
        try {
            List lista;
            lista = daoProy.mostrarProyecto();
            for(int i=0;i<lista.size();i++){
               proy = (Proyecto)lista.get(i);
               datos[0] = proy.getIdProyecto();
               datos[1]=proy.getNombreProyecto();
               datos[2]=proy.getFechaInicio();
               datos[3]= proy.getTiempoEstimado();
               datos[4]= proy.getPrecioTotal();
               tabla.addRow(datos);
            }
            this.TablaProyecto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR EN MOSTRAR FRM"+e.getMessage());
        }
    }
     
    
         public void botones()
    {
        if(agregando)
        {
            this.btnNuevo.setEnabled(false);
            this.btnInsertar.setEnabled(true);
            this.btnModificar.setEnabled(false);
            this.btnLimpiar.setEnabled(true);
            this.btnEliminar.setEnabled(false);
        }
        else if(editando)
        {
            this.btnNuevo.setEnabled(false);
            this.btnInsertar.setEnabled(false);
            this.btnModificar.setEnabled(false);
            this.btnLimpiar.setEnabled(true);
            this.btnEliminar.setEnabled(false);
            
        }
        else {
            this.btnNuevo.setEnabled(true);
            this.btnInsertar.setEnabled(false);
            this.btnModificar.setEnabled(true);
            this.btnLimpiar.setEnabled(false);
            this.btnEliminar.setEnabled(true);
        }
    }
    
      public void campos(boolean estado)
      {
          txtCodigo.setEnabled(false);
          txtProyectoName.setEnabled(estado);
          txtFecha.setEnabled(estado);
          txtTiempo.setEnabled(estado);
          txtPrecio.setEnabled(estado);
      }
      
         public void limpiar(){
        this.txtCodigo.setText("");
        this.txtProyectoName.setText("");
        this.txtFecha.setText("");
        this.txtTiempo.setText("");
        this.txtPrecio.setText("");
    }
         
         public void llenarTabla()
         {
         int fila = this.TablaProyecto.getSelectedRow();
         this.txtCodigo.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 0)));
         this.txtProyectoName.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 1)));
         this.txtFecha.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 2)));
         this.txtTiempo.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 3)));
         this.txtPrecio.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 4)));
         this.btnEliminar.setEnabled(true);
         this.btnModificar.setEnabled(true);
         }
    
    
    
    
    public void insertar(){
        try {          
            proy.setIdProyecto(Integer.parseInt(this.txtCodigo.getText()));
            proy.setNombreProyecto(this.txtProyectoName.getText());
            proy.setFechaInicio(this.txtFecha.getText());
            proy.setTiempoEstimado(this.txtTiempo.getText());
            proy.setPrecioTotal(Double.parseDouble(this.txtPrecio.getText()));
            daoProy.insertarProyecto(proy);
            JOptionPane.showMessageDialog(null, "Ha sido insertado correctamente");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error en FrmProyecto/insertar");
        }
        limpiar();
        mostrar();
    }
     
     
     public void modificar(){
         try {
             proy.setNombreProyecto(this.txtProyectoName.getText());
            proy.setFechaInicio(this.txtFecha.getText());
            proy.setTiempoEstimado(this.txtTiempo.getText());
            proy.setPrecioTotal(Double.parseDouble(this.txtPrecio.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea modificar los datos?","Modificar.",JOptionPane.YES_NO_OPTION);
            if(respuesta == JOptionPane.YES_OPTION){
                daoProy.modificarProyecto(proy);
                JOptionPane.showMessageDialog(null, "Proyecto modificado con éxito");
                mostrar();
                limpiar();
            }else{
                mostrar();
                limpiar();
            }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this, "Error al modificar en FrmProyecto");
         }
     }
     
     public void eliminar(){
         try {
             proy.setIdProyecto(Integer.parseInt(this.txtCodigo.getText()));
             int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este proyecto?","Eliminar",JOptionPane.YES_NO_OPTION);
             if(respuesta == JOptionPane.YES_OPTION){
                 daoProy.eliminarProyecto(proy);
                 mostrar();
                 limpiar();
             }else{
                 mostrar();
                 limpiar();
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(this,"Error al eliminar en FrmProyecto");
         }
     }
    
     public boolean validarCampos(){
         boolean validar=true;
         if(agregando){
             if(this.txtProyectoName.getText().isEmpty())
             {
                 validar=false;
             }
             if(this.txtFecha.getText().isEmpty())
             {
                 validar=false;
             }
             if(this.txtTiempo.getText().isEmpty())
             {
                 validar = false;
             }
             if(this.txtPrecio.getText().isEmpty())
             {
                 validar=false;
             }
         }
         else if(editando){
             if(this.txtProyectoName.getText().isEmpty())
             {
                 validar=false;
             }
             if(this.txtFecha.getText().isEmpty())
             {
                 validar=false;
             }
             if(this.txtTiempo.getText().isEmpty())
             {
                 validar = false;
             }
             if(this.txtPrecio.getText().isEmpty())
             {
                 validar=false;
             }
         }      
         return validar;
     }
  

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtProyectoName = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTiempo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProyecto = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Gestión del proyecto.");

        jLabel2.setText("Código proyecto:");

        jLabel4.setText("Proyecto: ");

        jLabel5.setText("Fecha de inicio:");

        jLabel6.setText("Tiempo estimado en meses:");

        jLabel7.setText("Presupuesto Total: ");

        TablaProyecto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaProyecto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProyectoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProyecto);

        btnNuevo.setText("Nuevo");
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoMouseClicked(evt);
            }
        });

        btnInsertar.setText("Guardar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnLimpiar.setText("Cancelar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)))
                            .addComponent(jLabel6)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtProyectoName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1)))
                .addGap(134, 342, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLimpiar)
                                .addGap(164, 164, 164))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNuevo)
                                    .addComponent(btnEliminar))
                                .addGap(77, 77, 77)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnModificar)
                                    .addComponent(btnInsertar))
                                .addGap(84, 84, 84))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(149, 149, 149))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnNuevo)
                                            .addComponent(btnInsertar))
                                        .addGap(70, 70, 70))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel2)
                                                .addGap(60, 60, 60)
                                                .addComponent(jLabel4)
                                                .addGap(69, 69, 69)
                                                .addComponent(jLabel5))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(60, 60, 60)
                                                        .addComponent(txtProyectoName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(55, 55, 55)
                                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnModificar)
                                .addGap(60, 60, 60)))
                        .addComponent(btnLimpiar)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProyectoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_TablaProyectoMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        try {
            if(validarCampos())
            {
                insertar();
                mostrar();
                limpiar();
                botones();
                agregando = false;
                editando = false;
            }else{
                 JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.","ERROR",JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {        
        }
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        try {
            if(validarCampos()){
                modificar();
                mostrar();
                limpiar();
                botones();
                agregando = false;
                editando= false;
               
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
        botones();
        mostrar();
        limpiar();
        agregando = false;
        editando = false; 
        
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        limpiar();
         botones();
        agregando = false;
        editando = false;
        campos(false);
       
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void btnNuevoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevoMouseClicked
        campos(true);
         botones();
        agregando = true;
        editando =false;
       
    }//GEN-LAST:event_btnNuevoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProyecto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JFormattedTextField txtFecha;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtProyectoName;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
