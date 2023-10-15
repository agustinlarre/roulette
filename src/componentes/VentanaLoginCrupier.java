/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import excepcionesSistema.UsuarioException;
import logicaNegocio.Crupier;
import logicaNegocio.Usuario;
import logicaNegocio.Sesion;
import servicios.Fachada;

/**
 *
 * @author agust
 */
public class VentanaLoginCrupier extends VentanaLogin {
    @Override
    public String getTitulo() {
        return "Iniciar sesión como crupier...";
    }

    @Override
    protected Sesion login(String cedula, String contrasenia) throws UsuarioException {
        return Fachada.getInstancia().loginCrupier(cedula, contrasenia);
    }

    @Override
    protected void proximoCU(Sesion sesionActual) {
        new VentanaInicioMesa(sesionActual).setVisible(true);
    }
}
