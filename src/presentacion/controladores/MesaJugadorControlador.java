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
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class MesaJugadorControlador implements Observador {
    private Participante participante;
    private Mesa mesa;
    private VistaMesaJugador vista;

    public MesaJugadorControlador(Participante participante, VistaMesaJugador vista) {
        this.participante = participante;
        this.mesa = participante.getMesa();
        this.vista = vista;
        
        this.inicializarMesa();
    }
    
    @Override
    public void actualizar(Observable origen, Object evento) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public void apostar(int valorFicha, int cellCode) {
        //Paso int de valor de ficha o el objeto ficha???
        Ficha ficha = new Ficha(valorFicha);
        Casillero casillero = this.mesa.getCasilleroSegunCellCode(cellCode);
        Apuesta apuesta = new Apuesta();
        apuesta.addFicha(ficha);
        apuesta.setCasillero(casillero);
        try {
            participante.realizarApuesta(apuesta);
            // Mostrar en ventana la ficha ingresada en el casillero
        } catch (ApuestaException ex) {
            vista.mostrarMensajeError(ex.getMessage());
        }
    }
    
    private void inicializarMesa() {
        
    }
}
