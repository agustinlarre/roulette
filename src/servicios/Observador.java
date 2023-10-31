/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servicios;

/**
 *
 * @author Agustin
 */
public interface Observador {
    
    public enum Evento {
        MESA_AGREGADA,
        MESA_ELIMINADA,
        MESA_PAUSADA,
        RONDA_LIQUIDADA,
        PARTICIPANTE_AGREGADO,
        SALDO_JUGADOR_MODIFICADO,
    }
    
    public void actualizar(Observable origen, Object evento);

}
