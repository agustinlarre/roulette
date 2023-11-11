/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import excepcionesSistema.UsuarioException;
import logicaNegocio.Sesion;
import presentacion.controladores.LoginCrupierControlador;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaLoginCrupier extends VentanaLogin {
    
    public VentanaLoginCrupier() {
        this.setControlador(new LoginCrupierControlador(this));
    }

    @Override
    public void proximoCU(Sesion sesionActual) {
        new VentanaIniciarMesa(sesionActual).setVisible(true);
    }
    
}
