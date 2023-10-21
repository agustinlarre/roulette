/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public class ApuestaColores extends TipoApuesta {
    
    public ApuestaColores() {
        this.nombreTipo = "Apuesta por colores";
        this.factorPago = 2;
    }

    @Override
    public int getFactorPago() {
        return this.factorPago;
    }
    
}
