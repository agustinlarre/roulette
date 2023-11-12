/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import excepcionesSistema.ApuestaException;
import excepcionesSistema.RestriccionTipoApuestaException;
import java.util.ArrayList;
import java.util.List;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author agust
 */
public class Apuesta {
    private int monto;
    private Casillero casillero;
    private List<Ficha> fichas;

    public Apuesta() {
        this.fichas = new ArrayList();
    }

    public int getMonto() {
        return monto;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public void addFicha(Ficha ficha) {
        this.fichas.add(ficha);
        this.monto += ficha.getValor();
    }

    public Casillero getCasillero() {
        return casillero;
    }

    public void setCasillero(Casillero casillero) {
        this.casillero = casillero;
    }
    
    public void validarRestriccionesApuesta(Participante participante) throws RestriccionTipoApuestaException {
        this.casillero.getTipoApuesta().validarApuestaSegunTipo(participante, this);
    }
    
}
