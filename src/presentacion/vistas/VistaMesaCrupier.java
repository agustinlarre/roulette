/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

import java.util.List;
import logicaNegocio.Casillero;
import logicaNegocio.Efecto;

/**
 *
 * @author Agustin
 */
public interface VistaMesaCrupier {
    public void deshabilitarCasilleros();
    public void inhabilitarPantallaMesa();
    public void habilitarPantallaMesa();
    public void mostrarNroMesa(int nroMesa);
    public void habilitarCasillero(int cellCode);
    public void agregarEfecto(Efecto efecto);
    public void actualizarNroRonda(int nroRonda);
    public void actualizarListaParticipantes();
    //Posible rename
    public void mostrarUltimoNroSorteado(int ultimoNum);
    public void mostrarUltimosLanzamientos(String ultimosLanzamientos);
}
