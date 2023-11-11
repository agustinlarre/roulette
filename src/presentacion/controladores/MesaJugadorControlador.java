/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.ApuestaException;
import logicaNegocio.Apuesta;
import logicaNegocio.Casillero;
import logicaNegocio.Ficha;
import logicaNegocio.Mesa;
import logicaNegocio.Participante;
import logicaNegocio.Sesion;
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
        
        this.inicializarMesa();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        // Cambiar condiciones anidadas
        if (evento.equals(Evento.MESA_PAUSADA)) {
            this.pausar();
        } else if (evento.equals(Evento.RONDA_LIQUIDADA)) {
            this.reanudar();
        }
    }
    
    public void apostar(int cellCode) {
        //Paso int de valor de ficha o el objeto ficha???
        Casillero casillero = this.mesa.getCasilleroSegunCellCode(cellCode);
        try {
            participante.realizarApuesta(casillero, fichaSeleccionada);
            this.mostrarSaldoActual();
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
        this.mostrarSaldoActual();
        vista.inhabilitarPantallaMesa();
    }
    
    private void reanudar() {
        vista.actualizarNroRonda(mesa.getNroRondaActual());
        vista.habilitarPantallaMesa();
    }
    
    private void inicializarMesa() {
        vista.mostrarNroMesa(this.mesa.getNroMesa());
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
}
