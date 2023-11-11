/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.UsuarioException;
import logicaNegocio.Sesion;
import servicios.Fachada;
import presentacion.vistas.VistaLogin;

/**
 *
 * @author Agustin
 */
public class LoginJugadorControlador extends LoginControlador {
    
    public LoginJugadorControlador(VistaLogin vista) {
        super(vista);
        this.getVista().setearTitulo( "Login jugador...");
    }

    @Override
    public Sesion login(String cedula, String password) throws UsuarioException {
        return Fachada.getInstancia().loginJugador(cedula, password);
    }

    @Override
    public void ejecutarProximoCasoDeUso(Sesion sesionActual) {
        this.getVista().proximoCU(sesionActual);
    }
    
}
