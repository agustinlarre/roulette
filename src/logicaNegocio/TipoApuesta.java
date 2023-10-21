/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author agust
 */
public abstract class TipoApuesta {
    protected String nombreTipo;
    protected int factorPago;

    public String getNombreTipo() {
        return this.nombreTipo;
    }
    
    public abstract int getFactorPago();

}
