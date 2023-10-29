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
public class Ronda {
    private int numeroSorteado;
    private Efecto efecto;
    private List<Apuesta> listaApuestas;

    public Ronda(Efecto efecto) {
        this.efecto = efecto;
        this.listaApuestas = new ArrayList();
    }

    public Efecto getEfecto() {
        return efecto;
    }

    public void setEfecto(Efecto efecto) {
        this.efecto = efecto;
    }

    public int getNumeroSorteado() {
        return numeroSorteado;
    }
    
    public void sortearNumero(List<Casillero> listaNumerosApuestaDirecta, List<Integer> listaNumerosSorteados) {
        // Preguntar apuesta -> casillero.esApuestaDirecta()
        // listaCasillerosNumeros -> todos los nros del 1 al 36
        // listaNumerosSorteados -> historial de numeros sorteados por cada ronda (proveniente de la mesa)
        // listaNumerosApostados -> lista de numeros que SI se apostaron en esta ronda
        
        this.numeroSorteado = this.efecto.sortear(listaNumerosApuestaDirecta, listaNumerosSorteados, getTotalNumerosApostados());
    }
    
    // Apuesta llegada desde el manejo de eventos de la MesaCrupier
    public void recibirApuesta(Apuesta apuesta) {
        // Excepciones
        this.listaApuestas.add(apuesta);
    }
    
    private List<Integer> getTotalNumerosApostados() {
        List<Integer> listaNum = new ArrayList();
        for (Apuesta apuesta : listaApuestas) {
            for (int num : apuesta.getCasillero().getNumerosVinculados()) {
                // Se verifica que el número no haya sido agregado anteriormente
                if (!listaNum.contains(num)) {
                    listaNum.add(num);
                }
            }
        }
        return listaNum;
    }
}
