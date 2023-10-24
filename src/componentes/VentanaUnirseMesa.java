/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package componentes;

import excepcionesSistema.MesaException;
import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import logicaNegocio.Jugador;
import logicaNegocio.Mesa;
import logicaNegocio.Sesion;
import servicios.Fachada;
import logicaNegocio.MesaNotificable;
import logicaNegocio.Participante;

/**
 *
 * @author agust
 */
public class VentanaUnirseMesa extends javax.swing.JFrame implements MesaNotificable {
    
    private Sesion sesion;
    private Jugador jugador;

    /**
     * Creates new form VentanaUnirseMesa
     */
    public VentanaUnirseMesa(Sesion sesionActual) {
        initComponents();
        inicializar();
        this.sesion = sesionActual;
        this.jugador = (Jugador) sesionActual.getUsuario();
        this.setTitle("Aplicación Jugador - Unirse a mesa");
    }
    
    private void inicializar() {
        Fachada.getInstancia().addNotificable(this);
        hidratarListaMesas();
    }
    
    private void hidratarListaMesas() {
        List<Mesa> mesasAbiertas = Fachada.getInstancia().getMesas();
        listaMesasAbiertas.setListData(mesasAbiertas.toArray());
        listaMesasAbiertas.setCellRenderer(new MesaAbiertaCellRenderer());
        listaMesasAbiertas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    
    private class MesaAbiertaCellRenderer implements ListCellRenderer<Mesa> {
        
        private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, Mesa mesa, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel ta = (JLabel) defaultRenderer.getListCellRendererComponent(list, index, index, isSelected, cellHasFocus);
            ta.setText("Mesa n°" + mesa.getNroMesa());
            return ta;
        }
        
    }
    
    @Override
    public void notificarMesaAbierta(Mesa mesa) {
        hidratarListaMesas();
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
        listaMesasAbiertas = new javax.swing.JList();
        btnUnirseMesa = new javax.swing.JButton();
        btnLogOff = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Mesas abiertas");

        jScrollPane1.setViewportView(listaMesasAbiertas);

        btnUnirseMesa.setText("Unirse");
        btnUnirseMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirseMesaActionPerformed(evt);
            }
        });

        btnLogOff.setText("Log off");
        btnLogOff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOffActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(btnUnirseMesa, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnLogOff, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUnirseMesa)
                    .addComponent(btnLogOff))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogOffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOffActionPerformed
        Fachada.getInstancia().logout(this.sesion);
        dispose();
    }//GEN-LAST:event_btnLogOffActionPerformed

    private void btnUnirseMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirseMesaActionPerformed
        try {
            Mesa mesaElegida = (Mesa) listaMesasAbiertas.getSelectedValue();
            Participante participante = new Participante(this.jugador, mesaElegida);
            Fachada.getInstancia().addParticipante(participante);
            new VentanaMesaJugador(participante).setVisible(true);
        } catch(MesaException mesaEx) {
            JOptionPane.showMessageDialog(this, mesaEx.getMessage());
        }
        
    }//GEN-LAST:event_btnUnirseMesaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogOff;
    private javax.swing.JButton btnUnirseMesa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listaMesasAbiertas;
    // End of variables declaration//GEN-END:variables
}
