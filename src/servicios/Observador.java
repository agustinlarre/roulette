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
        LISTA_MESAS_MODIFICADA,
        MESA_CERRADA,
        MESA_PAUSADA,
        RONDA_REANUDADA,
        PAGO_REALIZADO,
        PARTICIPANTE_AGREGADO,
        PARTICIPANTE_ELIMINADO,
        JUGADOR_DESLOGUEADO,
        APUESTA_REALIZADA,
        APUESTA_MODIFICADA,
        FICHA_AGREGADA
    }
    
    public void actualizar(Observable origen, Object evento);

}
