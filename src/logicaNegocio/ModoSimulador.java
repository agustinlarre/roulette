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
public class ModoSimulador extends Efecto {
    
    public ModoSimulador() {
    }
    
    @Override
    public String getNombreEfecto() {
        return "Modo simulador";
    }

    @Override
    public int sortear(List<Casillero> listaCasillerosNumeros, List<Integer> listaNumerosSorteados, List<Integer> listaNumerosApostados) {
        List<Integer> listaNumeros = new ArrayList();
        listaNumeros.add(0);
        for (int num : listaNumerosApostados) {
            listaNumeros.add(num);
        }
        Random random = new Random();
        int randomNum = listaNumeros.get(random.nextInt(listaNumeros.size()));
        return randomNum;
    }
    
    
    
}
