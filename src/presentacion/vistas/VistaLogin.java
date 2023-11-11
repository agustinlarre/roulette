/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package presentacion.vistas;

import logicaNegocio.Sesion;

/**
 *
 * @author Agustin
 */
public interface VistaLogin {
    public void setearTitulo(String titulo);
    public void mostrarError(String mensaje);
    public void cerrar();
    public void proximoCU(Sesion sesionActual);
}
