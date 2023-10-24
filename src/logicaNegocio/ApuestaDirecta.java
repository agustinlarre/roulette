/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Agustin
 */
public class ApuestaDirecta extends TipoApuesta {

    public ApuestaDirecta() {
        this.factorPago = 36;
    }

    @Override
    public List<Integer> getUniversalCellCodes() {
        List<Integer> listaCellCodes = new ArrayList();
        for (int i = 0; i <= 36; i++) {
            listaCellCodes.add(i);
        }
        return listaCellCodes;
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta directa";
    }
    
}
