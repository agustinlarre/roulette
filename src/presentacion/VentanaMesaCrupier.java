package presentacion;

import java.awt.Component;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import logicaNegocio.Efecto;
import logicaNegocio.Participante;
import logicaNegocio.Sesion;
import presentacion.controladores.MesaCrupierControlador;
import presentacion.vistas.VistaMesaCrupier;

/**
 *
 * @author digregor
 */
public class VentanaMesaCrupier extends javax.swing.JFrame implements VistaMesaCrupier {

//    private Sesion sesion;
//    private Crupier crupier;
//    private Mesa mesa;
    private MesaCrupierControlador controlador;

    /**
     * Creates new form NewJFrame
     */
    public VentanaMesaCrupier(Sesion sesionActual) {
        initComponents();
        comboEfectos.setRenderer(new EfectoRenderer());
        listaParticipantes.setCellRenderer(new ParticipanteRenderer());
        controlador = new MesaCrupierControlador(sesionActual, this);
    }
    
    @Override
    public void mostrarUltimoNroSorteado(int ultimoNum) {
        labelUltimoNro.setText(String.valueOf(ultimoNum));
        labelUltimosLanzamientos.setText(labelUltimosLanzamientos.getText() + " "+ ultimoNum);
    }

    @Override
    public void inhabilitarPantallaMesa() {
        r.pausar();
    }
    
    @Override
    public void habilitarPantallaMesa() {
        r.reanudar();
    }
    
    @Override 
    public void deshabilitarCasilleros() {
        r.desactivarBotones();
    }
    
    @Override
    public void habilitarCasillero(int cellCode) {
        r.habilitar(cellCode, true);
    }
    
    @Override
    public void agregarEfecto(Efecto efecto) {
        comboEfectos.addItem(efecto);
    }
    
    @Override
    public void mostrarNroMesa(int nroMesa) {
        this.labelNroRuleta.setText("Ruleta #" + nroMesa);
    }
    
    @Override
    public void mostrarBalanceActual(int balance) {
        labelBalanceCasa.setText("$" + String.valueOf(balance));
    }

    @Override
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    @Override
    public void cerrarVentanaMesa() {
        dispose();
    }

    @Override
    public void agregarParticipantes(List<Participante> participantes) {
        listaParticipantes.setListData(participantes.toArray());
    }

    @Override
    public void mostrarValorApostado(int cellCode, int monto) {
        r.setApuesta(cellCode, monto);
    }

    @Override
    public void limpiarValoresApostados() {
        r.limpiar();
    }

    @Override
    public void mostrarTotalApostadoPorRonda(int montoTotal) {
        labelValorTotalApuestas.setText("$" + String.valueOf(montoTotal));
    }

    @Override
    public void mostrarCantidadApuestasPorRonda(int cantApuestas) {
        labelCantApuestas.setText(String.valueOf(cantApuestas));
    }

    @Override
    public void mostrarNroRonda(int nroRonda) {
        labelRonda.setText("Ronda #" + nroRonda);
    }

    @Override
    public void popularHistoricoRondas(int nroRonda, int balanceAnt, int montoApu, int montoRec, int montoPagado, int balancePost) {
        listaRondas.setText(listaRondas.getText() + "Ronda: " + nroRonda + " - Balance anterior: " + balanceAnt + " - Apuestas: " + montoApu + " - Recoleccion: " + montoRec + " - Liquidacion: " + montoPagado + " - Balance posterior: " + balancePost + "\n");
    }
    
    private class EfectoRenderer implements ListCellRenderer<Efecto> {
        
        private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, Efecto efecto, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel efect = new JLabel();
            efect.setText((efecto.getNombreEfecto()));
            return efect;
        }
        
    }
    
    private class ParticipanteRenderer implements ListCellRenderer<Participante> {
        
        private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList list, Participante participante, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel ta = (JLabel) defaultRenderer.getListCellRendererComponent(list, index, index, isSelected, cellHasFocus);
            ta.setText(participante.getJugador().getNombre() + " - " + participante.getJugador().getSaldo());
            return ta;
        }
        
    }

    private void nuevaAccionMesa() {
        Efecto efectoSeleccionado = (Efecto) comboEfectos.getSelectedItem();
        controlador.nuevaAccionMesa(efectoSeleccionado);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        r = new presentacion.PanelRuleta();
        labelNroRuleta = new javax.swing.JLabel();
        btnCerrarMesa = new javax.swing.JButton();
        btnLanzarPagar = new javax.swing.JButton();
        labelCantApuestas = new javax.swing.JLabel();
        labelValorTotalApuestas = new javax.swing.JLabel();
        comboEfectos = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        labelBalanceCasa = new javax.swing.JLabel();
        labelRonda = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaParticipantes = new javax.swing.JList();
        labelUltimosLanzamientos = new javax.swing.JLabel();
        labelUltimoNro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaRondas = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelNroRuleta.setText("jLabel1");

        btnCerrarMesa.setText("Cerrar mesa");
        btnCerrarMesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarMesaActionPerformed(evt);
            }
        });

        btnLanzarPagar.setText("Lanzar/Pagar");
        btnLanzarPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLanzarPagarActionPerformed(evt);
            }
        });

        labelCantApuestas.setText("0");

        labelValorTotalApuestas.setText("0");

        comboEfectos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comboEfectos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEfectosActionPerformed(evt);
            }
        });

        labelBalanceCasa.setText("jLabel1");

        labelRonda.setText("jLabel2");

        jScrollPane3.setViewportView(listaParticipantes);

        labelUltimosLanzamientos.setText("Ultimos lanzamientos:");

        labelUltimoNro.setText("Ultimo n° sorteado");

        jLabel1.setText("Apuestas:");

        jLabel2.setText("Monto: ");

        listaRondas.setColumns(20);
        listaRondas.setRows(5);
        jScrollPane1.setViewportView(listaRondas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCantApuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelValorTotalApuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(162, 162, 162)
                        .addComponent(comboEfectos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLanzarPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUltimoNro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(r, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelBalanceCasa)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelRonda)
                                .addGap(182, 182, 182)
                                .addComponent(labelNroRuleta, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCerrarMesa))
                            .addComponent(labelUltimosLanzamientos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRonda)
                    .addComponent(labelNroRuleta)
                    .addComponent(btnCerrarMesa)
                    .addComponent(labelBalanceCasa))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCantApuestas)
                    .addComponent(labelValorTotalApuestas)
                    .addComponent(comboEfectos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLanzarPagar)
                    .addComponent(labelUltimoNro)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelUltimosLanzamientos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarMesaActionPerformed
        controlador.cerrarMesa();
    }//GEN-LAST:event_btnCerrarMesaActionPerformed

    private void btnLanzarPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLanzarPagarActionPerformed
        // Llamar a un metodo y no implementarlo dentro del boton
        nuevaAccionMesa();
    }//GEN-LAST:event_btnLanzarPagarActionPerformed

    private void comboEfectosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEfectosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboEfectosActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        controlador.cerrarMesa();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarMesa;
    private javax.swing.JButton btnLanzarPagar;
    private javax.swing.JComboBox comboEfectos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelBalanceCasa;
    private javax.swing.JLabel labelCantApuestas;
    private javax.swing.JLabel labelNroRuleta;
    private javax.swing.JLabel labelRonda;
    private javax.swing.JLabel labelUltimoNro;
    private javax.swing.JLabel labelUltimosLanzamientos;
    private javax.swing.JLabel labelValorTotalApuestas;
    private javax.swing.JList listaParticipantes;
    private javax.swing.JTextArea listaRondas;
    private presentacion.PanelRuleta r;
    // End of variables declaration//GEN-END:variables
}
