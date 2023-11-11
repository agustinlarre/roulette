/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

import java.util.List;
import logicaNegocio.Mesa;
import logicaNegocio.Participante;

/**
 *
 * @author Agustin
 */
public interface VistaUnirseMesa {
    public void mostrarError(String mensaje);
    public void obtenerMesasDisponibles(List<Mesa> listaMesas);
    public void proximoCU(Participante participante);
    public void cerrarVentana();
}
