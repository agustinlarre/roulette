/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.ApuestaException;
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
            this.salirDeMesa();
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
    
    private void pausar() {
        this.mostrarUltimoNumeroSorteado();
        vista.inhabilitarPantallaMesa();
        vista.limpiarValoresApostados();
    }
    
    private void salirDeMesa() {
        vista.cerrarVentanaMesa();
    }
    
    private void reanudar() {
        this.mostrarSaldoActual();
        vista.actualizarNroRonda(mesa.getNroRondaActual());
        vista.habilitarPantallaMesa();
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
