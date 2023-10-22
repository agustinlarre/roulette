/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.TipoApuestaObligatoriaException;
import excepcionesSistema.TiposApuestaVaciaException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author agust
 */
public class Mesa {
    private List<TipoApuesta> tiposApuesta;
    private ArrayList<Efecto> listaEfectos;
    private ArrayList<Casillero> listaCasilleros;

    public Mesa(List<TipoApuesta> tiposApuesta) {
        this.tiposApuesta = tiposApuesta;
        this.listaEfectos = new ArrayList();
    }

    public List<TipoApuesta> getTiposApuesta() {
        return tiposApuesta;
    }
    
    // Metodos
    public void setTipoApuesta(TipoApuesta tipo) {
        this.tiposApuesta.add(tipo);
    }
    
    public void validar() throws TipoApuestaObligatoriaException, TiposApuestaVaciaException {
        boolean esValido = false;
        if (this.tiposApuesta.isEmpty()) {
            throw new TiposApuestaVaciaException();
        } else {
            for (TipoApuesta tipo : tiposApuesta) {
                if (tipo.getNombreTipo().equals("Apuesta directa")) esValido = true;
            }
            if (!esValido) throw new TipoApuestaObligatoriaException();
        }
    }
}
