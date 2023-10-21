/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public class ApuestaDirecta extends TipoApuesta {

    public ApuestaDirecta() {
        this.nombreTipo = "Apuesta directa";
        this.factorPago = 36;
    }

    @Override
    public int getFactorPago() {
        return this.factorPago;
    }
    
}
