/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import excepcionesSistema.MesaException;
import excepcionesSistema.UsuarioException;
import java.util.ArrayList;
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
    
    public void addJugador(Jugador jugador) {
        this.servicioUsuarios.addJugador(jugador);
    }
    
    public void addCrupier(Crupier crupier) {
        this.servicioUsuarios.addCrupier(crupier);
    }
    
    public Sesion loginJugador(String cedula, String contrasenia) throws UsuarioException {
        return this.servicioUsuarios.loginJugador(cedula, contrasenia);
    }
    
    public Sesion loginCrupier(String cedula, String contrasenia) throws UsuarioException {
        return this.servicioUsuarios.loginCrupier(cedula, contrasenia);
    }
    
    public void logout(Sesion sesion) {
        this.servicioUsuarios.logout(sesion);
    }
    
    public void addMesa(Mesa mesa) throws MesaException {
        this.servicioMesas.addMesa(mesa);
    }
    
    public void addTipoApuesta(TipoApuesta tipoApuesta) {
        this.servicioMesas.addTipoApuesta(tipoApuesta);
    }
    
    public ArrayList<TipoApuesta> getTiposApuesta() {
        return this.servicioMesas.getTiposApuesta();
    }
    
    public void addEfecto(Efecto efecto) {
        this.servicioMesas.addEfecto(efecto);
    }
    
    public ArrayList<Efecto> getEfectos() {
        return this.servicioMesas.getEfectos();
    }
    
    public void addCasillero(Casillero casillero) {
        this.servicioMesas.addCasillero(casillero);
    }
    
    public ArrayList<Casillero> getCasilleros() {
        return this.servicioMesas.getCasilleros();
    }
}
