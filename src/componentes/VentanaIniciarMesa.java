/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package componentes;

import excepcionesSistema.MesaException;
import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import logicaNegocio.Crupier;
import logicaNegocio.Mesa;
import logicaNegocio.Sesion;
import logicaNegocio.TipoApuesta;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaIniciarMesa extends javax.swing.JFrame {
    
    private Sesion sesion;
    private Crupier crupier;

    /**
     * Creates new form VentanaInicioMesa
     */
    public VentanaIniciarMesa(Sesion sesionActual) {
        initComponents();
        inicializar();
        this.sesion = sesionActual;
        this.crupier = (Crupier) sesionActual.getUsuario();
        this.setTitle("Aplicación Crupier - Iniciar mesa");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.getInstancia().logout(sesionActual);
            }
        });
    }
    
    private void inicializar() {
        hidratarListaTiposApuesta();
    }
    
    private void hidratarListaTiposApuesta() {
        //Como elegir el tipo de apuesta seleccionado de la lista...
        List<TipoApuesta> tiposApuesta = Fachada.getInstancia().getTiposApuesta();
        listaTiposApuesta.setListData(tiposApuesta.toArray());
        listaTiposApuesta.setCellRenderer(new TipoApuestaRenderer());
        //Habilitar seleccion multiple
        listaTiposApuesta.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    }
    
    private class TipoApuestaRenderer implements ListCellRenderer<TipoApuesta> {
        
        private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, TipoApuesta tipoApuesta, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel ta = (JLabel) defaultRenderer.getListCellRendererComponent(list, index, index, isSelected, cellHasFocus);
            ta.setText(tipoApuesta.getNombreTipo() + " - Factor de pago: " + tipoApuesta.getFactorPago());
            return ta;
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaTiposApuesta = new javax.swing.JList();
        btnIniciarMesa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tipo de apuesta");

        listaTiposApuesta.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(listaTiposApuesta);

        btnIniciarMesa.setText("Iniciar");
        btnIniciarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarMesaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                    .addComponent(btnIniciarMesa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIniciarMesa)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarMesaActionPerformed
        try {
            List<TipoApuesta> tiposApuestaElegidos = listaTiposApuesta.getSelectedValuesList();
            Mesa mesa = new Mesa(tiposApuestaElegidos);
            this.crupier.setMesa(mesa);
            Fachada.getInstancia().addMesa(mesa);
            new VentanaMesaCrupier(this.sesion).setVisible(true);
            dispose();
        } catch(MesaException mesaEx) {
            JOptionPane.showMessageDialog(this, mesaEx.getMessage());
        }
        
    }//GEN-LAST:event_btnIniciarMesaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaTiposApuesta;
    // End of variables declaration//GEN-END:variables
}
