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
public final class ApuestaDocenas extends TipoApuesta {
    
    public ApuestaDocenas() {
        this.factorPago = 3;
        setCasillerosDisponibles();
    }

    @Override
    public String getNombreTipo() {
        return "Apuesta por docenas";
    }

    @Override
    protected void setCasillerosDisponibles() {
        this.listaCasilleros.add(new Casillero(40, this.listaNumerosPrimeraDocena()));
        this.listaCasilleros.add(new Casillero(41, this.listaNumerosSegundaDocena()));
        this.listaCasilleros.add(new Casillero(42, this.listaNumerosTerceraDocena()));
    }
    
    private List<Integer> listaNumerosPrimeraDocena() {
        List<Integer> lista = new ArrayList();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        lista.add(7);
        lista.add(8);
        lista.add(9);
        lista.add(10);
        lista.add(11);
        lista.add(12);
        return lista;
    }
    
    private List<Integer> listaNumerosSegundaDocena() {
        List<Integer> lista = new ArrayList();
        lista.add(13);
        lista.add(14);
        lista.add(15);
        lista.add(16);
        lista.add(17);
        lista.add(18);
        lista.add(19);
        lista.add(20);
        lista.add(21);
        lista.add(22);
        lista.add(23);
        lista.add(24);
        return lista;
    }
    
    private List<Integer> listaNumerosTerceraDocena() {
        List<Integer> lista = new ArrayList();
        lista.add(25);
        lista.add(26);
        lista.add(27);
        lista.add(28);
        lista.add(29);
        lista.add(30);
        lista.add(31);
        lista.add(32);
        lista.add(33);
        lista.add(34);
        lista.add(35);
        lista.add(36);
        return lista;
    }
}
