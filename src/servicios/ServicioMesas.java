/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import excepcionesSistema.MesaException;
import excepcionesSistema.TipoApuestaObligatoriaException;
import excepcionesSistema.TiposApuestaVaciaException;
import java.util.ArrayList;
import java.util.List;
import logicaNegocio.Mesa;
import logicaNegocio.TipoApuesta;
import logicaNegocio.Casillero;
import logicaNegocio.Efecto;
import logicaNegocio.Ficha;
import logicaNegocio.Jugador;

/**
 *
 * @author agust
 */
public class ServicioMesas {
    private List<Mesa> listaMesas;
    private List<TipoApuesta> tiposApuesta;
    private List<Casillero> listaCasilleros;
    List<Ficha> listaFichas;
    private List<Efecto> listaEfectos;

    public ServicioMesas() {
        listaMesas = new ArrayList();
        tiposApuesta = new ArrayList();
        listaEfectos = new ArrayList();
        listaFichas = new ArrayList();
        listaCasilleros = new ArrayList();
    }
    
    public void addMesa(Mesa mesa) throws MesaException {
        try {
            mesa.validar();
            listaMesas.add(mesa);
            Fachada.getInstancia().notificar(Observador.Evento.LISTA_MESAS_MODIFICADA);
        } catch(TiposApuestaVaciaException ex1) {
            throw new MesaException("Debe seleccionar al menos un tipo de apuesta, recuerde que el tipo de apuesta 'Directa' es obligatorio.");
        } catch(TipoApuestaObligatoriaException ex2) {
            throw new MesaException("El tipo de apuesta directa debe ser obligatoria.");
        }
    }
    
    public void removeMesa(Mesa mesa) {
        //Camino feliz -implementar cambios con excepciones-
        listaMesas.remove(mesa);
        Fachada.getInstancia().notificar(Observador.Evento.LISTA_MESAS_MODIFICADA);
    }
    
    public List<Mesa> getMesas() {
        return this.listaMesas;
    }
    
    public void addTipoApuesta(TipoApuesta tipoApuesta) {
        tiposApuesta.add(tipoApuesta);
    }
    
    public List<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    public void addEfecto(Efecto efecto) {
        this.listaEfectos.add(efecto);
    }
    
    public List<Efecto> getEfectos() {
        return this.listaEfectos;
    }
    
    public void addCasillero(Casillero casillero) {
        this.listaCasilleros.add(casillero);
    }
    
    public List<Casillero> getCasilleros() {
        return this.listaCasilleros;
    }
    
    public Casillero getCasilleroSegunCellCode(int cellCode) {
        for (Casillero casillero : listaCasilleros) {
            if (casillero.getCellCode() == cellCode) return casillero;
        }
        return null;
    }
    
    public List<Ficha> getFichas() {
        return this.listaFichas;
    }
    
    public void addFicha(Ficha ficha) {
        this.listaFichas.add(ficha);
    }
    
    public Ficha getFichaSegunValor(int valor) {
        for (Ficha ficha : listaFichas) {
            if (ficha.getValor() == valor) return ficha;
        }
        return null;
    }
}
