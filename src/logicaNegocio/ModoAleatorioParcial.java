/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Agustin
 */
public class ModoAleatorioParcial extends Efecto {

    public ModoAleatorioParcial() {
    }

    @Override
    public String getNombreEfecto() {
        return "Modo aleatorio parcial";
    }

    @Override
    public int sortear(List<Casillero> listaCasillerosNumeros, List<Integer> listaNumerosSorteados, List<Integer> listaNumerosApostados) {
        List<Integer> listaNumeros = new ArrayList();
        listaNumeros.add(0);
        for (Casillero casillero : listaCasillerosNumeros) {
            // Se asume que al ser un único número vinculado, se obtiene la posición 0
            listaNumeros.add(casillero.getNumerosVinculados().get(0));
        }
        List<Integer> ultimosTresNumeros = new ArrayList();
        ultimosTresNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-1));
        ultimosTresNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-2));
        ultimosTresNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-3));
        Random random = new Random();
        int randomNum;
        do {
            randomNum = listaNumeros.get(random.nextInt(listaNumeros.size()));
        } while (ultimosTresNumeros.contains(randomNum));
        
        return randomNum;
    }
    
}
