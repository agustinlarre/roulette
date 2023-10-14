/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import excepciones.UsuarioException;
import excepciones.UsuarioLogueadoException;
import excepciones.UsuarioNoEncontradoException;
import java.util.ArrayList;
import java.util.List;
import logicaNegocio.*;

/**
 *
 * @author agust
 */
public class ServicioUsuarios {
    private List<Jugador> listaJugadores;
    private List<Crupier> listaCrupieres;
    private List<Sesion> sesionesActivas;

    public ServicioUsuarios() {
        this.listaJugadores = new ArrayList();
        this.listaCrupieres = new ArrayList();
        this.sesionesActivas = new ArrayList();
    }
    
    public void agregarJugador(Jugador jugador) {
        this.listaJugadores.add(jugador);
    }
    
    public void agregarCrupier(Crupier crupier) {
        this.listaCrupieres.add(crupier);
    }
    
    public Sesion loginJugador(String usuario, String contrasenia) throws UsuarioException {
        return this.login(usuario, contrasenia, (List) this.listaJugadores);
    }
    
    public Sesion loginCrupier(String usuario, String contrasenia) throws UsuarioException {
        return this.login(usuario, contrasenia, (List) this.listaCrupieres);
    }
    
    private Sesion login(String cedula, String contrasenia, List<Usuario> listaUsuarios) throws UsuarioException {
        try {
            Usuario usuario = this.getUsuario(cedula, contrasenia, listaUsuarios);
            Sesion sesion = new Sesion(usuario);
            this.agregarSesionDeUsuario(sesion);
            return sesion;
        } catch (UsuarioNoEncontradoException ex1) {
            throw new UsuarioException("Credenciales incorrectas.");
        } catch (UsuarioLogueadoException ex2) {
            throw new UsuarioException("Acceso denegado. El usuario ya tiene una sesión activa.");
        }
    }
    
    private Usuario getUsuario(String cedula, String contrasenia, List<Usuario> listaUsuarios) throws UsuarioNoEncontradoException {
        Usuario usuario = null;
        for (Usuario u : listaUsuarios) {
            if (u.getCedula().equals(cedula) && u.getPassword().equals(contrasenia)) usuario = u;
        }
        if (usuario == null) {
            throw new UsuarioNoEncontradoException();
        } else {
            return usuario;
        }
    }
    
    private void agregarSesionDeUsuario(Sesion sesion) throws UsuarioLogueadoException {
        if (!this.existeSesionDeUsuario(sesion.getUsuario())) {
                this.sesionesActivas.add(sesion);
            } else {
                throw new UsuarioLogueadoException();
            }
    }
    
    public void logout(Sesion sesion) {
        this.sesionesActivas.remove(sesion);
    }
    
    private boolean existeSesionDeUsuario(Usuario usuario) {
        for (Sesion sesionActual : sesionesActivas) {
            if (sesionActual.getUsuario().equals(usuario)) return true;
        }
        return false;
    }
}
