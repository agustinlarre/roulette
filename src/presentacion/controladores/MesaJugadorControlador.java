/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.ApuestaException;
import excepcionesSistema.MesaException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocio.TipoApuesta;
import logicaNegocio.Casillero;
import logicaNegocio.Ficha;
import logicaNegocio.Mesa;
import logicaNegocio.Participante;
import presentacion.vistas.VistaMesaJugador;
import servicios.Fachada;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class MesaJugadorControlador implements Observador {
    private Participante participante;
    private Mesa mesa;
    private Ficha fichaSeleccionada;
    private VistaMesaJugador vista;

    public MesaJugadorControlador(Participante participante, VistaMesaJugador vista) {
        this.participante = participante;
        this.mesa = participante.getMesa();
        this.vista = vista;
        this.fichaSeleccionada = null;
        mesa.subscribir(this);
        Fachada.getInstancia().subscribir(this);
        
        this.inicializarMesa();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        // Cambiar condiciones anidadas
        if (evento.equals(Evento.MESA_PAUSADA)) {
            this.pausar();
        } else if (evento.equals(Evento.RONDA_REANUDADA)) {
            this.reanudar();
        } else if (evento.equals(Evento.APUESTA_REALIZADA) || evento.equals(Evento.APUESTA_MODIFICADA) || evento.equals(Evento.PAGO_REALIZADO)) {
            this.mostrarSaldoActual();
        } else if (evento.equals(Evento.MESA_CERRADA)) {
            this.salirDeMesaPorCierre();
        } else if (evento.equals(Evento.JUGADOR_DESLOGUEADO)) {
            vista.cerrarVentanaMesa();
        }
    }
    
    public void apostar(int cellCode) {
        //Paso int de valor de ficha o el objeto ficha???
        Casillero casillero = this.mesa.getCasilleroSegunCellCode(cellCode);
        try {
            participante.realizarApuesta(casillero, fichaSeleccionada);
            this.establecerMontoSegunCasillero(casillero);
            // Mostrar en ventana la ficha ingresada en el casillero
        } catch (ApuestaException ex) {
            vista.mostrarMensajeError(ex.getMessage());
        }
    }
    
    public void almacenarFicha(int valor) {
        fichaSeleccionada = Fachada.getInstancia().getFichaSegunValor(valor);
    }
    
    public void abandonarMesa() {
        try {
            participante.abandonarMesa(false);
            vista.cerrarVentanaMesa();
        } catch (MesaException ex) {
            vista.mostrarMensajeError(ex.getMessage());
        }
    }
    
    private void pausar() {
        this.mostrarUltimoNumeroSorteado();
        vista.limpiarValoresApostados();
        vista.inhabilitarPantallaMesa();
    }
    
    private void salirDeMesaPorCierre() {
        participante.getJugador().abandonarParticipacionPorCierre(participante);
        vista.avisarCierreMesa();
        vista.cerrarVentanaMesa();
    }
    
    private void reanudar() {
        vista.limpiarOcurrencias();
        this.actualizarHistoricoRondas();
        this.mostrarSaldoActual();
        vista.habilitarPantallaMesa();
        vista.deshabilitarCasilleros();
        this.habilitarTiposApuesta();
        vista.actualizarNroRonda(mesa.getNroRondaActual());
    }
    
    private void inicializarMesa() {
        vista.mostrarNroMesa(this.mesa.getNroMesa());
        vista.deshabilitarCasilleros();
        this.habilitarTiposApuesta();
        this.mostrarSaldoActual();
    }
    
    private void establecerMontoSegunCasillero(Casillero casillero) {
        vista.mostrarValorApostado(casillero.getCellCode(), participante.getMontoApostadoSegunCasillero(casillero));
    }
    
    private void mostrarUltimoNumeroSorteado() {
        vista.mostrarUltimoNroSorteado(mesa.getUltimoNumeroSorteado());
    }
    
   private void actualizarHistoricoRondas() {
        int nroRonda = mesa.getNroUltimaRonda();
        int totalApostado = mesa.getUltimaRonda().getMontoTotalApostadoSegunParticipante(participante);
        int montoGanado = mesa.getUltimaRonda().getMontoGanadoSegunParticipante(participante);
        int montoPerdido = mesa.getUltimaRonda().getMontoPerdidoSegunParticipante(participante);
        int balance = mesa.getUltimaRonda().getBalanceSegunParticipante(participante);
        vista.popularHistoricoRondas(nroRonda, totalApostado, montoGanado, montoPerdido, balance);
        
        for (Casillero casillero : mesa.obtenerTodosLosCasilleros()) {
            vista.recibirOcurrencia(casillero.getCellCode(), mesa.calcularOcurrencia(casillero));
        }
   }
    
    private void mostrarSaldoActual() {
        vista.mostrarSaldoActual(participante.getJugador().getSaldo());
    }
    
    private void habilitarTiposApuesta() {
        for (TipoApuesta tipoApuesta : this.mesa.getTiposApuesta()) {
            this.habilitarCasilleros(tipoApuesta);
        }
    }
    
    private void habilitarCasilleros(TipoApuesta tipoApuesta) {
        for (Casillero casillero : tipoApuesta.getCasillerosDisponibles()) {
            vista.habilitarCasillero(casillero.getCellCode());
        }
    }
}
