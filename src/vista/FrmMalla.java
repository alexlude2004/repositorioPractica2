
package vista;

import controlador.MallaControllerListas;
import controlador.TDA.listas.LinkedList;
import java.awt.event.ItemEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import vista.listas.tablas.ModeloTablaMallaListas;

/**
 *
 * @author alexg
 */
public class FrmMalla extends javax.swing.JFrame {

    private MallaControllerListas mcl = new MallaControllerListas();
    private ModeloTablaMallaListas mtml = new ModeloTablaMallaListas();
    private Boolean estado;
        
    /**
     * Creates new form FrmCurso
     */
    public FrmMalla() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
        
    }

    private void ordenar() {
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        Integer ascdesc  = cbxAscDesc.getSelectedIndex();
        try {
            mtml.setMallas(mcl.quickSort(ascdesc, criterio, mtml.getMallas()));
            tblTabla.setModel(mtml);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }      

    private void buscar() {
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        try {
            if (criterio.equalsIgnoreCase("nombre")) {
                mtml.setMallas(mcl.buscarNombre(mcl.getMallas(), criterio, txtBusqueda.getText()));
            }
            else if (criterio.equalsIgnoreCase("modalidad")) {
                mtml.setMallas(mcl.buscarModalidad(mcl.getMallas(), criterio, txtBusqueda.getText()));
            }
            else if (criterio.equalsIgnoreCase("fecha")) {
                String strDate = txtBusquedaFecha.getText();
                SimpleDateFormat formatter = new SimpleDateFormat("dd / MM / yy");
                Date fecha = formatter.parse(strDate);
                mtml.setMallas(mcl.buscarFecha(mcl.getMallas(), "fecha_Creacion", fecha));
            }
            
            tblTabla.setModel(mtml);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }      
    
    private void limpiar() {
        txtNombre.setText("");
        txtBusqueda.setText("");
        txtCodResolucion.setText("COD_R_" + mcl.generatedCode());
        txtCodResolucion.setEnabled(false);
        txtFechaCreacion.setText(new  SimpleDateFormat("dd / MM / yy").format(new Date()));
        txtBusquedaFecha.setText("dd / MM / yy");
        cbxCriterio.setSelectedItem("NOMBRE");
        checkBoxVigente.setSelected(false);
        checkBoxDeshabilitado.setSelected(false);
        mcl.setMalla(null);
        mcl.setMallas(new LinkedList<>());
        cargarTabla();
        mcl.setMalla(null);
        mcl.setIndex(-1);
        try {
            txtBusqueda.setVisible(true);            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private void cargarTabla() {
        mtml.setMallas(mcl.getMallas());
        tblTabla.setModel(mtml);
        tblTabla.updateUI();
    }
    
    private Boolean validar() {
        return !txtNombre.getText().trim().isEmpty() &&
                    !checkBoxVigente.getText().trim().isEmpty() &&
                    !checkBoxDeshabilitado.getText().trim().isEmpty();
    }        
    
    private void guardar() {
        if (validar()) {
            try {
                mcl.getMalla().setNombre(txtNombre.getText());
                mcl.getMalla().setCod_resolucion(txtCodResolucion.getText());
                mcl.getMalla().setModalidad(cbxModalidad.getSelectedItem().toString());
                mcl.getMalla().setFecha_Creacion(new SimpleDateFormat("dd / MM / yy").parse(txtFechaCreacion.getText()));
                if (checkBoxVigente.isSelected()) {
                    mcl.getMalla().setEstado(estado);
                } else {
                    mcl.getMalla().setEstado(estado);
                }
               
                
                if (mcl.getMalla().getId() == null) {
                    if (mcl.save()) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha guardado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        mcl.setMalla(null); 
                    } else {
                    JOptionPane.showMessageDialog(null, 
                            "No se ha podido guardar correctamente", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } 
            } else {
                    if (mcl.update(mcl.getIndex())) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha editado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        mcl.setMalla(null); 
                    } else {
                    JOptionPane.showMessageDialog(null, 
                            "No se ha podido editar correctamente", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }  
               }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);                   
                } 
        }
    }    
    
    
    private void cargarVista() {
        mcl.setIndex(tblTabla.getSelectedRow());
        if (mcl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                mcl.setMalla(mtml.getMallas().get(mcl.getIndex()));
                txtNombre.setText(mcl.getMalla().getNombre());
                txtCodResolucion.setText(mcl.getMalla().getCod_resolucion());
                txtFechaCreacion.setText(new SimpleDateFormat("dd / MM / yy").format(mcl.getMalla().getFecha_Creacion()));
                cbxModalidad.setSelectedItem(mcl.getMalla().getModalidad());   
                checkBoxVigente.setSelected(mcl.getMalla().getEstado().equals(true));
                checkBoxDeshabilitado.setSelected(mcl.getMalla().getEstado().equals(false));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                        e.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
        
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCodResolucion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtFechaCreacion = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxModalidad = new javax.swing.JComboBox<>();
        checkBoxVigente = new javax.swing.JCheckBox();
        checkBoxDeshabilitado = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxAscDesc = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        txtBusquedaFecha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion de la Malla", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnGuardar, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCancelar, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Nombre de la malla:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Codigo de Resolucion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel7, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Modalidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel10, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(txtNombre, gridBagConstraints);

        txtCodResolucion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        jPanel3.add(txtCodResolucion, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Fecha de Creacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel11, gridBagConstraints);

        txtFechaCreacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(txtFechaCreacion, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Estado:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel12, gridBagConstraints);

        cbxModalidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presencial", "En Linea", "Hibrida" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxModalidad, gridBagConstraints);

        checkBoxVigente.setText("Vigente");
        checkBoxVigente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxVigenteItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        jPanel3.add(checkBoxVigente, gridBagConstraints);

        checkBoxDeshabilitado.setText("Deshabilitado");
        checkBoxDeshabilitado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxDeshabilitadoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 4;
        jPanel3.add(checkBoxDeshabilitado, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Lista de Mallas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setLayout(new java.awt.GridBagLayout());

        tblTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblTabla);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 300;
        gridBagConstraints.ipady = -200;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jScrollPane2, gridBagConstraints);

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnSeleccionar, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Texto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel8, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(txtBusqueda, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Criterio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel9, gridBagConstraints);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "NOMBRE", "MODALIDAD", "FECHA" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxCriterio, gridBagConstraints);

        cbxAscDesc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDETE" }));
        cbxAscDesc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxAscDescItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxAscDesc, gridBagConstraints);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.ipadx = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnBuscar, gridBagConstraints);

        txtBusquedaFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBusquedaFecha.setText("dd / MM / yy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(txtBusquedaFecha, gridBagConstraints);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1053, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed
        cargarVista();
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if (evt.getItem().toString().equalsIgnoreCase("NOMBRE")) {
            txtBusqueda.setVisible(true);
            txtBusquedaFecha.setVisible(false);
        } else if (evt.getItem().toString().equalsIgnoreCase("MODALIDAD")) {
            txtBusqueda.setVisible(true);
            txtBusquedaFecha.setVisible(false);            
        } else if (evt.getItem().toString().equalsIgnoreCase("FECHA")) {
            txtBusqueda.setVisible(false);
            txtBusquedaFecha.setVisible(true);            
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxAscDescItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAscDescItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxAscDescItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void checkBoxVigenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxVigenteItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            checkBoxDeshabilitado.setSelected(false);
            estado = true;
        }
    }//GEN-LAST:event_checkBoxVigenteItemStateChanged

    private void checkBoxDeshabilitadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxDeshabilitadoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            checkBoxVigente.setSelected(false);
            estado = false;
        }
    }//GEN-LAST:event_checkBoxDeshabilitadoItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMalla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cbxAscDesc;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxModalidad;
    private javax.swing.JCheckBox checkBoxDeshabilitado;
    private javax.swing.JCheckBox checkBoxVigente;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtBusquedaFecha;
    private javax.swing.JTextField txtCodResolucion;
    private javax.swing.JTextField txtFechaCreacion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
