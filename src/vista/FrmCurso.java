
package vista;

import controlador.CursoControllerListas;
import controlador.MallaControllerListas;
import controlador.TDA.listas.LinkedList;
import javax.swing.JOptionPane;
import modelo.Malla;
import vista.listas.tablas.ModeloTablaCursoListas;
import vista.listas.util.UtilVista;

/**
 *
 * @author alexg
 */
public class FrmCurso extends javax.swing.JFrame {

    private CursoControllerListas ccl = new CursoControllerListas();
    private ModeloTablaCursoListas mtcl = new ModeloTablaCursoListas();
        
    /**
     * Creates new form FrmCurso
     */
    public FrmCurso() {
        initComponents();
        this.setLocationRelativeTo(null);
        limpiar();
    }

    private void ordenar() {
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        Integer ascdesc  = cbxAscDesc.getSelectedIndex();
        try {
            mtcl.setCursos(ccl.quickSort(ascdesc, criterio, mtcl.getCursos()));
            tblTabla.setModel(mtcl);
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
            if (criterio.equalsIgnoreCase("ciclo")) {
                Integer ciclo = Integer.parseInt(txtBusqueda.getText());
                mtcl.setCursos(ccl.buscarCiclo(ccl.getCursos(), criterio, ciclo));
            }
            else if (criterio.equalsIgnoreCase("paralelo")) {
                String paralelo = txtBusqueda.getText().toUpperCase();
                mtcl.setCursos(ccl.buscarParalelo(ccl.getCursos(), criterio, paralelo));
            }
            else if (criterio.equalsIgnoreCase("malla")) {
                mtcl.setCursos(ccl.buscarMalla(ccl.getCursos(), "id_malla", UtilVista.getComboMallas(cbxMallaB)));
            }
            
            tblTabla.setModel(mtcl);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }      
    
    private void limpiar() {
        txtBusqueda.setText("");
        txtModalidad.setEnabled(false);
        ccl.setCurso(null);
        ccl.setCursos(new LinkedList<>());
        cargarTabla();
        ccl.setCurso(null);
        ccl.setIndex(-1);
        try {
            UtilVista.cargarMalla(cbxMalla);
            UtilVista.cargarMalla(cbxMallaB);
            txtBusqueda.setVisible(true);   
            cbxMallaB.setVisible(false);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private void cargarTabla() {
        mtcl.setCursos(ccl.getCursos());
        tblTabla.setModel(mtcl);
        tblTabla.updateUI();
    }
    
    private void guardar() {
        try {
            ccl.getCurso().setId_malla(UtilVista.getComboMallas(cbxMalla).getId());
            ccl.getCurso().setCiclo(cbxCiclo.getSelectedIndex() + 1);
            ccl.getCurso().setParalelo(cbxParalelo.getSelectedItem().toString());

            if (ccl.getCurso().getId() == null) {
                if (ccl.save()) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, 
                            "Se ha guardado correctamente", "Ok", 
                            JOptionPane.INFORMATION_MESSAGE);   
                    ccl.setCurso(null); 
                } else {
                JOptionPane.showMessageDialog(null, 
                        "No se ha podido guardar correctamente", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
            } 
        } else {
                if (ccl.update(ccl.getIndex())) {
                    limpiar();
                    JOptionPane.showMessageDialog(null, 
                            "Se ha editado correctamente", "Ok", 
                            JOptionPane.INFORMATION_MESSAGE);   
                    ccl.setCurso(null); 
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
    
    private void cargarVista() {
        ccl.setIndex(tblTabla.getSelectedRow());
        if (ccl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                ccl.setCurso(mtcl.getCursos().get(ccl.getIndex()));
                cbxMalla.setSelectedIndex(ccl.getCurso().getId_malla() - 1); 
                cbxCiclo.setSelectedIndex(ccl.getCurso().getCiclo());
                cbxParalelo.setSelectedItem(ccl.getCurso().getId() - 1);
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
        cbxCiclo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cbxMalla = new javax.swing.JComboBox<>();
        cbxParalelo = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtModalidad = new javax.swing.JTextField();
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
        cbxMallaB = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion del Curso", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
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
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCancelar, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Malla:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Ciclo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel7, gridBagConstraints);

        cbxCiclo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxCiclo, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Paralelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel10, gridBagConstraints);

        cbxMalla.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMalla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMallaItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxMalla, gridBagConstraints);

        cbxParalelo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A", "B" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxParalelo, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Modalidad:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel11, gridBagConstraints);

        txtModalidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        jPanel3.add(txtModalidad, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Lista de Cursos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
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

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CICLO", "PARALELO", "MALLA" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 0);
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
        jPanel5.add(btnBuscar, gridBagConstraints);

        cbxMallaB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMallaB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxMallaBItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        jPanel5.add(cbxMallaB, gridBagConstraints);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
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
        if (evt.getItem().toString().equalsIgnoreCase("MALLA")) {
            cbxMallaB.setVisible(true);
            txtBusqueda.setVisible(false);
        } else if (evt.getItem().toString().equalsIgnoreCase("CICLO")) {
            cbxMallaB.setVisible(false);
            txtBusqueda.setVisible(true);
        } else if (evt.getItem().toString().equalsIgnoreCase("PARALELO")) {
            cbxMallaB.setVisible(false);
            txtBusqueda.setVisible(true);
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void cbxAscDescItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxAscDescItemStateChanged
        ordenar();
    }//GEN-LAST:event_cbxAscDescItemStateChanged

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxMallaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMallaItemStateChanged
        Malla malla = null;
        try {
            malla = new MallaControllerListas().getMallas().get(UtilVista.getComboMallas(cbxMalla).getId() - 1);
            txtModalidad.setText(malla.getModalidad());
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_cbxMallaItemStateChanged

    private void cbxMallaBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxMallaBItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxMallaBItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCurso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmCurso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cbxAscDesc;
    private javax.swing.JComboBox<String> cbxCiclo;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxMalla;
    private javax.swing.JComboBox<String> cbxMallaB;
    private javax.swing.JComboBox<String> cbxParalelo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField txtModalidad;
    // End of variables declaration//GEN-END:variables
}
