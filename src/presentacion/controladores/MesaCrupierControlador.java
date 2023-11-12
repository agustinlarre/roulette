/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.MesaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaNegocio.Casillero;
import logicaNegocio.Crupier;
import logicaNegocio.Efecto;
import logicaNegocio.Mesa;
import logicaNegocio.Sesion;
import logicaNegocio.TipoApuesta;
import presentacion.vistas.VistaMesaCrupier;
import servicios.Fachada;
import servicios.Observable;
import servicios.Observador;

/**
 *
 * @author Agustin
 */
public class MesaCrupierControlador implements Observador {
    private Sesion sesion;
    private Crupier crupier;
    private Mesa mesa;
    private VistaMesaCrupier vista;

    public MesaCrupierControlador(Sesion sesion, VistaMesaCrupier vista) {
        this.sesion = sesion;
        this.crupier = (Crupier) sesion.getUsuario();
        this.mesa = crupier.getMesa();
        this.vista = vista;
        mesa.subscribir(this);
        
        this.inicializarMesa();
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
        // Cambiar condiciones anidadas
        if (evento.equals(Evento.MESA_PAUSADA)) {
            this.pausar();
        } else if (evento.equals(Evento.RONDA_REANUDADA)) {
            this.reanudar();
        } else if (evento.equals(Evento.PARTICIPANTE_AGREGADO)) {
            vista.actualizarListaParticipantes();
        }
    }
    
    public void nuevaAccionMesa(Efecto efectoSeleccionado) {
        this.mesa.accionarMesa(efectoSeleccionado);
    }
    
    public void cerrarMesa() {
        try {
            mesa.cerrarMesa();
            vista.cerrarVentanaMesa();
        } catch (MesaException ex) {
            vista.mostrarMensajeError(ex.getMessage());
        }
    }
    
    private void pausar() {
        this.mostrarUltimoNumeroSorteado();
        this.mostrarHistoricoNumSorteados();
        vista.inhabilitarPantallaMesa();
    }
    
    private void reanudar() {
        this.mostrarBalanceActual();
        vista.actualizarNroRonda(mesa.getNroRondaActual());
        vista.habilitarPantallaMesa();
    }
    
    private void inicializarMesa() {
        vista.mostrarNroMesa(this.mesa.getNroMesa());
        vista.deshabilitarCasilleros();
        this.popularEfectos();
        this.habilitarTiposApuesta();
        this.mostrarBalanceActual();
    }
    
    private void mostrarUltimoNumeroSorteado() {
        vista.mostrarUltimoNroSorteado(mesa.getUltimoNumeroSorteado());
    }
    
    private void mostrarBalanceActual() {
        vista.mostrarBalanceActual(mesa.getBalance());
    }
    
    private void mostrarHistoricoNumSorteados() {
        String cadenaNumerosSorteados = "";
        for (int num : mesa.getlistaNumerosSorteados()) {
            cadenaNumerosSorteados += String.valueOf(num) + " - ";
        }
        cadenaNumerosSorteados = cadenaNumerosSorteados.substring(0, cadenaNumerosSorteados.length()-1);
        vista.mostrarUltimosLanzamientos(cadenaNumerosSorteados);
    }
    
    private void habilitarTiposApuesta() {
        for (TipoApuesta tipoApuesta : this.mesa.getTiposApuesta()) {
            this.habilitarCasilleros(tipoApuesta);
        }
    }
    
    private void habilitarCasilleros(TipoApuesta tipoApuesta) {
        for (Casillero casillero : tipoApuesta.getCasillerosDisponibles()) {
            vista.habilitarCasillero(casillero.getCellCode());
        }
    }
    
    private void popularEfectos() {
        List<Efecto> efectos = Fachada.getInstancia().getEfectos();
        for (Efecto efecto : efectos) {
            vista.agregarEfecto(efecto);
        }
    }
}
