/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.AbandonarMesaEnPausaException;
import excepcionesSistema.ApuestaException;
import excepcionesSistema.ApuestaInvalidaException;
import excepcionesSistema.ApuestasEnProgresoException;
import excepcionesSistema.MartingalaException;
import excepcionesSistema.MesaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.MesaPausadaException;
import excepcionesSistema.SaldoInsuficienteException;
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
            checkApuestaParticipante(apuesta);
            this.apuestas.add(apuesta);
            this.mesa.recibirApuesta(apuesta);
            this.notificar(Observador.Evento.APUESTA_REALIZADA);
        } catch (MesaPausadaException ex1) {
            throw new ApuestaException("No puede realizar apuestas mientras la mesa se encuentre pausada.");
        } catch (SaldoInvalidoException ex2) {
            throw new ApuestaException("No se han ingresado fichas.");
        } catch (SaldoInsuficienteException ex3) {
            throw new ApuestaException("No tiene saldo suficiente para realizar esta apuesta.");
        } catch (MartingalaException ex4) {
            throw new ApuestaException("No puede recurrir a la estrategia martingala.");
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
    
    private void checkApuestaParticipante(Apuesta apuesta) throws SaldoInvalidoException, SaldoInsuficienteException, MartingalaException {
        validarExistenciaFichas(apuesta);
        validarSaldoParaApuesta(apuesta);
        validarMartingala(apuesta);
    }
    
    private void validarMartingala(Apuesta apuesta) throws MartingalaException {
        List<Apuesta> apuestasParticipante = this.apuestas;
        if (!apuestasParticipante.isEmpty()) {
            Apuesta ultimaApuesta = apuestasParticipante.get(this.apuestas.size()-1);
            if (ultimaApuesta.getCasillero().equals(apuesta.getCasillero()) && apuesta.getMonto() == (ultimaApuesta.getMonto()*2)) {
                throw new MartingalaException();
            }
        }
    }
    
    private void validarSaldoParaApuesta(Apuesta apuesta) throws SaldoInsuficienteException {
        if (this.getJugador().getSaldo() - apuesta.getMonto() < 0) throw new SaldoInsuficienteException();
    }
    
    private void validarExistenciaFichas(Apuesta apuesta) throws SaldoInvalidoException {
        if (apuesta.getFichas().isEmpty()) throw new SaldoInvalidoException();
    }
}
