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
import presentacion.vistas.VistaLogin;

/**
 *
 * @author Agustin
 */
public abstract class LoginControlador {
    private VistaLogin vista;

    public VistaLogin getVista() {
        return vista;
    }
    
    public LoginControlador(VistaLogin vista) {
        this.vista = vista;
        this.vista.setearTitulo("");
    }
    
    public void usuarioIntentaIngresar(String cedula, String password) {
        try {
            Sesion sesion = this.login(cedula, password);
            this.ejecutarProximoCasoDeUso(sesion);
        } catch (UsuarioException ex) {
            vista.mostrarError(ex.getMessage());
        }
        
    }
    
    protected abstract Sesion login(String cedula, String password) throws UsuarioException;
    
    protected abstract void ejecutarProximoCasoDeUso(Sesion sesion);
        
    public void cerrar() {
        this.vista.cerrar();
    }
}
