/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.RestriccionTipoApuestaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agustin
 */
public final class ApuestaDirecta extends TipoApuesta {

    public ApuestaDirecta() {
        this.factorPago = 36;
        setCasillerosDisponibles();
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta directa";
    }

    @Override
    protected void setCasillerosDisponibles() {
        for (int i = 0; i <= 36; i++) {
            List<Integer> lista = new ArrayList();
            lista.add(i);
            listaCasilleros.add(new Casillero(i, lista, this));
        }
    }

//Apuesta Directa: Apostar a un número específico (incluyendo el cero).
//Pago: 36 a 1. No hay límites ni restricciones
//de apuestas.

    @Override
    public void validarApuestaSegunTipo(Participante participante, Apuesta apuestaActual) throws RestriccionTipoApuestaException {
        //
    }
    
}
