/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public class ApuestaDocenas extends TipoApuesta {
    
    public ApuestaDocenas() {
        this.nombreTipo = "Apuesta por docenas";
        this.factorPago = 3;
    }

    @Override
    public int getFactorPago() {
        return this.factorPago;
    }
    
}
