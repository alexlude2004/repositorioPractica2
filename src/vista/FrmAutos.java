
package vista;

import controlador.AutoControllerListas;
import controlador.MarcaControllerListas;
import controlador.TDA.listas.LinkedList;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import vista.listas.tablas.ModeloTablaAutoListas;
import vista.listas.util.UtilVista;

/**
 *
 * @author alexg
 */
public class FrmAutos extends javax.swing.JDialog {

    private AutoControllerListas acl = new AutoControllerListas();
    private MarcaControllerListas mcl = new MarcaControllerListas();
    private ModeloTablaAutoListas mtal = new ModeloTablaAutoListas();
    
    /**
     * Creates new form FrmAutos
     */
    public FrmAutos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        limpiar();
    }
    
    private void ordenar() {
        String metodo = cbxMetodoOrdenacion.getSelectedItem().toString();
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        Integer ascdesc  = cbxAscDesc.getSelectedIndex();
        String tipoOrdenamiento = cbxAscDesc.getSelectedItem().toString();
        
        long startTime = System.nanoTime();
        try {
            mtal.setAutos(acl.ordenar(ascdesc, criterio, mtal.getAutos(), metodo));
            tblTabla.setModel(mtal);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
            
        System.out.println("\nMetodo de Ordenacion: " + metodo + "\nCritero: " + criterio + "\nTipo de Ordenamiento: " + tipoOrdenamiento + "\n");
        System.out.println("Tiempo de ejecucion " + metodo + ": " + timeElapsed + " nanosegundos");
        System.out.println("Tiempo de ejecucion " + metodo + ": " + timeElapsed/1e6 + " milisegundos");
    }    

    private void buscar() {
        String metodo = cbxMetodoBusqueda.getSelectedItem().toString();
        String criterio = cbxCriterio.getSelectedItem().toString().toLowerCase();
        
        try {
            if (metodo.equalsIgnoreCase("binaria")) {
                 if (criterio.equalsIgnoreCase("precio")) {
                    Double precio = Double.parseDouble(txtBusqueda.getText()); 
                    mtal.setAutos(acl.busquedaBinaria(acl.getAutos(), "precio", precio, criterio));
                } else if (criterio.equalsIgnoreCase("marca")) {
                    mtal.setAutos(acl.busquedaBinaria(acl.listAll(), "id_marca", UtilVista.getComboMarcas(cbxMarcaB).getId(), criterio));
                } else if (criterio.equalsIgnoreCase("modelo")) {
                    String text = txtBusqueda.getText();
                    String modelo = text.toUpperCase();
                    mtal.setAutos(acl.busquedaBinaria(acl.getAutos(), "modelo", modelo, criterio));
                } else if (criterio.equalsIgnoreCase("color")) {
                    String text = txtBusqueda.getText();
                    String color = Character.toUpperCase(text.charAt(0)) + text.substring(1);
                    mtal.setAutos(acl.busquedaBinaria(acl.getAutos(), "color", color, criterio));
                } else if (criterio.equalsIgnoreCase("anio")) {
                    Integer anio = Integer.parseInt(txtBusqueda.getText());
                    mtal.setAutos(acl.busquedaBinaria(acl.getAutos(), "anio", anio, criterio));
                }
            } else if (metodo.equalsIgnoreCase("linealbinaria")) {
                if (criterio.equalsIgnoreCase("precio")) {
                    Double precio = Double.parseDouble(txtBusqueda.getText()); 
                    mtal.setAutos(acl.busquedaLinealBinaria(acl.getAutos(), "precio", precio, criterio));
                } else if (criterio.equalsIgnoreCase("marca")) {
                    mtal.setAutos(acl.busquedaLinealBinaria(acl.listAll(), "id_marca", UtilVista.getComboMarcas(cbxMarcaB).getId(), criterio));
                } else if (criterio.equalsIgnoreCase("modelo")) {
                    String text = txtBusqueda.getText();
                    String modelo = text.toUpperCase();
                    mtal.setAutos(acl.busquedaLinealBinaria(acl.getAutos(), "modelo", modelo, criterio));
                } else if (criterio.equalsIgnoreCase("color")) {
                    String text = txtBusqueda.getText();
                    String color = Character.toUpperCase(text.charAt(0)) + text.substring(1);
                    mtal.setAutos(acl.busquedaLinealBinaria(acl.getAutos(), "color", color, criterio));
                } else if (criterio.equalsIgnoreCase("anio")) {
                    Integer anio = Integer.parseInt(txtBusqueda.getText());
                    mtal.setAutos(acl.busquedaLinealBinaria(acl.getAutos(), "anio", anio, criterio));
                }
            }
            tblTabla.setModel(mtal);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }    
    
    private void limpiar() {
        txtColor.setText("");
        txtAnio.setText("");
        txtPrecio.setText("");
        txtModelo.setText("");
        acl.setAuto(null);
        acl.setAutos(new LinkedList<>());
        cargarTabla();
        acl.setAuto(null);
        acl.setIndex(-1);
        try {
            UtilVista.cargarMarca(cbxMarca);
            UtilVista.cargarMarca(cbxMarcaB);
            cbxMarcaB.setVisible(false);
            txtBusqueda.setVisible(true);            
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
    
    private void cargarTabla() {
        mtal.setAutos(acl.getAutos());
        tblTabla.setModel(mtal);
        tblTabla.updateUI();
    }
    
    private Boolean validar() {
        return !txtColor.getText().trim().isEmpty() &&
                    !txtAnio.getText().trim().isEmpty() &&
                    !txtPrecio.getText().trim().isEmpty() &&
                    !txtModelo.getText().trim().isEmpty();
    }
    
    private void guardar() {
        if (validar()) {
            try {
                acl.getAuto().setId_marca(UtilVista.getComboMarcas(cbxMarca).getId());
                acl.getAuto().setColor(txtColor.getText());
                acl.getAuto().setAnio(Integer.parseInt(txtAnio.getText()));
                acl.getAuto().setPrecio(Double.parseDouble(txtPrecio.getText()));
                acl.getAuto().setModelo(txtModelo.getText());
                
                if (acl.getAuto().getId() == null) {
                    if (acl.save()) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha guardado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        acl.setAuto(null); 
                    } else {
                    JOptionPane.showMessageDialog(null, 
                            "No se ha podido guardar correctamente", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } 
            } else {
                    if (acl.update(acl.getIndex())) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha editado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        acl.setAuto(null); 
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
        }else {
                JOptionPane.showMessageDialog(null, 
                        "Llene todos los campos", 
                        "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void cargarVista() {
        acl.setIndex(tblTabla.getSelectedRow());
        if (acl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                acl.setAuto(mtal.getAutos().get(acl.getIndex()));
                txtColor.setText(acl.getAuto().getColor());
                txtAnio.setText(acl.getAuto().getAnio().toString());
                txtPrecio.setText(acl.getAuto().getPrecio().toString());
                txtModelo.setText(acl.getAuto().getModelo());
                cbxMarca.setSelectedIndex(acl.getAuto().getId_marca() - 1);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbxMarca = new javax.swing.JComboBox<>();
        txtColor = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtModelo = new javax.swing.JTextField();
        txtAnio1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTabla = new javax.swing.JTable();
        btnSeleccionar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbxCriterio = new javax.swing.JComboBox<>();
        cbxAscDesc = new javax.swing.JComboBox<>();
        cbxMetodoOrdenacion = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        btnOrdenar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        cbxMarcaB = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbxMetodoBusqueda = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion del auto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Marca:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Color:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Anio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel3, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Precio de venta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel4, gridBagConstraints);

        cbxMarca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMarca.setSelectedIndex(-1);
        cbxMarca.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxMarca, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtColor, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtAnio, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtPrecio, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnGuardar, gridBagConstraints);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCancelar, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtModelo, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtAnio1, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Modelo:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel6, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Lista de Autos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 400;
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnSeleccionar, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Criterio:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel9, gridBagConstraints);

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COLOR", "PRECIO", "ANIO", "MODELO", "MARCA" }));
        cbxCriterio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxCriterioItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxCriterio, gridBagConstraints);

        cbxAscDesc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ASCENDENTE", "DESCENDETE" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxAscDesc, gridBagConstraints);

        cbxMetodoOrdenacion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MERGESORT", "QUICKSORT" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxMetodoOrdenacion, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Metodo de Ordenacion:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 10);
        jPanel5.add(jLabel10, gridBagConstraints);

        btnOrdenar.setText("Ordenar");
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(btnOrdenar, gridBagConstraints);

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

        cbxMarcaB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxMarcaB.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 134;
        jPanel5.add(cbxMarcaB, gridBagConstraints);

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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Metodo de Busqueda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel11, gridBagConstraints);

        cbxMetodoBusqueda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BINARIA", "LINEALBINARIA" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(cbxMetodoBusqueda, gridBagConstraints);

        jPanel1.add(jPanel5, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1083, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        ordenar();
    }//GEN-LAST:event_btnOrdenarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cbxCriterioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxCriterioItemStateChanged
        if (evt.getItem().toString().equalsIgnoreCase("MARCA")) {
            txtBusqueda.setVisible(false);
            cbxMarcaB.setVisible(true);
        } else {
            txtBusqueda.setVisible(true);
            cbxMarcaB.setVisible(false);
        }        
    }//GEN-LAST:event_cbxCriterioItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmAutos dialog = new FrmAutos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JComboBox<String> cbxAscDesc;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxMarca;
    private javax.swing.JComboBox<String> cbxMarcaB;
    private javax.swing.JComboBox<String> cbxMetodoBusqueda;
    private javax.swing.JComboBox<String> cbxMetodoOrdenacion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblTabla;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnio1;
    private javax.swing.JTextField txtBusqueda;
    private javax.swing.JTextField txtColor;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
