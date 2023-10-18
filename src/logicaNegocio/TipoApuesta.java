/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author agust
 */
public class TipoApuesta {
    private String nombreTipo;
    private int factorPago;

    public TipoApuesta(String tipo) {
        this.establecerFactorPago(tipo);
    }
    
    private void establecerFactorPago(String tipo) {
        switch (tipo) {
            case "Apuesta directa":
                this.factorPago = 36;
                break;
            case "Apuesta colores":
                this.factorPago = 2;
                break;
            case "Apuesta docena":
                this.factorPago = 3;
                break;
            default:
                // Chequear escalabilidad
                System.out.println("Tipo de apuesta inexistente.");
                break;
        }
        this.nombreTipo = tipo;
    }

    public int getFactorPago() {
        return factorPago;
    }

    public String getNombreTipo() {
        return nombreTipo;
    }

}
