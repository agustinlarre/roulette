/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.MesaNoSeleccionadaException;
import java.util.ArrayList;
import java.util.List;

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
}
