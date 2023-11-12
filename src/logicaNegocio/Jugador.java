/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.MesaException;
import excepcionesSistema.MesaNoSeleccionadaException;
import excepcionesSistema.ParticipacionYaExistenteException;
import excepcionesSistema.SaldoInvalidoException;
import java.util.ArrayList;
import java.util.List;
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
        } catch (MesaNoSeleccionadaException ex1) {
            throw new MesaException("Debe seleccionar una mesa.");
        } catch (ParticipacionYaExistenteException ex2) {
            throw new MesaException("El usuario ya está participando en la mesa seleccionada.");
        }
    }
    
    public void abandonarParticipacion(Participante participante) {
        this.participaciones.remove(participante);
    }
    
    private void validarParticipacionEnMesa(Participante participante) throws ParticipacionYaExistenteException {
        for (Participante par : participaciones) {
            if (par.getMesa().equals(participante.getMesa())) throw new ParticipacionYaExistenteException();
        }
    }
}
