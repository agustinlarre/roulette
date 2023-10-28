/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import excepcionesSistema.UsuarioException;
import logicaNegocio.Sesion;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaLoginJugador extends VentanaLogin {
    @Override
    public String getTitulo() {
        return "Iniciar sesión como jugador...";
    }

    @Override
    protected Sesion login(String cedula, String contrasenia) throws UsuarioException {
        return Fachada.getInstancia().loginJugador(cedula, contrasenia);
    }

    @Override
    protected void proximoCU(Sesion sesionActual) {
        new VentanaCargarSaldoInicial(sesionActual).setVisible(true);
    }
}
