/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.List;

/**
 *
 * @author agust
 */
public abstract class TipoApuesta {
    protected int factorPago;
    
    public int getFactorPago() {
        return this.factorPago;
    }
    
    public abstract List<Integer> getUniversalCellCodes();
    
    public abstract String getNombreTipo();

}
