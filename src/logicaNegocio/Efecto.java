/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.List;

/**
 *
 * @author agust
 */
public abstract class Efecto {
    
    public abstract String getNombreEfecto();
    
    public abstract int sortear(List<Casillero> listaCasillerosNumeros, List<Integer> listaNumerosSorteados, List<Integer> listaNumerosApostados);
}
