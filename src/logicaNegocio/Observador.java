/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package logicaNegocio;

/**
 *
 * @author Agustin
 */
public interface Observador {
    
    public enum Evento {
        MESA_AGREGADA,
    }
    
    public void actualizar(Observable origen, Object evento);

}
