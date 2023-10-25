/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNegocio;

import java.util.List;
import logicaNegocio.Observador.Evento;

/**
 *
 * @author Agustin
 */
public abstract class Observable {
    private List<Observador> subscriptores;
    
    public void subscribir(Observador e) {
        subscriptores.add(e);
    }
    
    public void desubscribir(Observador e) {
        subscriptores.remove(e);
    }
    
    public void notificar(Evento evento) {
        for (Observador obs : subscriptores) {
            obs.actualizar(this, evento);
        }
    }
}
