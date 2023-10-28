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

    public Casillero(int cellCode) {
        this.cellCode = cellCode;
        this.setNumerosVinculados();
    }

    public int getCellCode() {
        return cellCode;
    }
    
    private void setNumerosVinculados() {
        switch (cellCode) {
            case 40: 
                break;
            default: 
                break;
        } 
    }

}
