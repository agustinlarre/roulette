/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.UsuarioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocio.Sesion;
import logicaNegocio.Usuario;
import servicios.Fachada;
import presentacion.vistas.VistaLogin;

/**
 *
 * @author Agustin
 */
public class LoginCrupierControlador extends LoginControlador {
    
    public LoginCrupierControlador(VistaLogin vista) {
        super(vista);
        this.getVista().setearTitulo( "Login crupier...");
    }

    @Override
    public Sesion login(String cedula, String password) throws UsuarioException {
        return Fachada.getInstancia().loginCrupier(cedula, password);
    }

    @Override
    public void ejecutarProximoCasoDeUso(Sesion sesionActual) {
        this.getVista().proximoCU(sesionActual);
    }

}
