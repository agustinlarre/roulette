/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.ApuestaException;
import excepcionesSistema.ApuestaInvalidaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.SaldoInsuficienteException;
import excepcionesSistema.SaldoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public void modificarApuesta(Apuesta apuesta, Ficha ficha) throws ApuestaException {
        try {
            this.mesa.getRondaActual().agregarNuevoValorApuesta(apuesta, ficha);
        } catch (ApuestaInvalidaException ex1) {
            throw new ApuestaException("La apuesta que intenta modificar no existe.");
        }
    }
    
    private void validarExistenciaFichas(Apuesta apuesta) throws SaldoInvalidoException {
        if (apuesta.getFichas().isEmpty()) throw new SaldoInvalidoException();
    }
}
