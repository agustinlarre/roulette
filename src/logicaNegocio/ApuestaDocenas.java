/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public final class ApuestaDocenas extends TipoApuesta {
    
    public ApuestaDocenas() {
        this.factorPago = 3;
        setCasillerosDisponibles();
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por docenas";
    }

    @Override
    protected void setCasillerosDisponibles() {
        this.listaCasilleros.add(new Casillero(40));
        this.listaCasilleros.add(new Casillero(41));
        this.listaCasilleros.add(new Casillero(42));
    }
    
}
