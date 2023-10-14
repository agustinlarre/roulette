/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.util.ArrayList;
import logicaNegocio.*;

/**
 *
 * @author agust
 */
public class ServicioMesas {
    private ArrayList<Mesa> listaMesas;

    public ServicioMesas() {
        listaMesas = new ArrayList();
    }
    
    public void agregarMesa(Mesa mesa) {
        listaMesas.add(mesa);
    }
}
