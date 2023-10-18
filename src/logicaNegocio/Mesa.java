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
        Efecto aleatorioCompleto = new Efecto("Aleatorio completo");
        Efecto aleatorioParcial = new Efecto("Aleatorio parcial");
        Efecto simulador = new Efecto("Simulador");
        this.listaEfectos.add(aleatorioCompleto);
        this.listaEfectos.add(aleatorioParcial);
        this.listaEfectos.add(simulador);
    }
}
