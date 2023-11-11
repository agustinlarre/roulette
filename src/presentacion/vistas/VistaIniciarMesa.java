/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

import java.util.List;
import logicaNegocio.Sesion;
import logicaNegocio.TipoApuesta;

/**
 *
 * @author Agustin
 */
public interface VistaIniciarMesa {
    public void proximoCU(Sesion sesionActual);
    public void mostrarError(String mensaje);
    public void obtenerTiposApuesta(List<TipoApuesta> tiposApuesta);
    public void cerrarVentana();
}
