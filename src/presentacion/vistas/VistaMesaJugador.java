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
    public void deshabilitarCasilleros();
    public void habilitarCasillero(int cellCode);
    public void mostrarMensajeError(String mensaje);
    public void actualizarNroRonda(int nro);
    public void habilitarPantallaMesa();
    public void inhabilitarPantallaMesa();
    public void mostrarNroMesa(int nro);
    public void mostrarUltimoNroSorteado(int ultimoNum);
    public void mostrarSaldoActual(int saldo);
    public void mostrarValorApostado(int cellCode, int monto);
    public void limpiarValoresApostados();
    public void avisarCierreMesa();
    public void cerrarVentanaMesa();
    public void popularHistoricoRondas(int nroRonda, int totalApostado, int ganado, int perdido, int balance);
    public void limpiarOcurrencias();
    public void recibirOcurrencia(int nroSorteado, double ocurrencia);
}
