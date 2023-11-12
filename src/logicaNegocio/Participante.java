/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.AbandonarMesaEnPausaException;
import excepcionesSistema.ApuestaException;
import excepcionesSistema.ApuestaInvalidaException;
import excepcionesSistema.ApuestasEnProgresoException;
import excepcionesSistema.RestriccionTipoApuestaException;
import excepcionesSistema.MesaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.MesaPausadaException;
import excepcionesSistema.SaldoInsuficienteException;
import excepcionesSistema.SaldoInvalidoException;
import java.util.ArrayList;
import java.util.List;
import servicios.Fachada;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class Participante {
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
    public void realizarApuesta(Casillero casillero, Ficha ficha) throws ApuestaException {
        // implementar manejo de excepciones (Ej: saldo insuficiente, no se selecciono una ficha, etc.)
        try {
            Apuesta apuesta = this.getApuestaExistente(casillero);
            if (apuesta == null) {
                apuesta = new Apuesta();
                apuesta.setCasillero(casillero);
                checkFichaParticipante(ficha);
                apuesta.addFicha(ficha);
                apuesta.validarRestriccionesApuesta(this);
                actualizarSaldoDuranteApuesta(ficha);
                this.apuestas.add(apuesta);
                this.mesa.recibirApuesta(apuesta);
            } else {
                checkFichaParticipante(ficha);
                apuesta.addFicha(ficha);
                apuesta.validarRestriccionesApuesta(this);
                actualizarSaldoDuranteApuesta(ficha);
                Fachada.getInstancia().notificar(Observador.Evento.APUESTA_MODIFICADA);
                //this.mesa.modificarApuesta(apuesta, ficha);
            }
        } catch (MesaPausadaException ex1) {
            throw new ApuestaException("No puede realizar apuestas mientras la mesa se encuentre pausada.");
        } catch (SaldoInvalidoException ex2) {
            throw new ApuestaException("No se han ingresado fichas.");
        } catch (SaldoInsuficienteException ex3) {
            throw new ApuestaException("No tiene saldo suficiente para realizar esta apuesta.");
        } catch (RestriccionTipoApuestaException ex4) {
            // Como se recurre a un método de validación polimórfico, el mensaje puede variar dependiendo de la subclase (TipoApuesta)
            throw new ApuestaException(ex4.getMessage());
        }
    }
    
//    private void modificarApuestaDeParticipante(Apuesta apuesta, Ficha ficha) {
//        this.mesa.getRondaActual().agregarNuevoValorApuesta(apuesta, ficha);
//    }
    
    public List<Apuesta> getApuestasPerdedorasUltimaRonda() {
        if (mesa.tieneLanzamientos()) {
            List<Apuesta> listaApuestas = new ArrayList();
            List<Apuesta> apuestasPerdedorasRondaAnterior = this.mesa.getUltimaRonda().getApuestasPerdedoras();
            for (Apuesta apuesta : apuestasPerdedorasRondaAnterior) {
                if (this.apuestas.contains(apuesta)) listaApuestas.add(apuesta);
            }
            return listaApuestas;
        }
        return null;
    }
    
    public List<Apuesta> getApuestasEnCurso() {
        List<Apuesta> listaApuestas = new ArrayList();
        List<Apuesta> listaApuestasRondaActual = this.mesa.getRondaActual().getListaApuestas();
        for (Apuesta apuesta : listaApuestasRondaActual) {
            if (this.apuestas.contains(apuesta)) listaApuestas.add(apuesta);
        }
        return listaApuestas;
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
    
    public int getMontoApostadoSegunCasillero(Casillero casillero) {
        for (Apuesta apuesta : apuestas) {
            if (apuesta.getCasillero().equals(casillero)) return apuesta.getMonto();
        }
        return 0;
    }
        
    private void actualizarSaldoDuranteApuesta(Ficha ficha) {
        this.jugador.setSaldo(jugador.getSaldo() - ficha.getValor());
    }
    
    private Apuesta getApuestaExistente(Casillero casillero) {
        for (Apuesta apuesta : this.getApuestasEnCurso()) {
            if (apuesta.getCasillero().equals(casillero)) return apuesta;
        }
        return null;
    }
    
    private void checkFichaParticipante(Ficha ultimaFicha) throws SaldoInvalidoException, SaldoInsuficienteException {
        validarExistenciaFicha(ultimaFicha);
        validarSaldoParaApuesta(ultimaFicha);
    }
    
    private void validarSaldoParaApuesta(Ficha ultimaFicha) throws SaldoInsuficienteException {
        if (this.getJugador().getSaldo() - ultimaFicha.getValor() < 0) throw new SaldoInsuficienteException();
    }
    
    private void validarExistenciaFicha(Ficha ficha) throws SaldoInvalidoException {
        if (ficha == null) throw new SaldoInvalidoException();
    }
}
