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
public class ModoAleatorioCompleto extends Efecto {
    
    public ModoAleatorioCompleto() {
    }
    
    @Override
    public String getNombreEfecto() {
        return "Modo aleatorio completo";
    }

    @Override
    public int sortear(List<Casillero> listaCasillerosNumeros, List<Integer> listaNumerosSorteados, List<Integer> listaNumerosApostados) {
        List<Integer> listaNumeros = new ArrayList();
        listaNumeros.add(0);
        for (Casillero casillero : listaCasillerosNumeros) {
            // Se asume que al ser un único número vinculado, se obtiene la posición 0
            listaNumeros.add(casillero.getNumerosVinculados().get(0));
        }
        Random random = new Random();
        int randomNum = listaNumeros.get(random.nextInt(listaNumeros.size()));
        return randomNum;
    }
    
}
