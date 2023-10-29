/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.List;

/**
 *
 * @author Agustin
 */
public class Casillero {
    
    private List<Integer> numerosVinculados;
    private int cellCode;

    public Casillero(int cellCode, List<Integer> numerosVinculados) {
        this.cellCode = cellCode;
        this.numerosVinculados = numerosVinculados;
    }

    public int getCellCode() {
        return cellCode;
    }

    public List<Integer> getNumerosVinculados() {
        return numerosVinculados;
    }
}
