/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import excepcionesSistema.UsuarioException;
import logicaNegocio.Sesion;
import presentacion.controladores.LoginJugadorControlador;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaLoginJugador extends VentanaLogin {
    
    public VentanaLoginJugador() {
        this.setControlador(new LoginJugadorControlador(this));
    }

    @Override
    public void proximoCU(Sesion sesionActual) {
        new VentanaUnirseMesa(sesionActual).setVisible(true);
    }
}
