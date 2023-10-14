/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import excepciones.UsuarioException;
import java.util.List;
import logicaNegocio.*;

/**
 *
 * @author agust
 */
public class Fachada {
    private static Fachada instancia;
    private ServicioMesas servicioMesas;
    private ServicioUsuarios servicioUsuarios;
    
    private Fachada() {
        servicioMesas = new ServicioMesas();
        servicioUsuarios = new ServicioUsuarios();
    }
    
    public static Fachada getInstancia() {
        if(instancia == null)
            instancia = new Fachada();
        
        return instancia;
    }
    
    public void agregarJugador(Jugador jugador) {
        this.servicioUsuarios.agregarJugador(jugador);
    }
    
    public void agregarCrupier(Crupier crupier) {
        this.servicioUsuarios.agregarCrupier(crupier);
    }
    
    public Sesion loginJugador(String cedula, String contrasenia) throws UsuarioException {
        return this.servicioUsuarios.loginJugador(cedula, contrasenia);
    }
    
    public Sesion loginCrupier(String cedula, String contrasenia) throws UsuarioException {
        return this.servicioUsuarios.loginCrupier(cedula, contrasenia);
    }
    
    public void agregarMesa(Mesa mesa) {
        this.servicioMesas.agregarMesa(mesa);
    }
    
    public void logout(Sesion sesion) {
        this.servicioUsuarios.logout(sesion);
    }
}
