
package vista;

import controlador.AutoControllerListas;
import controlador.MarcaControllerListas;
import controlador.TDA.listas.LinkedList;
import controlador.VendedorControllerListas;
import controlador.VentaControllerListas;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Auto;
import modelo.Marca;
import vista.listas.tablas.ModeloTablaVentaListas;
import vista.listas.util.UtilVista;

/**
 *
 * @author alexg
 */
public class FrmVentas extends javax.swing.JDialog {

    private VendedorControllerListas vcl = new VendedorControllerListas(); 
    private VentaControllerListas vtl = new VentaControllerListas();
    private MarcaControllerListas mcl = new MarcaControllerListas();
    private ModeloTablaVentaListas mtl = new ModeloTablaVentaListas();
    
    /**
     * Creates new form FrmAutos
     */
    public FrmVentas(java.awt.Frame parent, boolean modal) {
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
            if (criterio.equalsIgnoreCase("auto")) {
                mtl.setVentas(vtl.ordenar(ascdesc, "id_auto", mtl.getVentas(), metodo));
            } else if (criterio.equalsIgnoreCase("vendedor")) {
                mtl.setVentas(vtl.ordenar(ascdesc, "id_vendedor", mtl.getVentas(), metodo));
            } else {
                mtl.setVentas(vtl.ordenar(ascdesc, criterio, mtl.getVentas(), metodo));
            }
            tblTabla.setModel(mtl);
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
            String text = txtBusqueda.getText();
            if (metodo.equalsIgnoreCase("binaria")) {
                if (criterio.equalsIgnoreCase("fecha")) {
                    String strDate = txtBusquedaFecha.getText();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd / MM / yy");
                    Date fecha = formatter.parse(strDate);
                    mtl.setVentas(vtl.busquedaBinaria(vtl.getVentas(), "fecha", fecha, criterio));
                    
                } else if (criterio.equalsIgnoreCase("codigo_venta")) {
                    String codigo = txtBusqueda.getText();
                    mtl.setVentas(vtl.busquedaBinaria(vtl.getVentas(), "codigo_venta", codigo, criterio));
                    
                } else if (criterio.equalsIgnoreCase("auto")) {
                    mtl.setVentas(vtl.busquedaBinaria(vtl.getVentas(), "id_auto", UtilVista.getComboAutos(cbxAutoB).getId(), criterio));
                    
                } else if (criterio.equalsIgnoreCase("vendedor")) {
                    mtl.setVentas(vtl.busquedaBinaria(vtl.getVentas(), "id_vendedor", UtilVista.getComboVendedores(cbxVendedorB).getId(), criterio));
                }
                 
            } else if (metodo.equalsIgnoreCase("linealbinaria")) {
                if (criterio.equalsIgnoreCase("fecha")) {
                    String strDate = txtBusquedaFecha.getText();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd / MM / yy");
                    Date fecha = formatter.parse(strDate);
                    mtl.setVentas(vtl.busquedaLinealBinaria(vtl.getVentas(), "fecha", fecha, criterio));
                    
                } else if (criterio.equalsIgnoreCase("codigo_venta")) {
                    String codigo = txtBusqueda.getText();
                    mtl.setVentas(vtl.busquedaLinealBinaria(vtl.getVentas(), "codigo_venta", codigo, criterio));
                    
                } else if (criterio.equalsIgnoreCase("auto")) {
                    mtl.setVentas(vtl.busquedaLinealBinaria(vtl.getVentas(), "id_auto", UtilVista.getComboAutos(cbxAutoB).getId(), criterio));
                    
                } else if (criterio.equalsIgnoreCase("vendedor")) {
                    mtl.setVentas(vtl.busquedaLinealBinaria(vtl.getVentas(), "id_vendedor", UtilVista.getComboVendedores(cbxVendedorB).getId(), criterio));
                }
            }
            tblTabla.setModel(mtl);
            tblTabla.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }       
    
    private void limpiar() {
        cargarTabla();
        txtFecha.setText(new  SimpleDateFormat("dd / MM / yy").format(new Date()));
        txtFecha.setEnabled(true);
        txtNro_Venta.setText(vtl.generatedCode());
        txtNro_Venta.setEnabled(false);
        txtMarca.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtBusqueda.setText("");
        txtBusquedaFecha.setText("dd / MM / yy");
        vtl.setVenta(null);
        vtl.setVentas(new LinkedList<>());
        cargarTabla();
        vtl.setVenta(null);
        vtl.setIndex(-1);
        try {
            UtilVista.cargarVendedor(cbxVendedor);
            UtilVista.cargarAuto(cbxAuto);   
            UtilVista.cargarAuto(cbxAutoB);
            UtilVista.cargarVendedor(cbxVendedorB);
            cbxAutoB.setVisible(false);
            cbxVendedorB.setVisible(false);
            txtBusqueda.setVisible(false);
            txtBusquedaFecha.setVisible(true);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void cargarTabla() {
        mtl.setVentas(vtl.getVentas());
        tblTabla.setModel(mtl);
        tblTabla.updateUI();
    }
    
    private Boolean validar() {
        return !txtNro_Venta.getText().trim().isEmpty() &&
                    !txtFecha.getText().trim().isEmpty();
    }
    
    private void guardar() {
        if (validar()) {
            try {
                vtl.getVenta().setId_vendedor(UtilVista.getComboVendedores(cbxVendedor).getId());
                vtl.getVenta().setId_auto(UtilVista.getComboAutos(cbxAuto).getId());
                vtl.getVenta().setCodigo_venta(txtNro_Venta.getText());
                vtl.getVenta().setFecha(new SimpleDateFormat("dd / MM / yy").parse(txtFecha.getText()));
                
                if (vtl.getVenta().getId() == null) {
                    if (vtl.save()) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha guardado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        vtl.setVenta(null); 
                    } else {
                    JOptionPane.showMessageDialog(null, 
                            "No se ha podido guardar correctamente", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                } 
            } else {
                    if (vtl.update(vtl.getIndex())) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, 
                                "Se ha editado correctamente", "Ok", 
                                JOptionPane.INFORMATION_MESSAGE);   
                        vtl.setVenta(null); 
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
        vtl.setIndex(tblTabla.getSelectedRow());
        if (vtl.getIndex() < 0) {
            JOptionPane.showMessageDialog(null,
                    "Seleccione una fila", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                vtl.setVenta(mtl.getVentas().get(vtl.getIndex()));
                txtNro_Venta.setText(vtl.getVenta().getCodigo_venta());
                txtFecha.setText(new SimpleDateFormat("dd / MM / yy").format(vtl.getVenta().getFecha()));
                cbxVendedor.setSelectedIndex(vtl.getVenta().getId_vendedor() - 1);         
                cbxAuto.setSelectedIndex(vtl.getVenta().getId_auto() - 1); 
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
        cbxVendedor = new javax.swing.JComboBox<>();
        txtFecha = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNro_Venta = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbxAuto = new javax.swing.JComboBox<>();
        txtMarca = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
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
        jLabel11 = new javax.swing.JLabel();
        txtBusqueda = new javax.swing.JTextField();
        cbxAutoB = new javax.swing.JComboBox<>();
        btnBuscar = new javax.swing.JButton();
        cbxVendedorB = new javax.swing.JComboBox<>();
        txtBusquedaFecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbxMetodoBusqueda = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Informacion de la venta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("Agente Vendedor:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Fecha:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel2, gridBagConstraints);

        cbxVendedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxVendedor.setSelectedIndex(-1);
        cbxVendedor.setToolTipText("");
        cbxVendedor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxVendedor, gridBagConstraints);

        txtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtFecha, gridBagConstraints);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(btnCancelar, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Codigo de Venta:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel4, gridBagConstraints);

        txtNro_Venta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 100;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtNro_Venta, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("Auto vendido:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel5, gridBagConstraints);

        cbxAuto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAuto.setSelectedIndex(-1);
        cbxAuto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cbxAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAutoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(cbxAuto, gridBagConstraints);

        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtMarca, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Marca del Auto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel7, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("Precio del Auto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(jLabel8, gridBagConstraints);

        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel3.add(txtPrecio, gridBagConstraints);

        jPanel1.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), "Lista de Ventas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
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

        cbxCriterio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FECHA", "CODIGO_VENTA", "AUTO", "VENDEDOR" }));
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

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Texto:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel11, gridBagConstraints);

        txtBusqueda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 140;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(txtBusqueda, gridBagConstraints);

        cbxAutoB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxAutoB.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 134;
        jPanel5.add(cbxAutoB, gridBagConstraints);

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
        gridBagConstraints.insets = new java.awt.Insets(10, 20, 10, 10);
        jPanel5.add(btnBuscar, gridBagConstraints);

        cbxVendedorB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxVendedorB.setSelectedIndex(-1);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 134;
        jPanel5.add(cbxVendedorB, gridBagConstraints);

        txtBusquedaFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBusquedaFecha.setText("dd / MM / yy");
        txtBusquedaFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtBusquedaFechaMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 142;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(txtBusquedaFecha, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Metodo de Busqueda:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        jPanel5.add(jLabel12, gridBagConstraints);

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1094, Short.MAX_VALUE)
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

    private void cbxAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAutoActionPerformed
        Marca marca = null;
        Auto auto = null;
        try {
            marca = new MarcaControllerListas().getMarcas().get(UtilVista.getComboAutos(cbxAuto).getId_marca() - 1);
            auto = new AutoControllerListas().getAutos().get(UtilVista.getComboAutos(cbxAuto).getId() - 1);
            txtMarca.setText(marca.getNombre());
            txtPrecio.setText("$" + auto.getPrecio());
        } catch (Exception e) {
            
        }
    }//GEN-LAST:event_cbxAutoActionPerformed

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
        if (evt.getItem().toString().equalsIgnoreCase("AUTO")) {
            txtBusqueda.setVisible(false);
            txtBusquedaFecha.setVisible(false);
            cbxAutoB.setVisible(true);
            cbxVendedorB.setVisible(false);
        } else if (evt.getItem().toString().equalsIgnoreCase("VENDEDOR")) {
            txtBusqueda.setVisible(false);
            txtBusquedaFecha.setVisible(false);
            cbxAutoB.setVisible(false);
            cbxVendedorB.setVisible(true);            
        } else if (evt.getItem().toString().equalsIgnoreCase("FECHA")) {
            txtBusqueda.setVisible(false);
            txtBusquedaFecha.setVisible(true);
            txtBusquedaFecha.setText("dd / MM / yy");
            cbxAutoB.setVisible(false);
            cbxVendedorB.setVisible(false);            
        } else {
            txtBusqueda.setVisible(true);
            txtBusquedaFecha.setVisible(false);
            cbxAutoB.setVisible(false);
            cbxVendedorB.setVisible(false);            
        }
    }//GEN-LAST:event_cbxCriterioItemStateChanged

    private void txtBusquedaFechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBusquedaFechaMousePressed
        if (txtBusquedaFecha.getText().trim().isEmpty()) {
            txtBusquedaFecha.setText("dd / MM / yy");
            txtBusquedaFecha.setForeground(Color.black);
        } 
    }//GEN-LAST:event_txtBusquedaFechaMousePressed

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
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmVentas dialog = new FrmVentas(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbxAuto;
    private javax.swing.JComboBox<String> cbxAutoB;
    private javax.swing.JComboBox<String> cbxCriterio;
    private javax.swing.JComboBox<String> cbxMetodoBusqueda;
    private javax.swing.JComboBox<String> cbxMetodoOrdenacion;
    private javax.swing.JComboBox<String> cbxVendedor;
    private javax.swing.JComboBox<String> cbxVendedorB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
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
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtNro_Venta;
    private javax.swing.JTextField txtPrecio;
    // End of variables declaration//GEN-END:variables
}
