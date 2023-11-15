/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import java.util.ArrayList;
import java.util.List;
import servicios.Observador.Evento;

/**
 *
 * @author Agustin
 */
public abstract class Observable {
    private List<Observador> subscriptores;
    
    public Observable() {
        subscriptores = new ArrayList();
    }
    
    public void subscribir(Observador e) {
        subscriptores.add(e);
    }
    
    public void desubscribir(Observador e) {
        subscriptores.remove(e);
    }
    
    public void notificar(Evento evento) {
        List<Observador> copiaSubscriptores = new ArrayList<>(subscriptores);
        for (Observador obs : copiaSubscriptores) {
            obs.actualizar(this, evento);
        }
    }
}
