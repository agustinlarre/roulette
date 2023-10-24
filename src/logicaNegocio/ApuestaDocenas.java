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
public class ApuestaDocenas extends TipoApuesta {
    
    public ApuestaDocenas() {
        this.factorPago = 3;
    }

    @Override
    public List<Integer> getUniversalCellCodes() {
        List<Integer> listaCellCodes = new ArrayList();
        listaCellCodes.add(40);
        listaCellCodes.add(41);
        listaCellCodes.add(42);
        return listaCellCodes;
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por docenas";
    }
    
}
