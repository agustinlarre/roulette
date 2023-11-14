/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion.controladores;

import excepcionesSistema.MesaException;
import java.util.List;
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
        Fachada.getInstancia().subscribir(this);
        
        this.inicializarMesa();
    }

    @Override
    public void actualizar(Observable origen, Object evento) {
        // Cambiar condiciones anidadas
        if (evento.equals(Evento.MESA_PAUSADA)) {
            this.pausar();
        } else if (evento.equals(Evento.RONDA_REANUDADA)) {
            this.reanudar();
        } else if (evento.equals(Evento.PARTICIPANTE_AGREGADO) || 
                evento.equals(Evento.PARTICIPANTE_ELIMINADO) || 
                evento.equals(Evento.APUESTA_REALIZADA) || 
                evento.equals(Evento.APUESTA_MODIFICADA)) {
            this.actualizarListaParticipantes();
        } else if (evento.equals(Evento.FICHA_AGREGADA)) {
            this.actualizarCantYValorApuestasPorRonda();
            this.actualizarListaParticipantes();
            this.actualizarMontoApostadoSegunCasillero();
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
    
    private void actualizarMontoApostadoSegunCasillero() {
        for (Casillero casillero : mesa.getRondaActual().getCasillerosConApuestas()) {
            int montoDeCasillero = mesa.getRondaActual().calcularMontoPorCasillero(casillero);
            vista.mostrarValorApostado(casillero.getCellCode(), montoDeCasillero);
        }
    }
    
    private void actualizarCantYValorApuestasPorRonda() {
        int cantidadApuestas = mesa.getRondaActual().getCantTotalApuestas();
        int valorTotalApuestas = mesa.getRondaActual().getMontoTotalApostado();
        vista.mostrarTotalApostadoPorRonda(valorTotalApuestas);
        vista.mostrarCantidadApuestasPorRonda(cantidadApuestas);
    }
    
    private void pausar() {
        this.mostrarUltimoNumeroSorteado();
        vista.inhabilitarPantallaMesa();
        vista.limpiarValoresApostados();
    }
    
    private void reanudar() {
        this.actualizarHistoricoRondas();
        this.actualizarListaParticipantes();
        this.mostrarBalanceActual();
        vista.habilitarPantallaMesa();
        vista.deshabilitarCasilleros();
        this.habilitarTiposApuesta();
        vista.actualizarNroRonda(mesa.getNroRondaActual());
    }
    
    private void inicializarMesa() {
        vista.mostrarNroMesa(this.mesa.getNroMesa());
        vista.mostrarNroRonda(mesa.getNroRondaActual());
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
    
    private void actualizarHistoricoRondas() {
        int nroRonda = mesa.getNroUltimaRonda();
        int balanceAnterior = mesa.getBalanceAnterior();
        int montoTotalApuestas = mesa.getUltimaRonda().getMontoTotalApostado();
        int montoRecolectado = mesa.getUltimaRonda().getMontoTotalGanadoPorMesa();
        int montoPagado = mesa.getUltimaRonda().getMontoTotalGanadoPorParticipantes();
        int balancePosterior = mesa.getBalance();
        vista.popularHistoricoRondas(nroRonda, balanceAnterior, montoTotalApuestas, montoRecolectado, montoPagado, balancePosterior);
        //System.out.println("Ronda: " + nroRonda + " balance anterior: " + balanceAnterior + " apuestas: " + montoTotalApuestas + " Recolectado: " + montoRecolectado + " Pagado: " + montoPagado + " balance posterior: " + balancePosterior);
    }
    
    private void actualizarListaParticipantes() {
        vista.agregarParticipantes(mesa.getParticipantes());
    }
    
    private void popularEfectos() {
        List<Efecto> efectos = Fachada.getInstancia().getEfectos();
        for (Efecto efecto : efectos) {
            vista.agregarEfecto(efecto);
        }
    }
}
