/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.util.ArrayList;
import logicaNegocio.Mesa;
import logicaNegocio.TipoApuesta;
import logicaNegocio.Casillero;
import logicaNegocio.Efecto;

/**
 *
 * @author agust
 */
public class ServicioMesas {
    private ArrayList<Mesa> listaMesas;
    private ArrayList<TipoApuesta> tiposApuesta;
    private ArrayList<Casillero> listaCasilleros;
    private ArrayList<Efecto> listaEfectos;

    public ServicioMesas() {
        listaMesas = new ArrayList();
        tiposApuesta = new ArrayList();
    }
    
    public void addMesa(Mesa mesa) {
        listaMesas.add(mesa);
    }
    
    public void addTipoApuesta(TipoApuesta tipoApuesta) {
        tiposApuesta.add(tipoApuesta);
    }
    
    public ArrayList<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    public void addEfecto(Efecto efecto) {
        this.listaEfectos.add(efecto);
    }
    
    public ArrayList<Efecto> getEfectos() {
        return this.listaEfectos;
    }
    
    public void addCasillero(Casillero casillero) {
        this.listaCasilleros.add(casillero);
    }
    
    public ArrayList<Casillero> getCasilleros() {
        return this.listaCasilleros;
    }
}
