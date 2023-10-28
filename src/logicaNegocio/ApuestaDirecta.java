/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

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
            listaCasilleros.add(new Casillero(i));
        }
    }
    
}
