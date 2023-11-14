/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.AbandonarMesaEnPausaException;
import excepcionesSistema.ApuestasEnProgresoException;
import excepcionesSistema.MesaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.ParticipacionYaExistenteException;
import java.util.ArrayList;
import java.util.List;
import servicios.Fachada;
import servicios.Observador;

/**
 *
 * @author agust
 */
public class Jugador extends Usuario {
    private String nombre;
    private int saldo;
    private List<Participante> participaciones;

    public Jugador(String nombre, String cedula, String password, int saldoInicial) {
        super(cedula, password);
        this.nombre = nombre;
        this.participaciones = new ArrayList();
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getNombre() {
        return nombre;
    }
    
    public List<Participante> getParticipaciones() {
        return this.participaciones;
    }
    
    public void participar(Participante participante) throws MesaException {
        try {
            participante.validar();
            validarParticipacionEnMesa(participante);
            this.participaciones.add(participante);
            participante.getMesa().addParticipante(participante);
        } catch (MesaNoSeleccionadaException ex1) {
            throw new MesaException("Debe seleccionar una mesa.");
        } catch (ParticipacionYaExistenteException ex2) {
            throw new MesaException("El usuario ya está participando en la mesa seleccionada.");
        }
    }
    
    public void logoff() throws MesaException {
        try {
            validarMesasReanudadas();
            validarMesasSinApuestas();
            for (int i = 0; i < participaciones.size(); i++) {
                this.abandonarParticipacion(participaciones.get(i), true);
            }
            this.participaciones = new ArrayList();
            Fachada.getInstancia().notificar(Observador.Evento.JUGADOR_DESLOGUEADO);
        }
        catch (AbandonarMesaEnPausaException ex1) {
            throw new MesaException("Alguna de las mesas se encuentra pausada.");
        } catch (ApuestasEnProgresoException ex2) {
            throw new MesaException("Alguna de las mesas tiene apuestas en curso.");
        }
        
    }
    
    public void abandonarParticipacion(Participante participante, boolean hayLogoff) throws MesaException {
        participante.abandonarMesa(hayLogoff);
    }
    
    public void abandonarParticipacionPorCierre(Participante participante) {
        this.participaciones.remove(participante);
    }
    
    private void validarMesasReanudadas() throws AbandonarMesaEnPausaException {
        for (Participante participante : participaciones) {
            participante.getMesa().validarPausa();
        }
    }
    
    private void validarMesasSinApuestas() throws ApuestasEnProgresoException {
        for (Participante participante : participaciones) {
            participante.getMesa().validarNoHayApuestasEnCursoDeParticipante(participante);
        }
    }
    
    private void validarParticipacionEnMesa(Participante participante) throws ParticipacionYaExistenteException {
        for (Participante par : participaciones) {
            if (par.getMesa().equals(participante.getMesa())) throw new ParticipacionYaExistenteException();
        }
    }
}
