/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package componentes;

/**
 *
 * @author Agustin
 */
public class PanelNavbarMesaJugador extends javax.swing.JPanel {

    /**
     * Creates new form PanelNavbarMesaJugador
     */
    public PanelNavbarMesaJugador() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        labelSaldoJugador = new javax.swing.JLabel();
        labelRonda = new javax.swing.JLabel();
        labelNombreJugador = new javax.swing.JLabel();
        labelUltimoNumSorteado = new javax.swing.JLabel();
        labelNumSorteado = new javax.swing.JLabel();

        jTextField1.setText("50");

        labelSaldoJugador.setText("SaldoJugador");

        labelRonda.setText("Ronda");

        labelNombreJugador.setText("NombreJugador");

        labelUltimoNumSorteado.setText("UlNumSor");

        labelNumSorteado.setText("Numero Sorteado: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addComponent(labelNumSorteado)
                .addGap(34, 34, 34)
                .addComponent(labelUltimoNumSorteado, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(labelSaldoJugador)
                .addGap(266, 266, 266)
                .addComponent(labelRonda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 277, Short.MAX_VALUE)
                .addComponent(labelNombreJugador)
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRonda)
                    .addComponent(labelSaldoJugador)
                    .addComponent(labelNombreJugador))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumSorteado)
                    .addComponent(labelUltimoNumSorteado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labelNombreJugador;
    private javax.swing.JLabel labelNumSorteado;
    private javax.swing.JLabel labelRonda;
    private javax.swing.JLabel labelSaldoJugador;
    private javax.swing.JLabel labelUltimoNumSorteado;
    // End of variables declaration//GEN-END:variables
}
