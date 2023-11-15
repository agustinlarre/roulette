package presentacion;

import javax.swing.JOptionPane;
import logicaNegocio.Participante;
import presentacion.controladores.MesaJugadorControlador;
import presentacion.vistas.VistaMesaJugador;


/**
 *
 * @author digregor
 */
public class VentanaMesaJugador extends javax.swing.JFrame implements VistaMesaJugador {

    private MesaJugadorControlador controlador;

    /**
     * Creates new form NewJFrame
     */
    public VentanaMesaJugador(Participante participanteActual) {
        initComponents();
        iniciarEscuchador();
        controlador = new MesaJugadorControlador(participanteActual, this);
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
        labelSaldoJugador = new javax.swing.JLabel();
        labelRonda = new javax.swing.JLabel();
        labelNombreJugador = new javax.swing.JLabel();
        labelUltimoNumSorteado = new javax.swing.JLabel();
        labelNumSorteado = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnAbandonar = new javax.swing.JButton();
        btnFicha1 = new javax.swing.JButton();
        btnFicha5 = new javax.swing.JButton();
        btnFicha10 = new javax.swing.JButton();
        btnFicha50 = new javax.swing.JButton();
        btnFicha100 = new javax.swing.JButton();
        labelNroRonda = new javax.swing.JLabel();
        labelMesa = new javax.swing.JLabel();
        labelNroMesa = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaRondas = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        listaOcurrencias = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        labelSaldoJugador.setText("SaldoJugador");

        labelRonda.setText("Ronda:");

        labelNombreJugador.setText("NombreJugador");

        labelUltimoNumSorteado.setText(" ");

        labelNumSorteado.setText("Numero Sorteado: ");

        btnAbandonar.setText("Abandonar");
        btnAbandonar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbandonarActionPerformed(evt);
            }
        });

        btnFicha1.setBackground(new java.awt.Color(204, 204, 204));
        btnFicha1.setText("1");
        btnFicha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicha1ActionPerformed(evt);
            }
        });

        btnFicha5.setBackground(new java.awt.Color(0, 204, 0));
        btnFicha5.setText("5");
        btnFicha5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicha5ActionPerformed(evt);
            }
        });

        btnFicha10.setBackground(new java.awt.Color(0, 102, 204));
        btnFicha10.setText("10");
        btnFicha10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicha10ActionPerformed(evt);
            }
        });

        btnFicha50.setBackground(new java.awt.Color(255, 153, 102));
        btnFicha50.setText("50");
        btnFicha50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicha50ActionPerformed(evt);
            }
        });

        btnFicha100.setBackground(new java.awt.Color(255, 51, 51));
        btnFicha100.setText("100");
        btnFicha100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFicha100ActionPerformed(evt);
            }
        });

        labelNroRonda.setText(" ");

        labelMesa.setText("Ruleta:");

        labelNroMesa.setText(" ");
        labelNroMesa.setToolTipText("");

        listaRondas.setColumns(20);
        listaRondas.setRows(5);
        jScrollPane3.setViewportView(listaRondas);

        listaOcurrencias.setColumns(20);
        listaOcurrencias.setRows(5);
        jScrollPane4.setViewportView(listaOcurrencias);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(r, javax.swing.GroupLayout.DEFAULT_SIZE, 788, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(labelSaldoJugador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelMesa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNroMesa)
                        .addGap(45, 45, 45)
                        .addComponent(labelRonda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelNroRonda)
                        .addGap(245, 245, 245)
                        .addComponent(labelNombreJugador))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnFicha1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFicha5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFicha10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFicha50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFicha100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(labelNumSorteado)
                                .addGap(18, 18, 18)
                                .addComponent(labelUltimoNumSorteado, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnAbandonar, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelRonda)
                            .addComponent(labelSaldoJugador)
                            .addComponent(labelNombreJugador)
                            .addComponent(labelNroRonda)
                            .addComponent(labelMesa)
                            .addComponent(labelNroMesa))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelNumSorteado)
                            .addComponent(labelUltimoNumSorteado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnFicha1)
                            .addComponent(btnFicha50)
                            .addComponent(btnFicha100)
                            .addComponent(btnFicha10)
                            .addComponent(btnFicha5))
                        .addGap(20, 20, 20)
                        .addComponent(r, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(btnAbandonar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFicha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicha1ActionPerformed
        controlador.almacenarFicha(1);
    }//GEN-LAST:event_btnFicha1ActionPerformed

    private void btnFicha5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicha5ActionPerformed
        controlador.almacenarFicha(5);
    }//GEN-LAST:event_btnFicha5ActionPerformed

    private void btnFicha10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicha10ActionPerformed
        controlador.almacenarFicha(10);
    }//GEN-LAST:event_btnFicha10ActionPerformed

    private void btnFicha50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicha50ActionPerformed
        controlador.almacenarFicha(50);
    }//GEN-LAST:event_btnFicha50ActionPerformed

    private void btnFicha100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFicha100ActionPerformed
        controlador.almacenarFicha(100);
    }//GEN-LAST:event_btnFicha100ActionPerformed

    private void btnAbandonarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbandonarActionPerformed
       controlador.abandonarMesa();
    }//GEN-LAST:event_btnAbandonarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbandonar;
    private javax.swing.JButton btnFicha1;
    private javax.swing.JButton btnFicha10;
    private javax.swing.JButton btnFicha100;
    private javax.swing.JButton btnFicha5;
    private javax.swing.JButton btnFicha50;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel labelMesa;
    private javax.swing.JLabel labelNombreJugador;
    private javax.swing.JLabel labelNroMesa;
    private javax.swing.JLabel labelNroRonda;
    private javax.swing.JLabel labelNumSorteado;
    private javax.swing.JLabel labelRonda;
    private javax.swing.JLabel labelSaldoJugador;
    private javax.swing.JLabel labelUltimoNumSorteado;
    private javax.swing.JTextArea listaOcurrencias;
    private javax.swing.JTextArea listaRondas;
    private presentacion.PanelRuleta r;
    // End of variables declaration//GEN-END:variables
    
    @Override
    public void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    private void iniciarEscuchador() {
        r.agregar(new PanelRuleta.Escuchador() {
            @Override
            public void celdaSeleccionada(int universalCellCode) {
                controlador.apostar(universalCellCode);
            }
        });
    }

    @Override
    public void mostrarNroRonda(int nro) {
        labelNroRonda.setText("#" + String.valueOf(nro));
    }

    @Override
    public void habilitarPantallaMesa() {
        r.reanudar();
    }

    @Override
    public void inhabilitarPantallaMesa() {
        r.pausar();
    }

    @Override
    public void mostrarNroMesa(int nro) {
        labelNroMesa.setText("#" + String.valueOf(nro));
    }

    @Override
    public void mostrarUltimoNroSorteado(int ultimoNum) {
        labelUltimoNumSorteado.setText(String.valueOf(ultimoNum));
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
    public void mostrarSaldoActual(int saldo) {
        labelSaldoJugador.setText("$" + String.valueOf(saldo));
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
    public void avisarCierreMesa() {
        JOptionPane.showMessageDialog(this, "La mesa se cerrará...");
    }
    
    @Override
    public void mostrarNombreJugador(String nombre) {
        labelNombreJugador.setText(nombre);
    }

    @Override
    public void cerrarVentanaMesa() {
        dispose();
    }

    @Override
    public void popularHistoricoRondas(int nroRonda, int totalApostado, int ganado, int perdido, int balance) {
        listaRondas.setText(listaRondas.getText() + "Ronda: " + nroRonda + " - Total apostado: " + totalApostado + " - Ganado: " + ganado + " - Perdido: " + perdido + " Balance: " + balance + "\n");
    }

    @Override
    public void recibirOcurrencia(int cellCode, double ocurrencia) {
        listaOcurrencias.setText(listaOcurrencias.getText() + "Casillero: " + cellCode + " - Ocurrencia: " + ocurrencia + "% \n");
    }
    
    @Override
    public void limpiarOcurrencias() {
        listaOcurrencias.setText("");
    }
}
