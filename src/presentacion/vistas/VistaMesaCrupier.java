/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

import java.util.List;
import logicaNegocio.Casillero;
import logicaNegocio.Efecto;
import logicaNegocio.Participante;

/**
 *
 * @author Agustin
 */
public interface VistaMesaCrupier {
    public void deshabilitarCasilleros();
    public void inhabilitarPantallaMesa();
    public void habilitarPantallaMesa();
    public void mostrarNroMesa(int nroMesa);
    public void mostrarNroRonda(int nroRonda);
    public void mostrarMensajeError(String mensaje);
    public void habilitarCasillero(int cellCode);
    public void agregarEfecto(Efecto efecto);
    public void agregarParticipantes(List<Participante> participantes);
    public void actualizarNroRonda(int nroRonda);
    public void mostrarUltimoNroSorteado(int ultimoNum);
    public void mostrarBalanceActual(int balance);
    public void mostrarValorApostado(int cellCode, int monto);
    public void mostrarTotalApostadoPorRonda(int montoTotal);
    public void mostrarCantidadApuestasPorRonda(int cantApuestas);
    public void cerrarVentanaMesa();
    public void limpiarValoresApostados();
    public void popularHistoricoRondas(int nroRonda, int balanceAnt, int montoApu, int montoRec, int montoPagado, int balancePost);
}
