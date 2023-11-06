/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.AbandonarMesaEnPausaException;
import excepcionesSistema.ApuestaException;
import excepcionesSistema.ApuestaInvalidaException;
import excepcionesSistema.ApuestasEnProgresoException;
import excepcionesSistema.MesaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.SaldoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class Participante extends Observable {
    private Jugador jugador;
    private Mesa mesa;
    private List<Apuesta> apuestas;

    public Participante(Jugador jugador, Mesa mesa) {
        this.jugador = jugador;
        this.mesa = mesa;
        this.apuestas = new ArrayList();
    }
    
    public void validar() throws MesaNoSeleccionadaException {
        if (this.mesa == null) throw new MesaNoSeleccionadaException();
        // Contemplar posibles excepciones
        mesa.addParticipante(this);
    }

    public List<Apuesta> getApuestas() {
        return apuestas;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mesa getMesa() {
        return mesa;
    }
    
    // Caso de uso en el que el participante elige un casillero
    public void realizarApuesta(Apuesta apuesta) throws ApuestaException {
        // implementar manejo de excepciones (Ej: saldo insuficiente, no se selecciono una ficha, etc.)
        try {
            validarExistenciaFichas(apuesta);
            this.apuestas.add(apuesta);
            this.mesa.getRondaActual().recibirApuesta(apuesta);
            this.notificar(Observador.Evento.APUESTA_REALIZADA);
        } catch (SaldoInvalidoException ex1) {
            throw new ApuestaException("No se han ingresado fichas.");
        }
      
    }
    
    public void modificarApuestaDeParticipante(Apuesta apuesta, Ficha ficha) throws ApuestaException {
        try {
            this.mesa.getRondaActual().agregarNuevoValorApuesta(apuesta, ficha);
        } catch (ApuestaInvalidaException ex1) {
            throw new ApuestaException("La apuesta que intenta modificar no existe.");
        }
    }
    
    public void abandonarMesa() throws MesaException {
        // Preguntar si es necesario enviar una UsuarioException en el caso de que se quiera borrar un usuario inexistente
        try {
            this.mesa.removeParticipante(this);
        } catch(AbandonarMesaEnPausaException ex1) {
            throw new MesaException("No se puede abandonar la mesa mientras esté pausada.");
        } catch (ApuestasEnProgresoException ex2) {
            throw new MesaException("No se puede abandonar la mesa mientras tengas apuestas en curso.");
        }
    }
    
    private void validarExistenciaFichas(Apuesta apuesta) throws SaldoInvalidoException {
        if (apuesta.getFichas().isEmpty()) throw new SaldoInvalidoException();
    }
}
