/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public final class ApuestaColores extends TipoApuesta {
    
    public ApuestaColores() {
        this.factorPago = 2;
        setCasillerosDisponibles();
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por colores";
    }

    @Override
    protected void setCasillerosDisponibles() {
        this.listaCasilleros.add(new Casillero(43));
        this.listaCasilleros.add(new Casillero(44));
    }
}
