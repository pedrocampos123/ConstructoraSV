package com.vistas;

import com.dao.DaoProyecto;
import com.modelo.Proyecto;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Nombre de la clase: FrmProyecto 
 * Fecha: 01/11/2020 
 * Derechos: Última
 * modificación:01/11/2020
 * Version: 1.0
 * @author luis-
 */
public class FrmProyecto extends javax.swing.JInternalFrame {

    DaoProyecto daoProy = new DaoProyecto();
    Proyecto proy = new Proyecto();
    boolean agregando = false;
    boolean editando = false;

    public FrmProyecto() {
        initComponents();
        setResizable(false);
        mostrarDatos();
        /*botones();
        campos(false);
        this.btnNuevo.setEnabled(true);
        this.btnInsertar.setEnabled(false);
        this.btnModificar.setEnabled(false);
        this.btnLimpiar.setEnabled(false);
        this.btnEliminar.setEnabled(false);*/
    }

    public void mostrarDatos() {
        DefaultTableModel tabla;
        String encabezados[] = {"ID Proyecto", "Nombre del proyecto", "Fecha Inicio", "Tiempo estimado", 
            "Presupuesto total", "Ubicacion"};
        tabla = new DefaultTableModel(null, encabezados);
        Object datos[] = new Object[6];
        try {
            List lista;
            lista = daoProy.mostrarProyecto();
            for (int i = 0; i < lista.size(); i++) {
                proy = (Proyecto) lista.get(i);
                datos[0] = proy.getIdProyecto();
                datos[1] = proy.getNombreProyecto();
                datos[2] = proy.getFechaInicio();
                datos[3] = proy.getTiempoEstimado();
                datos[4] = proy.getPrecioTotal();
                datos[5] = proy.getIdUbicacion();
                tabla.addRow(datos);
            }
            this.TablaProyecto.setModel(tabla);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ERROR EN MOSTRAR FRM" + e.getMessage());
        }
    }

    public void botones() {
        if (agregando) {
            this.btnNuevo.setEnabled(false);
            this.btnInsertar.setEnabled(true);
            this.btnModificar.setEnabled(false);
            this.btnLimpiar.setEnabled(true);
            this.btnEliminar.setEnabled(false);
        } else if (editando) {
            this.btnNuevo.setEnabled(false);
            this.btnInsertar.setEnabled(false);
            this.btnModificar.setEnabled(false);
            this.btnLimpiar.setEnabled(true);
            this.btnEliminar.setEnabled(false);

        } else {
            this.btnNuevo.setEnabled(true);
            this.btnInsertar.setEnabled(false);
            this.btnModificar.setEnabled(true);
            this.btnLimpiar.setEnabled(false);
            this.btnEliminar.setEnabled(true);
        }
    }

    public void campos(boolean estado) {
        txtCodigo.setEnabled(false);
        txtProyectoName.setEnabled(estado);
        //txtFecha.setEnabled(estado);
        txtTiempo.setEnabled(estado);
        txtPrecio.setEnabled(estado);
    }

    public void limpiar() {
        this.txtCodigo.setText("");
        this.txtProyectoName.setText("");
        //this.txtFecha.setText("");
        this.txtTiempo.setText("");
        this.txtPrecio.setText("");
    }

    public void llenarTabla() {
        int fila = this.TablaProyecto.getSelectedRow();
        this.txtCodigo.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 0)));
        this.txtProyectoName.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 1)));
        this.txtFechaInicio.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 2)));
        this.txtTiempo.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 3)));
        this.txtPrecio.setText(String.valueOf(this.TablaProyecto.getValueAt(fila, 4)));
    }
    
    public void setearValores(){
        proy.setIdProyecto(0);
        proy.setNombreProyecto(this.txtProyectoName.getText());
        proy.setFechaInicio(this.txtFechaInicio.getText());
        proy.setTiempoEstimado(this.txtTiempo.getText());
        proy.setPrecioTotal(Double.parseDouble(this.txtPrecio.getText()));
        proy.setIdUbicacion(1);
    }

    public void insertar() {
        try {
            setearValores();
            daoProy.insertarProyecto(proy);
            JOptionPane.showMessageDialog(null, "Ha sido insertado correctamente");
            limpiar();
            mostrarDatos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error en FrmProyecto/insertar");
        }
    }

    public void modificar() {
        try {
            setearValores();
            proy.setIdProyecto(Integer.parseInt(this.txtCodigo.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar los datos?", "Modificar.", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                daoProy.modificarProyecto(proy);
                JOptionPane.showMessageDialog(null, "Proyecto modificado con éxito");
                limpiar();
                mostrarDatos();
            } else {
                limpiar();
                mostrarDatos();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar en FrmProyecto");
        }
    }

    public void eliminar() {
        try {
            proy.setIdProyecto(Integer.parseInt(this.txtCodigo.getText()));
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea eliminar este proyecto?", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                daoProy.eliminarProyecto(proy);
                mostrarDatos();
                limpiar();
            } else {
                mostrarDatos();
                limpiar();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar en FrmProyecto");
        }
    }

    public boolean validarCampos() {
        boolean validar = true;
        if (agregando) {
            if (this.txtProyectoName.getText().isEmpty()) {
                validar = false;
            }
            /*if (this.txtFecha.getText().isEmpty()) {
                validar = false;
            }*/
            if (this.txtTiempo.getText().isEmpty()) {
                validar = false;
            }
            if (this.txtPrecio.getText().isEmpty()) {
                validar = false;
            }
        } else if (editando) {
            if (this.txtProyectoName.getText().isEmpty()) {
                validar = false;
            }
            /*if (this.txtFecha.getText().isEmpty()) {
                validar = false;
            }*/
            if (this.txtTiempo.getText().isEmpty()) {
                validar = false;
            }
            if (this.txtPrecio.getText().isEmpty()) {
                validar = false;
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
        txtTiempo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProyecto = new javax.swing.JTable();
        btnNuevo = new javax.swing.JButton();
        btnInsertar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();

        setClosable(true);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gestión del proyecto.");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Código proyecto:");

        txtCodigo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Proyecto: ");

        txtProyectoName.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        txtTiempo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel6.setText("Tiempo estimado en meses:");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Presupuesto Total: ");

        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        txtPrecio.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

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

        btnNuevo.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNuevoMouseClicked(evt);
            }
        });

        btnInsertar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnInsertar.setText("Guardar");
        btnInsertar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInsertarMouseClicked(evt);
            }
        });

        btnEliminar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnModificar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnLimpiar.setText("Cancelar");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Fecha Inicio:");

        txtFechaInicio.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N

        btnMapa.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        btnMapa.setText("Mapa");
        btnMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMapaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                            .addComponent(txtProyectoName)))
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(txtFechaInicio)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(223, 223, 223)
                                .addComponent(btnMapa))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnNuevo)
                                        .addGap(31, 31, 31)
                                        .addComponent(btnInsertar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnModificar)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnEliminar)))
                                .addGap(18, 18, 18)
                                .addComponent(btnLimpiar))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(343, 343, 343)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtProyectoName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel6))
                            .addComponent(txtTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnMapa)))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnInsertar)
                    .addComponent(btnModificar)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar))
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaProyectoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProyectoMouseClicked
        llenarTabla();
    }//GEN-LAST:event_TablaProyectoMouseClicked

    private void btnInsertarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInsertarMouseClicked
        try {
            if (validarCampos()) {
                insertar();
                mostrarDatos();
                limpiar();
                /*botones();
                agregando = false;
                editando = false;*/
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnInsertarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        try {
            if (validarCampos()) {
                modificar();
                mostrarDatos();
                limpiar();
                botones();
                agregando = false;
                editando = false;

            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        eliminar();
        botones();
        mostrarDatos();
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
        editando = false;

    }//GEN-LAST:event_btnNuevoMouseClicked

    private void btnMapaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMapaMouseClicked
        open();
    }//GEN-LAST:event_btnMapaMouseClicked

    
    public void open(){
        final CreacionMapa sample = new CreacionMapa();
        JFrame frame = new JFrame("Mapa");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(sample, BorderLayout.CENTER);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProyecto;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInsertar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMapa;
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
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtProyectoName;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
