/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import java.awt.Component;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import logicaNegocio.Sesion;
import logicaNegocio.TipoApuesta;
import presentacion.controladores.IniciarMesaControlador;
import presentacion.vistas.VistaIniciarMesa;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaIniciarMesa extends javax.swing.JFrame implements VistaIniciarMesa {
    
    private IniciarMesaControlador controlador;

    /**
     * Creates new form VentanaInicioMesa
     */
    public VentanaIniciarMesa(Sesion sesionActual) {
        initComponents();
        controlador = new IniciarMesaControlador(sesionActual, this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Fachada.getInstancia().logout(sesionActual);
            }
        });
    }
    
    @Override
    public void obtenerTiposApuesta(List<TipoApuesta> tiposApuesta) {
        listaTiposApuesta.setListData(tiposApuesta.toArray());
        listaTiposApuesta.setCellRenderer(new TipoApuestaRenderer());
    }

    @Override
    public void proximoCU(Sesion sesionActual) {
        new VentanaMesaCrupier(sesionActual).setVisible(true);
    }

    @Override
    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void cerrarVentana() {
        dispose();
    }
    
    private class TipoApuestaRenderer implements ListCellRenderer<TipoApuesta> {
        
        private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, TipoApuesta tipoApuesta, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel tipoAp = (JLabel) defaultRenderer.getListCellRendererComponent(list, index, index, isSelected, cellHasFocus);
            tipoAp.setText(tipoApuesta.getNombreTipo() + " - Factor de pago: " + tipoApuesta.getFactorPago());
            return tipoAp;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

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
        controlador.iniciarMesa(listaTiposApuesta.getSelectedValuesList());        
    }//GEN-LAST:event_btnIniciarMesaActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        controlador.cerrarSesion();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciarMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaTiposApuesta;
    // End of variables declaration//GEN-END:variables
}
