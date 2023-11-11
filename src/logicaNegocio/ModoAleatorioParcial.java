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
    public int sortear(Mesa mesa) {
        List<Integer> listaNumeros = new ArrayList();
        List<Casillero> listaCasillerosNumeros = mesa.getCasillerosNumericos();
        List<Integer> listaNumerosSorteados = mesa.getlistaNumerosSorteados();
        for (Casillero casillero : listaCasillerosNumeros) {
            // Se asume que al ser un único número vinculado, se obtiene la posición 0
            listaNumeros.add(casillero.getNumerosVinculados().get(0));
        }
        List<Integer> ultimosNumeros = new ArrayList();
        // Evaluamos si la mesa ya contiene numeros sorteados, en caso de no ser así, se comienza obteniendo cualquier número
        if (listaNumerosSorteados.size() == 1) {
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-1));
            return obtenerNumAleatorioParcial(listaNumeros, ultimosNumeros);
        } else if (listaNumerosSorteados.size() == 2) {
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-1));
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-2));
            return obtenerNumAleatorioParcial(listaNumeros, ultimosNumeros);
        } else if (listaNumerosSorteados.size() >= 3) {
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-1));
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-2));
            ultimosNumeros.add(listaNumerosSorteados.get(listaNumerosSorteados.size()-3));
            return obtenerNumAleatorioParcial(listaNumeros, ultimosNumeros);
        } else {
            return obtenerNumAleatorio(listaNumeros);
        }
    }
    
    private int obtenerNumAleatorioParcial(List<Integer> listaNumeros, List<Integer> ultimosNumerosSorteados) {
        Random random = new Random();
        int randomNum;
        do {
            randomNum = listaNumeros.get(random.nextInt(listaNumeros.size()));
        } while (ultimosNumerosSorteados.contains(randomNum));
        return randomNum;
    }
    
    private int obtenerNumAleatorio(List<Integer> listaNumeros) {
        Random random = new Random();
        int randomNum = listaNumeros.get(random.nextInt(listaNumeros.size()));
        return randomNum;
    }
    
}
