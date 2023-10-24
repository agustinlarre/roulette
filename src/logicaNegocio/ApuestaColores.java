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
public class ApuestaColores extends TipoApuesta {
    
    public ApuestaColores() {
        this.factorPago = 2;
    }

    @Override
    public List<Integer> getUniversalCellCodes() {
        List<Integer> listaCellCodes = new ArrayList();
        listaCellCodes.add(43);
        listaCellCodes.add(44);
        return listaCellCodes;
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por colores";
    }
}
