/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.MesaException;
import logicaNegocio.Jugador;
import logicaNegocio.Mesa;
import logicaNegocio.Participante;
import logicaNegocio.Sesion;
import presentacion.vistas.VistaUnirseMesa;
import servicios.Fachada;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class UnirseMesaControlador implements Observador {
    private Sesion sesion;
    private Jugador jugador;
    private VistaUnirseMesa vista;
    
    public UnirseMesaControlador(Sesion sesion, VistaUnirseMesa vista) {
        this.sesion = sesion;
        this.jugador = (Jugador) sesion.getUsuario();
        this.vista = vista;
        Fachada.getInstancia().subscribir(this);
        
        hidratarMesas();
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
        if (evento.equals(Evento.MESA_AGREGADA)) {
            this.hidratarMesas();
        }
    }
    
    public void cerrarSesion() {
        Fachada.getInstancia().logout(sesion);
        vista.cerrarVentana();
    }
    
    public void unirseMesa(Mesa mesa) {
        try {
            Participante participante = new Participante(this.jugador, mesa);
            jugador.participar(participante);
            vista.proximoCU(participante);
        } catch(MesaException mesaEx) {
            vista.mostrarError(mesaEx.getMessage());
        }
    }
    
    private void hidratarMesas() {
        vista.obtenerMesasDisponibles(Fachada.getInstancia().getMesas());
    }
}
