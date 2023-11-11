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
 * @author agust
 */
public abstract class TipoApuesta {
    protected List<Casillero> listaCasilleros;
    protected int factorPago;

    public TipoApuesta() {
        this.listaCasilleros = new ArrayList();
    }
    
    public int getFactorPago() {
        return this.factorPago;
    }
    
    public List<Casillero> getCasillerosDisponibles() {
        return this.listaCasilleros;
    }
    
    protected abstract void setCasillerosDisponibles();
    
    public abstract String getNombreTipo();
    
    public abstract void validarApuestaSegunTipo(Participante participante, Apuesta apuestaActual) throws RestriccionTipoApuestaException;
}
