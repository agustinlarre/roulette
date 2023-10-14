/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.ArrayList;

/**
 *
 * @author agust
 */
public class Jugador extends Usuario {
    private String cedula;
    private String password;
    private int saldoInicial;
    private ArrayList<Mesa> mesas;

    public Jugador(String cedula, String password) {
        super(cedula, password);
        this.mesas = new ArrayList();
    }

    public int getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(int saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
    
    public void setMesa(Mesa mesa) {
        boolean mesaEncontrada = false;
        for (Mesa m : mesas) {
            if (m.equals(mesa)) mesaEncontrada = true;
        }
        if (mesaEncontrada) {
            // Mostrar en panel "El jugador ya participa de esta mesa."
        } else {
            mesas.add(mesa);
        }
    }
    
    public void jugar() {
        
    }
}
