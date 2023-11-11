/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

/**
 *
 * @author Agustin
 */
public interface VistaMesaJugador {
    public void mostrarMensajeError(String mensaje);
    public void actualizarNroRonda(int nro);
    public void habilitarPantallaMesa();
    public void inhabilitarPantallaMesa();
    public void mostrarNroMesa(int nro);
    public void mostrarUltimoNroSorteado(int ultimoNum);
    public void mostrarSaldoActual(int saldo);
    public void mostrarValorApostado(int cellCode, int monto);
    public void limpiarValoresApostados();
}
