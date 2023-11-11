/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.MesaException;
import java.util.List;
import logicaNegocio.Crupier;
import logicaNegocio.Mesa;
import logicaNegocio.Sesion;
import logicaNegocio.TipoApuesta;
import presentacion.vistas.VistaIniciarMesa;
import servicios.Fachada;

/**
 *
 * @author Agustin
 */
public class IniciarMesaControlador {
    private Sesion sesion;
    private Crupier crupier;
    private VistaIniciarMesa vista;

    public IniciarMesaControlador(Sesion sesion, VistaIniciarMesa vista) {
        this.sesion = sesion;
        this.crupier = (Crupier) sesion.getUsuario();
        this.vista = vista;
        
        hidratarTiposApuesta();
    }
    
    public void iniciarMesa(List<TipoApuesta> tiposApuestaElegidos) {
        try {
            Mesa mesa = new Mesa(tiposApuestaElegidos);
            this.crupier.setMesa(mesa);
            Fachada.getInstancia().addMesa(mesa);
            vista.proximoCU(sesion);
            vista.cerrarVentana();
        } catch(MesaException mesaEx) {
            vista.mostrarError(mesaEx.getMessage());
        }
    }
    
    private void hidratarTiposApuesta() {
        vista.obtenerTiposApuesta(Fachada.getInstancia().getTiposApuesta());
    }
}
