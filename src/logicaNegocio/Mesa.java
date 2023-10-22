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
    private ArrayList<Efecto> listaEfectos;
    private ArrayList<Casillero> listaCasilleros;

    public Mesa(ArrayList<TipoApuesta> tiposApuesta) {
        this.tiposApuesta = tiposApuesta;
        this.listaEfectos = new ArrayList();
        hidratarListaEfectos();
    }

    public ArrayList<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    // Metodos
    public void setTipoApuesta(TipoApuesta tipo) {
        this.tiposApuesta.add(tipo);
    }
    
    private void hidratarListaEfectos() {
        
    }
    
//    public static ArrayList<Casillero> getCasillerosBase() {
//        
//    }
}
