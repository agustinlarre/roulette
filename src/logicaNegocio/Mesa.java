/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.ArrayList;

/**
 *
 * @author agust
 */
public class Mesa {
    private ArrayList<TipoApuesta> tiposApuesta;
    private Efecto efecto;

    public Mesa() {
    }

    public ArrayList<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    // Metodos
    public void setTipoApuesta(TipoApuesta tipo) {
        this.tiposApuesta.add(tipo);
    }

    public Efecto getEfecto() {
        return efecto;
    }

    public void setEfecto(Efecto efecto) {
        this.efecto = efecto;
    } 
}
